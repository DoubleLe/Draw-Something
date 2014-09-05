package cn.edu.swu.service.impl;

import java.io.IOException;




import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


import cn.edu.swu.Server.RefreshOtherUsrsDisplayRoomsListThread;
import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.Room;
import cn.edu.swu.service.ServerService;

public class CreatRoomService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		ServerRecource.setA_ROOM_USERS_COUNT(request.getCompetitionNumber());
		
		RefreshOtherUsrsDisplayRoomsListThread rt = new RefreshOtherUsrsDisplayRoomsListThread(request);
		Thread t = new Thread(rt);
		t.start();
		
		int count = 0;
		request.setRoomNumber(builderRoomNumber(request, count));
		
		ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(socket));
		
		Response response = new Response();
		response.setHostUser(request.getHostUser());
		response.setResponseName(request.getServiceName());
		response.setServerListCount(request.getServerListCount());
		response.setRoomNumber(request.getRoomNumber());
		response.setServersListMap(ServerRecource.getServersListMap());
		
		
		
		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private int builderRoomNumber(Request request, int count){
		int getroomNumber = count;
		count++;
		if(ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount()))!=null){
			if(ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(getroomNumber*100+request.getServerListCount()))!=null){
				return builderRoomNumber(request, count);
			}else{
				Room room = new Room();
				Map<String, Room> temporaryRoomsListMap = new HashMap<String, Room>();
				
				room.setHomeownersUser(request.getHostUser());
				room.setRoomId(getroomNumber);
				ServerRecource.getRoomsListMap().put(String.valueOf(getroomNumber*100+request.getServerListCount()), room);
				for(Map.Entry<String, Room> en : ServerRecource.getRoomsListMap().entrySet()){
					if(Integer.parseInt(en.getKey())%100==request.getServerListCount()){
						
						temporaryRoomsListMap.put(en.getKey(), en.getValue());
					}
				}
				ServerRecource.getServersListMap().put(String.valueOf(request.getServerListCount()), temporaryRoomsListMap);
				
				
				return getroomNumber;
			}
		}else{
			Room room = new Room();
			Map<String, Room> temporaryRoomsListMap = new HashMap<String, Room>();
			
			room.setHomeownersUser(request.getHostUser());
			room.setRoomId(getroomNumber);
			ServerRecource.getRoomsListMap().put(String.valueOf(getroomNumber*100+request.getServerListCount()), room);
			for(Map.Entry<String, Room> en : ServerRecource.getRoomsListMap().entrySet()){
				if(Integer.parseInt(en.getKey())%100==request.getServerListCount()){
					
					temporaryRoomsListMap.put(en.getKey(), en.getValue());
				}
			}
			ServerRecource.getServersListMap().put(String.valueOf(request.getServerListCount()), temporaryRoomsListMap);
			
			
			return getroomNumber;
		}
	}

}
