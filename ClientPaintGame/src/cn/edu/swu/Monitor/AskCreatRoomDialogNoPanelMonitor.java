package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.AskCreatRoomDialog;

public class AskCreatRoomDialogNoPanelMonitor extends MouseAdapter{
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		AskCreatRoomDialog.noPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(1==e.getClickCount()){
			AskCreatRoomDialog.askCreatRoomDialog.dispose();
		}
	}
}
