package cn.edu.swu.rmiServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import cn.edu.swu.rmiImp.LoginImp;
import cn.edu.swu.rmiInterface.LoginInterface;

public class LoginServer {
	public static void LoginSeverFunction(){
		try {
			
			@SuppressWarnings("unused")
			Registry registry = LocateRegistry.createRegistry(1099);
			LoginInterface loginServer = new LoginImp();
			Naming.rebind("loginServer", loginServer);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
