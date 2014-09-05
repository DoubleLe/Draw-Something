package cn.edu.swu.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cn.edu.swu.data.ServerRecource;


public class ServerAcceptThread implements Runnable{
	ServerSocket server;
	public ServerAcceptThread(ServerSocket server){
		this.server = server;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Socket socket = server.accept();
				ServerRecource.putObjectStream(socket);
				ServerThread st = new ServerThread(socket);
				Thread t = new Thread(st);
				t.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
