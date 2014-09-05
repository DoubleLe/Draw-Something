package cn.edu.swu.Monitor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import cn.edu.swu.clientFrame.ServerListFrame;

public class ServerListFrameminButtonImaMonitor extends MouseAdapter{
	@Override
	public void mouseClicked(MouseEvent e) {
		ServerListFrame.serverListFrame.setExtendedState(JFrame.ICONIFIED);
	}
}
