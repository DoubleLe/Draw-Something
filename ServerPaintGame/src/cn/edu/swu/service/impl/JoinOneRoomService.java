package cn.edu.swu.service.impl;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.edu.swu.Server.RefreshInRoomOtherPlayersListThread;
import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.service.ServerService;

public class JoinOneRoomService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		ServerRecource.getServersListMap().get(String.valueOf(request.getServerListCount())).get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount())).getPlayersUsersList().add(request.getHostUser());
		
		Response response = new Response();
		
		response.setHostUser(request.getHostUser());
		response.setResponseName(request.getServiceName());
		response.setServerListCount(request.getServerListCount());
		response.setRoomNumber(request.getRoomNumber());
		response.setServersListMap(ServerRecource.getServersListMap());
		
		RefreshInRoomOtherPlayersListThread rInt = new RefreshInRoomOtherPlayersListThread(request);
		Thread t = new Thread(rInt);
		t.start();
		
		
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
