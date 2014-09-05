package cn.edu.swu.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;

import cn.edu.swu.model.Response;

public class SendPictureThread implements Runnable{
	private Response response;
	private ObjectOutputStream oos;
	
	public SendPictureThread(Response response, ObjectOutputStream oos){
		this.response = response;
		this.oos = oos;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			oos.writeObject(response);
			oos.flush();
			oos.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
