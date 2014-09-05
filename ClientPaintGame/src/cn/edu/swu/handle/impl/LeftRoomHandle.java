package cn.edu.swu.handle.impl;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;

public class LeftRoomHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		
		if(21==RoomFrame.everyServerInnerInterfaceState){
			RoomFrame.timeCountLabel.remove(RoomFrame.timeCountField);
			RoomFrame.timeCountLabel.repaint();
			RoomFrame.labelLeftPanel.remove(RoomFrame.timeCountLabel);
			RoomFrame.labelLeftPanel.repaint();
		}
		
		RoomFrame.label0bg.remove(RoomFrame.returnServerList);
		RoomFrame.freshReturnServerList(response, ois, oos);
		RoomFrame.label0bg.remove(RoomFrame.labelTipRoomName);
		RoomFrame.label0bg.repaint();
		
		if(1==RoomFrame.paint_state){
			
			RoomFrame.cancel_sketchpad();
		}
		
		RoomFrame.add_left_img.removeAll();
		RoomFrame.labelRight.remove(RoomFrame.add_left_img);//将JPanel的声明与定义都写在最前面，也就是类加载时便有JPanel这样才能remove掉
		RoomFrame.add_left_img.repaint();
		RoomFrame.labelRight.repaint();
		
		RoomFrame.addPaintUserHeadImgPanel.removeAll();
		RoomFrame.labelLeftPanel.remove(RoomFrame.addPaintUserHeadImgPanel);
		RoomFrame.labelLeftPanel.removeAll();
		RoomFrame.addPaintUserHeadImgPanel.repaint();
		RoomFrame.labelLeftPanel.repaint();
		
		RoomFrame.labelLeftPanelRightRooms.removeAll();
		RoomFrame.labelLeftPanel.remove(RoomFrame.labelLeftPanelRightRooms);
		RoomFrame.labelLeftPanel.repaint();
		RoomFrame.labelLeft.remove(RoomFrame.labelLeftPanel);
		RoomFrame.labelLeft.repaint();
		
		RoomFrame.freshRooms(response, ois, oos);
		RoomFrame.displayEveryRoom(response, ois, oos);
		
	
	}

}
