package cn.edu.swu.Monitor;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.ServerListFrame;

public class ServerListFrameMouseMotionMonitor extends MouseAdapter{
	@Override
	public void mouseDragged(MouseEvent e) {
		if(ServerListFrame.isDragged){
			ServerListFrame.secondFrame = new Point(ServerListFrame.serverListFrame.getLocation().x+e.getX()-ServerListFrame.firstFrame.x,
					ServerListFrame.serverListFrame.getLocation().y + e.getY() - ServerListFrame.firstFrame.y);
			
			ServerListFrame.serverListFrame.setLocation(ServerListFrame.secondFrame);
		}
	}
}
