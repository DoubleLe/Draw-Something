package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.LoginFrame;

public class LoginFrameMouseMoveMonitor extends MouseAdapter{
	@Override
	public void mouseReleased(MouseEvent e) {
		LoginFrame.isDragged = false;
		LoginFrame.loginFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		LoginFrame.firstFrame = new Point(e.getX(), e.getY());
		LoginFrame.isDragged = true;
		LoginFrame.loginFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
