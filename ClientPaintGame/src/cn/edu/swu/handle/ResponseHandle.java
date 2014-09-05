package cn.edu.swu.handle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cn.edu.swu.model.Response;


public interface ResponseHandle {
	public void handle(Response response,ObjectInputStream ois,ObjectOutputStream oos);
}
