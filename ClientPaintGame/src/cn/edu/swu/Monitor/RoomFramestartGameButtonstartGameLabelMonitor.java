package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;

public class RoomFramestartGameButtonstartGameLabelMonitor extends MouseAdapter{
	private Response response;
	private ObjectOutputStream oos;
	
	public RoomFramestartGameButtonstartGameLabelMonitor(Response response, ObjectOutputStream oos){
		this.response = response;
		this.oos = oos;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		RoomFrame.startGameLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Request request = new Request();
		request.setHostUser(response.getHostUser());
		request.setRoomNumber(response.getRoomNumber());
		request.setServerListCount(response.getServerListCount());
		request.setServiceName(ClientResource.TIMECOUNTINTOPAINTROOM);
		
		try {
			oos.writeObject(request);
			oos.flush();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
