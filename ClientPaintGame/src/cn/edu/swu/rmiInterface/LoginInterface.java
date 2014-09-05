package cn.edu.swu.rmiInterface;

import java.rmi.Remote;


import java.rmi.RemoteException;


import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;


public interface LoginInterface extends Remote{
	public Response ServerListImp(Request request) throws RemoteException;
}
