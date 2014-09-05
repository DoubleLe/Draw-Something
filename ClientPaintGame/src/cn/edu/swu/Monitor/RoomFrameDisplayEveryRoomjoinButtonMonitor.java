package cn.edu.swu.Monitor;

import java.awt.Cursor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;

import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;

public class RoomFrameDisplayEveryRoomjoinButtonMonitor extends MouseAdapter{
	private Response response;
	private ObjectOutputStream oos;
	private JButton joinButton;
	private int clicked_room_number;
	
	public RoomFrameDisplayEveryRoomjoinButtonMonitor(Response response, ObjectOutputStream oos, JButton joinButton, int room_number){
		this.response = response;
		this.oos = oos;
		this.joinButton = joinButton;
		this.clicked_room_number = room_number;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		joinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(1==e.getClickCount()){
			
			Request request = new Request();
			request.setRoomNumber(clicked_room_number);
			request.setHostUser(response.getHostUser());
			request.setServerListCount(response.getServerListCount());
			request.setServiceName(ClientResource.JOIN_ONE_ROOM);
			
			
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
