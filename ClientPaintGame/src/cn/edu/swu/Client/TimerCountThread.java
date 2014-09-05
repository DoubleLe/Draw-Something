package cn.edu.swu.Client;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;


public class TimerCountThread implements Runnable{

	private Response response;
	private ObjectOutputStream oos;
	
	public TimerCountThread(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		this.response = response;
		this.oos = oos;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long room_list_one_time = System.currentTimeMillis();
		int time_count =  response.getTime_count();
		
		RoomFrame.run_state = 1111; //1111表示开始或进行中
		RoomFrame.timeCountInWaitRoom(time_count);
		
		while(time_count>0 && 1111==RoomFrame.run_state){
			long currentTime = System.currentTimeMillis();
			
			if(1==(currentTime-room_list_one_time)/1000){
				
				time_count--;
				RoomFrame.timeCountField.setText("");
				RoomFrame.timeCountLabel.remove(RoomFrame.timeCountField);
				RoomFrame.timeCountLabel.repaint();
				
				RoomFrame.addpaintPanel.remove(RoomFrame.timeCountLabel);
				RoomFrame.addpaintPanel.repaint();
				
				RoomFrame.timeCountInWaitRoom(time_count);
				
				room_list_one_time = currentTime;
			}
		}
		
		if(1111==RoomFrame.run_state){
			
			Request request = new Request();
			request.setHostUser(response.getHostUser());
			request.setRoomNumber(response.getRoomNumber());
			request.setServerListCount(response.getServerListCount());
			request.setServiceName(ClientResource.PAINT_ROOM);
			request.setPainting_YesNo(response.isPainting_YesNo());
			
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
