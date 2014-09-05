package cn.edu.swu.Monitor;

import java.awt.Cursor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;

import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;

public class RoomFramefreshRoomleftRoomImgLabelPaintMonitor extends MouseAdapter{
	private Response response;
	private ObjectOutputStream oos;
	private JLabel leftRoomImgLabel;
	
	public RoomFramefreshRoomleftRoomImgLabelPaintMonitor(Response response, ObjectOutputStream oos, JLabel leftRoomImgLabel){
		this.response = response;
		this.oos = oos;
		this.leftRoomImgLabel = leftRoomImgLabel;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		leftRoomImgLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(1==e.getClickCount()){
			Request request = new Request();
			request.setHostUser(response.getHostUser());
			request.setServerListCount(response.getServerListCount());
			request.setRoomNumber(response.getRoomNumber());
			request.setServiceName(ClientResource.LEFT_ROOM);
			request.setStart_YesNo(response.isStart_YesNo());
			
			try {
				oos.writeObject(request);
				oos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
