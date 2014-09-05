package cn.edu.swu.Monitor;

import java.awt.Cursor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;


import cn.edu.swu.clientFrame.AskCreatRoomDialog;
import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.model.Response;

public class RoomFramecreatRoomlabelMonitor extends MouseAdapter{
	private Response response;
	private ObjectOutputStream oos;
	
	public RoomFramecreatRoomlabelMonitor(Response response, ObjectOutputStream oos){
		this.response = response;
		this.oos = oos;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		RoomFrame.creatRoomlabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(1==e.getClickCount()){
			AskCreatRoomDialog.askCreatRoomDialog = new AskCreatRoomDialog(response, oos);
			AskCreatRoomDialog.askCreatRoomDialog.setVisible(true);
		}
	}
}
