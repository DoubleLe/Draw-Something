package cn.edu.swu.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientResource {
	public static final String LOGIN = "Login";
	public static final String EXIT_CURRENT_SERVER = "ExitCurrentServer";
	public static final String CREAT_ROOM = "CreatRoom";
	public static final String LEFT_ROOM = "LeftRoom";
	public static final String JOIN_ONE_ROOM = "JoinOneRoom";
	public static final String TIMECOUNTINTOPAINTROOM = "TimeCountForIntoPaintRoom";
	public static final String PAINT_ROOM = "PaintRoom";
	public static final String GET_WAIT_ROOM = "GetWaitRoom";
	public static final String TOPICHINT = "TopicHint";
	public static final String PICTURETRANSMISSION = "PictureTransmission";
	
	private static final Properties PROPERTIES = new Properties();
	
	static{
		InputStream in = null;
		try {
			in = new FileInputStream(System.getProperty("user.dir")+"/src/cn/edu/swu/data/Handle.properties");
			PROPERTIES.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try{
				if(in!=null){
					in.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public static String getClassName(String responseName){
		return PROPERTIES.getProperty(responseName);
	}

}
