package cn.edu.swu.Monitor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cn.edu.swu.clientFrame.AskCreatRoomDialog;

public class AskCreatRoomDialognumberTextFieldMonitor extends MouseAdapter{
	private int count;
	
	public AskCreatRoomDialognumberTextFieldMonitor(int count){
		this.count = count;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(1==e.getClickCount()){
			Font font1=new Font("SansSerif",Font.BOLD,18);
			for(int j = 1; j<7;j++){
				AskCreatRoomDialog.numberTextField[j].setFont(font1);
				AskCreatRoomDialog.numberTextField[j].setForeground(Color.BLACK);
			}
			
			Font font2=new Font("SansSerif",Font.BOLD,30);
			AskCreatRoomDialog.numberTextField[count].setFont(font2);
			AskCreatRoomDialog.numberTextField[count].setForeground(Color.RED);
			AskCreatRoomDialog.joinNumber = count+1;
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		AskCreatRoomDialog.numberTextField[count].setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

}
