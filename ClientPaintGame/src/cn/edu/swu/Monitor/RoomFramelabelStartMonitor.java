package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.RoomFrame;

public class RoomFramelabelStartMonitor extends MouseAdapter{
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		RoomFrame.labelStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
}
