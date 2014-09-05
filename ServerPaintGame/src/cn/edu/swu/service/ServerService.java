package cn.edu.swu.service;

import java.net.Socket;

import cn.edu.swu.model.Request;


public interface ServerService {
	public void service(Request request,Socket socket);
}
