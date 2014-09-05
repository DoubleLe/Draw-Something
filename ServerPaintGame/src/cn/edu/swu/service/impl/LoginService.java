package cn.edu.swu.service.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.net.Socket;

import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.User;
import cn.edu.swu.service.ServerService;

public class LoginService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		
		User hostUser = request.getHostUser();
		hostUser = ServerRecource.getUSER_MAP().get(request.getHostUser().getUserId());
		
		ServerRecource.getOnlineMap().put(request.getHostUser().getUserId(), hostUser);
		ServerRecource.getEveryServerUsersMap().get(String.valueOf(request.getServerListCount())).add(hostUser);
		ServerRecource.getUserSocketMap().put(request.getHostUser().getUserId(), socket);
		
		Response response = new Response();
		response.setHostUser(hostUser);
		response.setServersListMap(ServerRecource.getServersListMap());
		response.setServerListMap(ServerRecource.getSERVER_MAP());
		response.setResponseName("Show_Room_Count");
		response.setServerListCount(request.getServerListCount());
		response.setServerListName(ServerRecource.getConstellationname()[request.getServerListCount()]);
		
		response.setRoomNumber(100);
		
		ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(socket));
	
		
		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
