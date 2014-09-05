package cn.edu.swu.handle.impl;

import java.io.ObjectInputStream;


import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;

public class CreatRoomHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		
		RoomFrame.label0bg.remove(RoomFrame.returnServerList);
		RoomFrame.freshReturnServerList(response, ois, oos);
		RoomFrame.freshRoom(response, ois, oos);
		RoomFrame.addRoomUser(response, ois, oos);
		RoomFrame.refreshLeftRoom(response, ois, oos);
		
	}

}
