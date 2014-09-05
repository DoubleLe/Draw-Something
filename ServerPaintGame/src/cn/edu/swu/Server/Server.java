package cn.edu.swu.Server;

import java.io.IOException;

import java.net.ServerSocket;

import cn.edu.swu.rmiServer.LoginServer;

public class Server {
	public static final int originalPort=59780;
	public static void main(String[] args) {
		
		LoginServer.LoginSeverFunction();
		
		ServerSocket[] server = new ServerSocket[12];
		try {
			for(int i = 0;i<12;i++){
				
				server[i] = new ServerSocket(originalPort+i);
				ServerAcceptThread sat = new ServerAcceptThread(server[i]);
				Thread t = new Thread(sat);
				t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
