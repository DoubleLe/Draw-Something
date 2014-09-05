package cn.edu.swu.Monitor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.ServerListFrame;

public class ServerListFrameoffButtonImaMonitor extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		ServerListFrame.serverListFrame.dispose();
		System.exit(0);
	}
}
