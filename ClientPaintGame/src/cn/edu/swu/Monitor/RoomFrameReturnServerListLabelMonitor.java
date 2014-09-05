package cn.edu.swu.Monitor;

import java.awt.Cursor;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;


import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.clientFrame.ServerListFrame;
import cn.edu.swu.data.ClientResource;
import cn.edu.swu.model.Request;
import cn.edu.swu.model.Response;

public class RoomFrameReturnServerListLabelMonitor extends MouseAdapter{
	private Response response;
	private ObjectOutputStream oos;
	
	public RoomFrameReturnServerListLabelMonitor(Response response, ObjectOutputStream oos){
		this.response = response;
		this.oos = oos;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		RoomFrame.returnServerList.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(1==e.getClickCount()){
			Request request = new Request();
			request.setHostUser(response.getHostUser());
			request.setServerListCount(response.getServerListCount());
			request.setServiceName(ClientResource.EXIT_CURRENT_SERVER);
			request.setRoomNumber(response.getRoomNumber());
			
			if(21==RoomFrame.everyServerInnerInterfaceState){
				RoomFrame.timeCountLabel.remove(RoomFrame.timeCountField);
				RoomFrame.timeCountLabel.repaint();
				RoomFrame.labelLeftPanel.remove(RoomFrame.timeCountLabel);
				RoomFrame.labelLeftPanel.repaint();
			}
			
			if(3==RoomFrame.everyServerInnerInterfaceState){
				RoomFrame.add_left_img.removeAll();
				RoomFrame.labelRight.remove(RoomFrame.add_left_img);//将JPanel的声明与定义都写在最前面，也就是类加载时便有JPanel这样才能remove掉
				RoomFrame.add_left_img.repaint();
				RoomFrame.labelRight.repaint();
				
				RoomFrame.addPaintUserHeadImgPanel.removeAll();
				RoomFrame.labelLeftPanel.remove(RoomFrame.addPaintUserHeadImgPanel);
				RoomFrame.labelLeftPanel.removeAll();
				RoomFrame.addPaintUserHeadImgPanel.repaint();
				RoomFrame.labelLeftPanel.repaint();
			}
			
			if(1==RoomFrame.paint_state){
				
				RoomFrame.cancel_sketchpad();
			}
			
			RoomFrame.labelLeftPanelRightRooms.removeAll();
			RoomFrame.roomFrame.dispose();
			RoomFrame.everyServerInnerInterfaceState = 0; //服务器列表界面
			RoomFrame.run_state = 1110;
			
			ServerListFrame.serverListFrame.setVisible(true);
			
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
