package cn.edu.swu.service.impl;


import java.io.IOException;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import cn.edu.swu.Server.RefreshInRoomOtherPlayersListThread;
import cn.edu.swu.Server.RefreshOtherUsrsDisplayRoomsListThread;
import cn.edu.swu.Server.Server;
import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.Room;
import cn.edu.swu.model.User;
import cn.edu.swu.service.ServerService;

public class ExitCurrentServerService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		
		ServerRecource.getEveryServerUsersMap().get(String.valueOf(request.getServerListCount())).remove(request.getHostUser());
		
		
		if(null!=ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount()))){
			
			
			if(null!=ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()))){
								
				Room room = ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
				
				List<User> playUersList = room.getPlayersUsersList();
				if(0!=playUersList.size()){
					ServerRecource.getUserSocketMap().remove(request.getHostUser().getUserId());
					if(request.getHostUser().getUserId().equals(room.getHomeownersUser().getUserId())){
						ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).remove(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
						ServerRecource.getRoomsListMap().remove(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
						
						Room new_room = new Room();
						new_room.setHomeownersUser(playUersList.get(0));
						playUersList.remove(0);
						new_room.setPlayersUsersList(playUersList);
						new_room.setRoomId(room.getRoomId());
						
						ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).put(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()), new_room);
						ServerRecource.getRoomsListMap().put(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()), new_room);
						
					}else{
						ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount())).getPlayersUsersList().remove(request.getHostUser());
						ServerRecource.getRoomsListMap().get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount())).getPlayersUsersList().remove(request.getHostUser());
					}
					
					RefreshInRoomOtherPlayersListThread rt = new RefreshInRoomOtherPlayersListThread(request);
					Thread t = new Thread(rt);
					t.start();
					
				}else{
					if(ServerRecource.getEveryServerUsersMap().get(String.valueOf(request.getServerListCount())).isEmpty()){
						ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).remove(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
						ServerRecource.getRoomsListMap().remove(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
					}
					ServerRecource.getUserSocketMap().remove(request.getHostUser().getUserId());
					
					RefreshOtherUsrsDisplayRoomsListThread rt = new RefreshOtherUsrsDisplayRoomsListThread(request);
					Thread t = new Thread(rt);
					t.start();
				}
			}
		}
		
		try {
			ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(socket));
			
			Response response = new Response();
			response.setHostUser(request.getHostUser());
			response.setResponseName(request.getServiceName());
			response.setServerListCount(request.getServerListCount());
			response.setTip("退出当前服务器: "+Server.originalPort+request.getServerListCount());
			
			oos.writeObject(response);
			
			oos.flush();
			
			Thread.sleep(1000);
			
			socket.close();
			
			String key = socket.getInetAddress().getHostAddress()+":"+socket.getPort();
			ServerRecource.removeObjectStream(key);
			
		}catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
