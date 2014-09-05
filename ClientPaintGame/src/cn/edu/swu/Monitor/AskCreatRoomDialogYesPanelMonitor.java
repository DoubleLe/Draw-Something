package cn.edu.swu.Monitor;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.AskCreatRoomDialog;
import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;

public class AskCreatRoomDialogYesPanelMonitor extends MouseAdapter{
	private Response response;
	private ObjectOutputStream oos;
	
	public AskCreatRoomDialogYesPanelMonitor(Response response, ObjectOutputStream oos){
		this.response = response;
		this.oos = oos;
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		AskCreatRoomDialog.yesPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(1==e.getClickCount()){
			
			Request request = new Request();
			request.setHostUser(response.getHostUser());
			request.setServerListCount(response.getServerListCount());
			request.setServiceName(ClientResource.CREAT_ROOM);
			request.setCompetitionNumber(AskCreatRoomDialog.joinNumber);
			
			try {
				oos.writeObject(request);
				oos.flush();
				
				AskCreatRoomDialog.askCreatRoomDialog.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
