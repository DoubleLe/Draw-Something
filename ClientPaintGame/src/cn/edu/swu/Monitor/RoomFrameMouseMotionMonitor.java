package cn.edu.swu.Monitor;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.RoomFrame;

public class RoomFrameMouseMotionMonitor extends MouseAdapter{
	@Override
	public void mouseDragged(MouseEvent e) {
		if(RoomFrame.isDragged){
			RoomFrame.secondFrame = new Point(RoomFrame.roomFrame.getLocation().x+e.getX()-RoomFrame.firstFrame.x,
					RoomFrame.roomFrame.getLocation().y + e.getY() - RoomFrame.firstFrame.y);
			
			RoomFrame.roomFrame.setLocation(RoomFrame.secondFrame);
		}
	}
}
