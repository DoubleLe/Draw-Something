package cn.edu.swu.paint;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



public class ButtonMonitor implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i=0;i<DrawPanel.ButtonName.length;i++){
			if(e.getSource()==DrawPanel.jToggleButton[i]){
				DrawPanel.drawMethod=i;
				if(DrawPanel.drawMethod==0 || DrawPanel.drawMethod==1){
					DrawPanel.setPanel.pencil_add_ctrl();					
				}else{
					DrawPanel.clearPaintPanel();
					DrawPanel.jToggleButton[2].setSelected(false);
					DrawPanel.jToggleButton[1].setSelected(true);
					DrawPanel.drawMethod=1;
				}
				
				
				DrawPanel.drawPanel.repaint();
			}
		}
	}

}
