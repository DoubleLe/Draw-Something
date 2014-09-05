package cn.edu.swu.service.impl;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cn.edu.swu.Server.TimerThread;
import cn.edu.swu.data.ServerRecource;
import cn.edu.swu.data.ServerTool;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.WhenJoinOneTime;
import cn.edu.swu.service.ServerService;
/**
 * 为用户提供统一倒计时原始值，来倒计时以进行房主先画图
 * @author suiting
 *
 */
public class TimeCountForIntoPaintRoomService implements ServerService {

	@Override
	public void service(Request request, Socket socket) {
		// TODO Auto-generated method stub
		//记录下第一次按开始按钮时的时间
		long room_list_one_time = System.currentTimeMillis();
		WhenJoinOneTime join_time = new WhenJoinOneTime();
		join_time.setJoin_one_time(room_list_one_time);
		ServerRecource.getTimerJoinRoomOneMap().put(String.valueOf(request.getRoomNumber()*100+request.getServerListCount()), join_time);
		
		Response response = new Response();
		
		response.setGuestUser(request.getHostUser());
		response.setResponseName(request.getServiceName());
		response.setServerListCount(request.getServerListCount());
		response.setRoomNumber(request.getRoomNumber());
		response.setServersListMap(ServerRecource.getServersListMap());
		response.setTime_count(set_time_count_start(request));
		response.setHostUser(request.getHostUser());
		response.setPainting_YesNo(true);
		
		try {
			ObjectOutputStream oos = ServerRecource.getObjectOutputStream(ServerTool.getSocketKey(socket));
			oos.writeObject(response);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TimerThread ttd = new TimerThread(request);
		Thread t_time = new Thread(ttd);
		t_time.start();
	}
	
	public static int set_time_count_start(Request request){
		
		long room_list_one_time = ServerRecource.getTimerJoinRoomOneMap().get(String.valueOf(request.getRoomNumber()*100+request.getServerListCount())).getJoin_one_time();
		
		long currentTime = System.currentTimeMillis();
		int m = (int)(currentTime-room_list_one_time)/1000;
		
		return 5-m;
	}
}
