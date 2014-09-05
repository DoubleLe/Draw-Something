package cn.edu.swu.Monitor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import cn.edu.swu.clientFrame.LoginFrame;


public class LoginFrameminButtonImaMonitor extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		LoginFrame.loginFrame.setExtendedState(JFrame.ICONIFIED);
	}
}
