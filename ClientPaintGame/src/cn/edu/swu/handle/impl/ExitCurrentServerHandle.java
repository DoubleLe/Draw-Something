package cn.edu.swu.handle.impl;

import java.io.IOException;






import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



import cn.edu.swu.handle.ResponseHandle;
import cn.edu.swu.model.Response;


public class ExitCurrentServerHandle implements ResponseHandle {

	@Override
	public void handle(Response response, ObjectInputStream ois,
			ObjectOutputStream oos) {
		
		
		
		try {
			
			oos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
