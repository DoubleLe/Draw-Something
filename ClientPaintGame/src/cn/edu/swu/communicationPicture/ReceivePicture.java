package cn.edu.swu.communicationPicture;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JLabel;

import cn.edu.swu.clientFrame.RoomFrame;
import cn.edu.swu.model.Response;

public class ReceivePicture {
	public static boolean is_img = false;
	public static void showPicture(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		if(is_img){
			RoomFrame.addpaintPanel.removeAll();
			RoomFrame.addpaintPanel.repaint();
		}
		JLabel imgLabel = new JLabel(response.getImage());
		imgLabel.setBounds(0, 0, response.getImage().getIconWidth(), response.getImage().getIconHeight());
		RoomFrame.addpaintPanel.add(imgLabel);
		is_img = true;
	}
}
