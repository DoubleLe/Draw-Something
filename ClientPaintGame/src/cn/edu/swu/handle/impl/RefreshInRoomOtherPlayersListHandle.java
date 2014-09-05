package cn.edu.swu.handle.impl;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.data.ClientResource;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.Room;

public class RefreshInRoomOtherPlayersListHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		Room room = response.getServersListMap().get(String.valueOf(response.getServerListCount())).get(String.valueOf(response.getRoomNumber()*100+response.getServerListCount()));
		
		RoomFrame.reloadwaitface(response, ois, oos);
		
		if(3==RoomFrame.everyServerInnerInterfaceState && room.getPlayersUsersList().size()>=1){
			RoomFrame.addPaintUserHeadImgPanel.removeAll();
			RoomFrame.addPaintUserHeadImgPanel.repaint();
			RoomFrame.labelLeftPanel.remove(RoomFrame.addPaintUserHeadImgPanel);
			RoomFrame.labelLeftPanel.repaint();
			RoomFrame.freshRoomPaintUserHeadImg(response, ois, oos);
		}else if(3==RoomFrame.everyServerInnerInterfaceState && 0==room.getPlayersUsersList().size()){
			RoomFrame.add_left_img.removeAll();
			RoomFrame.labelRight.remove(RoomFrame.add_left_img);//将JPanel的声明与定义都写在最前面，也就是类加载时便有JPanel这样才能remove掉
			RoomFrame.add_left_img.repaint();
			RoomFrame.labelRight.repaint();
			
			RoomFrame.addPaintUserHeadImgPanel.removeAll();
			RoomFrame.labelLeftPanel.remove(RoomFrame.addPaintUserHeadImgPanel);
			RoomFrame.labelLeftPanel.removeAll();
			RoomFrame.addPaintUserHeadImgPanel.repaint();
			RoomFrame.labelLeftPanel.repaint();
			
			if(1==RoomFrame.paint_state){
				RoomFrame.cancel_sketchpad();
			}
			
			Request request = new Request();
			request.setHostUser(response.getHostUser());
			request.setRoomNumber(response.getRoomNumber());
			request.setServerListCount(response.getServerListCount());
			request.setServiceName(ClientResource.GET_WAIT_ROOM);
			
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
