package cn.edu.swu.handle.impl;

import java.io.ObjectInputStream;


import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.clientFrame.ServerListFrame;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;

public class Show_Room_CountHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		
		RoomFrame.roomFrame = new RoomFrame(response, oos);
		RoomFrame.freshReturnServerList(response, ois, oos);
		RoomFrame.freshRooms(response, ois, oos);
		RoomFrame.displayEveryRoom(response, ois, oos);
		ServerListFrame.serverListFrame.dispose();
		RoomFrame.roomFrame.setVisible(true);
		
	}
}
