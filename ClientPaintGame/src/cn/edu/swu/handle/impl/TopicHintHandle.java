package cn.edu.swu.handle.impl;

import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;

public class TopicHintHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		RoomFrame.currentPaintThemeName.setText(response.getTopic_hint());
//		if(response.isPainting_YesNo()){
//			SendPicture.sendPictures(response, oos);			
//		}
	}

}
