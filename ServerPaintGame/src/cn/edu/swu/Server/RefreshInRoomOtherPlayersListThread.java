package cn.edu.swu.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.Room;
import cn.edu.swu.model.User;

public class RefreshInRoomOtherPlayersListThread implements Runnable {

private Request request;
	
	public RefreshInRoomOtherPlayersListThread(Request request){
		this.request = request;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Room room = ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
		Response response = new Response();
		
		response.setGuestUser(request.getHostUser());
		response.setResponseName("RefreshInRoomOtherPlayersList");
		response.setServerListCount(request.getServerListCount());
		response.setRoomNumber(request.getRoomNumber());
		response.setServersListMap(ServerRecource.getServersListMap());
		response.setStart_YesNo(false);
		
		
		
		for(User user : room.getPlayersUsersList()){
			
			Socket st = ServerRecource.getUserSocketMap().get(user.getUserId());
			response.setHostUser(user);
			
			if((!(user.getUserId().equals(request.getHostUser().getUserId()))) && st!=null){
				
				try {
					ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(st));
					oos.writeObject(response);
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		response.setStart_YesNo(true);
		Socket st = ServerRecource.getUserSocketMap().get(room.getHomeownersUser().getUserId());
		response.setHostUser(room.getHomeownersUser());
		ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(st));
		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
