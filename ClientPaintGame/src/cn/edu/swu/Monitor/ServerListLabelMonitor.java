package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import cn.edu.swu.Client.ClientThread;
import cn.edu.swu.clientFrame.LoginFrame;
import cn.edu.swu.clientFrame.ServerListFrame;
import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.User;

public class ServerListLabelMonitor extends MouseAdapter{
	private int portCount;
	
	public ServerListLabelMonitor(int portCount){
		this.portCount = portCount;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(1==e.getClickCount()){
			try {
				Socket socket = new Socket("127.0.0.1", 59780+portCount);
				ClientThread clientThread = new ClientThread(socket);
				ObjectOutputStream oos = clientThread.getOos();
				
				Request request = new Request();
				User hostUser = new User();
				
				hostUser.setUserId(LoginFrame.loginFrame.getIdField().getText());
				hostUser.setPassword(new String(LoginFrame.loginFrame.getPasswordField().getPassword()));
				request.setHostUser(hostUser);
				request.setServerListCount(portCount);
				request.setServiceName(ClientResource.LOGIN);
				
				oos.writeObject(request);
				oos.flush();
				
				Thread t = new Thread(clientThread);
				t.start();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		ServerListFrame.labelList[portCount].setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
