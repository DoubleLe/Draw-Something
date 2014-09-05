package cn.edu.swu.Monitor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectOutputStream;

import cn.edu.swu.communicationPicture.SendPicture;
import cn.edu.swu.model.Response;

public class ClearDrawPanelMonitor extends MouseAdapter{
	private Response response;
	private ObjectOutputStream oos;
	public ClearDrawPanelMonitor(Response response, ObjectOutputStream oos){
		this.response = response;
		this.oos = oos;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);
			SendPicture.isLoop = false;
			SendPicture.sendPictures(response, oos);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		SendPicture.isLoop = true;
	}

}
