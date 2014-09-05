package cn.edu.swu.clientFrame;

import java.io.IOException;
import java.io.ObjectOutputStream;

import cn.edu.swu.model.Request;

public class SendThread implements Runnable{
	private Request request;
	private ObjectOutputStream oos;
	public SendThread(Request request, ObjectOutputStream oos){
		this.request = request;
		this.oos =oos;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			oos.writeObject(request);
			oos.flush();
			Thread.sleep(10);
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
