package cn.edu.swu.service.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.service.ServerService;

public class PaintRoomService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		Response response = new Response();
		
		response.setHostUser(request.getHostUser());
		response.setResponseName(request.getServiceName());
		response.setRoomNumber(request.getRoomNumber());
		response.setServerListCount(request.getServerListCount());
		response.setServersListMap(ServerRecource.getServersListMap());
		response.setPainting_YesNo(request.isPainting_YesNo());
		
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
