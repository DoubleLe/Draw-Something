package cn.edu.swu.service.impl;

import java.net.Socket;

import cn.edu.swu.Server.PictureTransmissionThread;
import cn.edu.swu.model.Request;
import cn.edu.swu.service.ServerService;

public class PictureTransmissionService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		
		
		PictureTransmissionThread ptt = new PictureTransmissionThread(request);
		
		Thread td = new Thread(ptt);
		
		td.start();
	}

}
