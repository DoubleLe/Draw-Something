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
		//移除离开房间按钮
		RoomFrame.add_left_img.removeAll();
		RoomFrame.labelRight.remove(RoomFrame.add_left_img);//将JPanel的声明与定义都写在最前面，也就是类加载时便有JPanel这样才能remove掉
		RoomFrame.add_left_img.repaint();
		RoomFrame.labelRight.repaint();
		
		if(response.isPainting_YesNo()){
			//移除开始按钮
			RoomFrame.labelLeftPanel.remove(RoomFrame.startGameLabel);
			RoomFrame.labelLeftPanel.repaint();
		}
		
		//进入画图界面——5秒倒计时
		RoomFrame.refreshLeftRoom(response, ois, oos);
		RoomFrame.freshRoomPaint(response, ois, oos);
		RoomFrame.freshRoomPaintUserHeadImg(response, ois, oos);
		RoomFrame.currentPaintUserHeadImg(response, ois, oos);
		
		TimerCountThread time_count_t = new TimerCountThread(response, ois, oos);
		Thread t_time = new Thread(time_count_t);
		t_time.start();
		
	}

}
