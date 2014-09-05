package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.RoomFrame;

public class RoomFrameMouseMoveMonitor extends MouseAdapter{
	@Override
	public void mouseReleased(MouseEvent e) {
		RoomFrame.isDragged = false;
		RoomFrame.roomFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		RoomFrame.firstFrame = new Point(e.getX(), e.getY());
		RoomFrame.isDragged = true;
		RoomFrame.roomFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
