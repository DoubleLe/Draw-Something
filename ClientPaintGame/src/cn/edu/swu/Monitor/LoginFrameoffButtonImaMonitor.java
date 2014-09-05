package cn.edu.swu.Monitor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.LoginFrame;


public class LoginFrameoffButtonImaMonitor extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		LoginFrame.loginFrame.dispose();
		System.exit(0);
	}
}
