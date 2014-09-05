package cn.edu.swu.Client;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.edu.swu.data.ClientResource;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;


public class ClientThread implements Runnable{
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	public ClientThread(Socket socket){
		this.socket = socket;
		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ObjectInputStream getOis() {
		return ois;
	}

	public ObjectOutputStream getOos() {
		return oos;
	}


	public Socket getSocket() {
		return socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		Response response = new Response();
		try {
			while(!socket.isClosed()){
				
				
				response = (Response)ois.readObject();
				
				String className = ClientResource.getClassName(response.getResponseName());
				ResponseHandle handle = (ResponseHandle)Class.forName(className).newInstance();
				handle.handle(response,ois,oos);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
