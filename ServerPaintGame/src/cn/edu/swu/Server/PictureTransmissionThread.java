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

public class PictureTransmissionThread implements Runnable {
	private Request request;
	
	public PictureTransmissionThread(Request request){
		this.request = request;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Response response = new Response();
		
		response.setServerListCount(request.getServerListCount());
		response.setRoomNumber(request.getRoomNumber());
		response.setGuestUser(request.getHostUser());
		response.setServersListMap(ServerRecource.getServersListMap());
		response.setTopic_hint("œ÷‘⁄”…£∫ "+request.getHostUser().getUserName()+" ª≠Õº");
		response.setImage(request.getImage());
		response.setPoint(request.getPoint());
		response.setResponseName("ReceivePicture");
		
		
		
		Room room = ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
		for(User user : room.getPlayersUsersList()){
			
			Socket st = ServerRecource.getUserSocketMap().get(user.getUserId());
			response.setHostUser(user);
			
			if((!(user.getUserId().equals(request.getHostUser().getUserId()))) && st!=null){
				
				try {
					ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(st));
					oos.writeObject(response);
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(!(request.getHostUser().getUserId().equals(room.getHomeownersUser().getUserId()))){
			Socket st = ServerRecource.getUserSocketMap().get(room.getHomeownersUser().getUserId());
			response.setHostUser(room.getHomeownersUser());
			
			try {
				ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(st));
				oos.writeObject(response);
				oos.flush();
				oos.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
