package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.ServerListFrame;

public class ServerListFrameMouseMoveMonitor extends MouseAdapter{
	@Override
	public void mouseReleased(MouseEvent e) {
		ServerListFrame.isDragged = false;
		ServerListFrame.serverListFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ServerListFrame.firstFrame = new Point(e.getX(), e.getY());
		ServerListFrame.isDragged = true;
		ServerListFrame.serverListFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
