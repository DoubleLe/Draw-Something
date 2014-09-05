package cn.edu.swu.rmiClient;

import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.User;
import cn.edu.swu.rmiInterface.LoginInterface;


public class LoginClient {
	
	public static Response Login(User hostUser){

		Request request = new Request();
		request.setHostUser(hostUser);
		Response response;
		
		String ipAdress = "rmi://127.0.0.1:1099/";
		try {
			LoginInterface loginImp = (LoginInterface)Naming.lookup(ipAdress+"loginServer");
			response = loginImp.ServerListImp(request);
			
			return response;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
//	public static void main(String[] args) {
//		LoginClient.Login();
//		
//		ServerListFrame.serverListFrame = new ServerListFrame();
//		ServerListFrame.serverListFrame.setVisible(true);
//		
//		ServerListFrame.freshServerList(response);
//	}
}
