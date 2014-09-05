package cn.edu.swu.communicationPicture;

import java.awt.AWTException;

import java.awt.Robot;
import java.io.ObjectOutputStream;

import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;


public class SendPicture {
	public static boolean isLoop = false;
	
	public static void sendPictures(Response response,ObjectOutputStream oos){

		Request request = new Request();
		
		request.setStart_YesNo(response.isStart_YesNo());
		request.setServerListCount(response.getServerListCount());
		request.setRoomNumber(response.getRoomNumber());
		request.setPainting_YesNo(response.isPainting_YesNo());
		request.setHostUser(response.getHostUser());
		request.setServiceName(ClientResource.PICTURETRANSMISSION);
		
		Robot robot;
		try {
			robot = new Robot();
			SendPictureThread spt = new SendPictureThread(request, oos, robot);
			Thread t = new Thread(spt);
			t.start();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
