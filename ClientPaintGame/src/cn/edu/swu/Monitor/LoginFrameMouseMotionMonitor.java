package cn.edu.swu.Monitor;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.LoginFrame;

public class LoginFrameMouseMotionMonitor extends MouseAdapter{
	@Override
	public void mouseDragged(MouseEvent e) {
		if(LoginFrame.isDragged){
			LoginFrame.secondFrame = new Point(LoginFrame.loginFrame.getLocation().x+e.getX()-LoginFrame.firstFrame.x,
					LoginFrame.loginFrame.getLocation().y + e.getY() - LoginFrame.firstFrame.y);
			
			LoginFrame.loginFrame.setLocation(LoginFrame.secondFrame);
		}
	}
}
