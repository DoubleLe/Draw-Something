package cn.edu.swu.rmiImp;

import java.rmi.RemoteException;


import java.rmi.server.UnicastRemoteObject;


import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.User;
import cn.edu.swu.rmiInterface.LoginInterface;

public class LoginImp extends UnicastRemoteObject implements LoginInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8602780193784756465L;

	public LoginImp() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response ServerListImp(Request request) throws RemoteException {
		// TODO Auto-generated method stub
		User requestUser = ServerRecource.getUSER_MAP().get(request.getHostUser().getUserId());
		Response response = new Response();
		
		if(requestUser!=null){
			response.setHostUser(request.getHostUser());
			
			if(requestUser.getPassword().equals(request.getHostUser().getPassword())){
				response.setServerListMap(ServerRecource.getSERVER_MAP());
				response.setResponseCode(Response.LOGIN_SUCCESS);
				response.setHostUser(requestUser);
				response.setTip("³É¹¦µÇÂ½£¡");
			}else{
				response.setResponseCode(Response.LOGIN_PASSWORD_ERROR);
				response.setTip("ÃÜÂë´íÎó£¡");
			}
		}else{
			response.setResponseCode(Response.LOGIN_ACCOUNT_NOT);
			response.setTip("ÕËºÅ´íÎó£¡");
		}
		
		return response;
	}
	
}
