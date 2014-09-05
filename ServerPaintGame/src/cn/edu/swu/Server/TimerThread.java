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
import cn.edu.swu.service.impl.TimeCountForIntoPaintRoomService;

public class TimerThread implements Runnable{

private Request request;
	
	public TimerThread(Request request){
		this.request = request;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Room room = ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()));
				
		Response response = new Response();
		
		response.setGuestUser(request.getHostUser());
		response.setResponseName(request.getServiceName());
		response.setServerListCount(request.getServerListCount());
		response.setRoomNumber(request.getRoomNumber());
		response.setServersListMap(ServerRecource.getServersListMap());
		response.setTime_count(TimeCountForIntoPaintRoomService.set_time_count_start(request));
		response.setHostUser(request.getHostUser());
		response.setPainting_YesNo(false);
			
		for(User u : room.getPlayersUsersList()){
			
			Socket st_room_home_user = ServerRecource.getUserSocketMap().get(u.getUserId());
			response.setHostUser(u);
			ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(st_room_home_user));
			try {
				oos.writeObject(response);
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
