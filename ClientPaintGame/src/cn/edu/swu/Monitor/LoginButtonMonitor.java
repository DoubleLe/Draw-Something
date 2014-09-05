package cn.edu.swu.Monitor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cn.edu.swu.clientFrame.LoginFrame;
import cn.edu.swu.clientFrame.ServerListFrame;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.User;
import cn.edu.swu.rmiClient.LoginClient;

public class LoginButtonMonitor implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		User hostUser = new User();
		hostUser.setUserId(LoginFrame.loginFrame.getIdField().getText());
		hostUser.setPassword(new String(LoginFrame.loginFrame.getPasswordField().getPassword()));
		
		Response response = LoginClient.Login(hostUser);
		
		if(Response.LOGIN_PASSWORD_ERROR==response.getResponseCode()){
			LoginFrame.loginFrame.getPasswordError().setForeground(Color.RED);
			LoginFrame.loginFrame.getPasswordError().setText(response.getTip());
		}else{
			LoginFrame.loginFrame.getPasswordError().setText("");
		}
		
		if(Response.LOGIN_ACCOUNT_NOT==response.getResponseCode()){
			LoginFrame.loginFrame.getUserNameError().setForeground(Color.RED);
			LoginFrame.loginFrame.getUserNameError().setText(response.getTip());
		}else{
			LoginFrame.loginFrame.getUserNameError().setText("");
		}
		
		if(Response.LOGIN_SUCCESS==response.getResponseCode()){
			LoginFrame.loginFrame.dispose();
			
			ServerListFrame.serverListFrame = new ServerListFrame();
			ServerListFrame.serverListFrame.setVisible(true);
			
			ServerListFrame.freshServerList(response);
		}
	}

	
	
}
