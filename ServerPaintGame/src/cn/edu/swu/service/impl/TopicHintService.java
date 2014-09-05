package cn.edu.swu.service.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;


import cn.edu.swu.Server.TopicHintThread;
import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.service.ServerService;

public class TopicHintService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		Response response = new Response();
		
		response.setHostUser(request.getHostUser());
		response.setPainting_YesNo(request.isPainting_YesNo());
		response.setResponseName(request.getServiceName());
		response.setRoomNumber(request.getRoomNumber());
		response.setServerListCount(request.getServerListCount());
		response.setServersListMap(ServerRecource.getServersListMap());
		response.setStart_YesNo(request.isStart_YesNo());
		
		TopicHintThread tht = new TopicHintThread(request);
		Thread td = new Thread(tht);
		td.start();
		
		response.setTopic_hint("ÌâÄ¿ÊÇ£º  "+ServerRecource.getTopicHint().get(builderTopicNumber()));
		ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(socket));
	
		
		try {
			oos.writeObject(response);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	private int builderTopicNumber(){
		return (int)(Math.random()*(11-0))+0;
	}
}
