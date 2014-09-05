package cn.edu.swu.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.service.ServerService;


public class ServerThread implements Runnable{
	private Socket socket;
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!socket.isClosed()){
					ObjectInputStream ois = ServerRecource.getObjectInputStream(ServerTool.getSocketKey(socket));
					Request request = (Request)ois.readObject();
					
					request.getHostUser().setIp(socket.getInetAddress().getHostAddress());
					request.getHostUser().setPort(socket.getPort());
					request.getHostUser().setUserName(ServerRecource.getUser(request.getHostUser().getUserId()).getUserName());
					
					String className = ServerRecource.getClassName(request.getServiceName());
					
					
					
					ServerService service = (ServerService)Class.forName(className).newInstance();
					
					
					service.service(request, socket);
					
			}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				String key = socket.getInetAddress().getHostAddress()+":"+socket.getPort();
				ServerRecource.removeObjectStream(key);
			}
	}

}
