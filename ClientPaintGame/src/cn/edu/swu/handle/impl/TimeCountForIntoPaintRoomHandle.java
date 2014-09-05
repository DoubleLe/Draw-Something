package cn.edu.swu.handle.impl;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import cn.edu.swu.Client.TimerCountThread;
import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;

public class TimeCountForIntoPaintRoomHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		//�Ƴ��뿪���䰴ť
		RoomFrame.add_left_img.removeAll();
		RoomFrame.labelRight.remove(RoomFrame.add_left_img);//��JPanel�������붨�嶼д����ǰ�棬Ҳ���������ʱ����JPanel��������remove��
		RoomFrame.add_left_img.repaint();
		RoomFrame.labelRight.repaint();
		
		if(response.isPainting_YesNo()){
			//�Ƴ���ʼ��ť
			RoomFrame.labelLeftPanel.remove(RoomFrame.startGameLabel);
			RoomFrame.labelLeftPanel.repaint();
		}
		
		//���뻭ͼ���桪��5�뵹��ʱ
		RoomFrame.refreshLeftRoom(response, ois, oos);
		RoomFrame.freshRoomPaint(response, ois, oos);
		RoomFrame.freshRoomPaintUserHeadImg(response, ois, oos);
		RoomFrame.currentPaintUserHeadImg(response, ois, oos);
		
		TimerCountThread time_count_t = new TimerCountThread(response, ois, oos);
		Thread t_time = new Thread(time_count_t);
		t_time.start();
		
	}

}
