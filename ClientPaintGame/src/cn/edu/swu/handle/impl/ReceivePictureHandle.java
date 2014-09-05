package cn.edu.swu.handle.impl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.edu.swu.communicationPicture.ReceivePicture;
import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;

public class ReceivePictureHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		// TODO Auto-generated method stub
		ReceivePicture.showPicture(response, ois, oos);
	}

}
