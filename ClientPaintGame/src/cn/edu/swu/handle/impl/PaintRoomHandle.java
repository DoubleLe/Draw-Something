package cn.edu.swu.handle.impl;

import java.io.IOException;
import java.io.ObjectInputStream;



import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.data.ClientResource;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;

public class PaintRoomHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		//移除进行画画倒计时器
		RoomFrame.timeCountLabel.removeAll();
		RoomFrame.timeCountLabel.repaint();
		RoomFrame.labelLeftPanel.remove(RoomFrame.timeCountLabel);
		RoomFrame.labelLeftPanel.repaint();
		
		
		RoomFrame.refreshLeftRoom(response, ois, oos);
		RoomFrame.freshRoomPaint(response, ois, oos);
		
		
		RoomFrame.freshRoomPaintUserHeadImg(response, ois, oos);
		RoomFrame.currentPaintUserHeadImg(response, ois, oos);
		
		if(response.isPainting_YesNo()){
			RoomFrame.paintTool(response, oos);//家在画图工具:当response.isPainting_YesNo()为真时加载画板
			RoomFrame.roomFrame.dispose();
			RoomFrame.roomFrame.setVisible(true);
			
//			RoomFrame.messageArea(response);
			
			Request request = new Request();
			request.setHostUser(response.getHostUser());
			request.setPainting_YesNo(response.isPainting_YesNo());
			request.setRoomNumber(response.getRoomNumber());
			request.setServerListCount(response.getServerListCount());
			request.setStart_YesNo(response.isStart_YesNo());
			request.setServiceName(ClientResource.TOPICHINT);
			
			try {
				oos.writeObject(request);
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
