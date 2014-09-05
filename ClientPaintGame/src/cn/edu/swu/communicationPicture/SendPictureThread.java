package cn.edu.swu.communicationPicture;

import java.awt.Dimension;


import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.model.MyIcon;
import cn.edu.swu.model.Request;

public class SendPictureThread implements Runnable{
	private Request request;
	private Point point;
	private ObjectOutputStream oos;
	private Robot robot;
	public SendPictureThread(Request request, ObjectOutputStream oos, Robot robot){
		this.request = request;
		this.oos = oos;
		this.robot = robot;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!SendPicture.isLoop){
			try {
				point = RoomFrame.roomFrame.getLocation(point);
				point.x = (int) (point.getX() + 171);
				point.y = (int) (point.getY() + 98);
				Rectangle screenRect = new Rectangle(point, new Dimension(RoomFrame.addpaintPanel.getWidth(), RoomFrame.addpaintPanel.getHeight()));
				BufferedImage image = robot.createScreenCapture(screenRect);
				
				MyIcon myIcon = new MyIcon(image);
				request.setImage(myIcon);
				request.setPoint(point);
				
				
				oos.writeObject(request);
				oos.flush();
				Thread.sleep(50);
				oos.reset();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
