package cn.edu.swu.Server;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.User;

public class RefreshOtherUsrsDisplayRoomsListThread implements Runnable{
	private Request request;
	
	public RefreshOtherUsrsDisplayRoomsListThread(Request request){
		this.request = request;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(null!=ServerRecource.getEveryServerUsersMap().get(String.valueOf(request.getServerListCount()))){
			
			
			List<User> roomsList = ServerRecource.getEveryServerUsersMap().get(String.valueOf(request.getServerListCount()));
			
			Response response = new Response();
			response.setGuestUser(request.getHostUser());
			response.setResponseName("RefreshUserDisplayRoomsList");
			response.setServerListCount(request.getServerListCount());
			response.setRoomNumber(request.getRoomNumber());
			response.setServersListMap(ServerRecource.getServersListMap());
			
			for(User u : roomsList){
				
				Socket st = ServerRecource.getUserSocketMap().get(u.getUserId());
				response.setHostUser(u);
				
				if((!(u.getUserId().equals(request.getHostUser().getUserId()))) && st!=null){
					
					
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
			
		}
	}

}
