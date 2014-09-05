package cn.edu.swu.paint;

import java.awt.BasicStroke;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class SizePanLabelMonitor extends MouseAdapter{
	private int i;
	
	public SizePanLabelMonitor(int i){
		if(0==i){
			this.i = 1;
		}else if(1==i){
			this.i = 5;
		}else if(2==i){
			this.i = 10;
		}else{
			this.i = 15;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		SetPanel.number = i;
			
    	BasicStroke stroke2 = (BasicStroke) DrawPanel.stroke;
    	DrawPanel.stroke = new BasicStroke( SetPanel.number, stroke2.getEndCap(), stroke2.getLineJoin(), stroke2.getMiterLimit(), stroke2.getDashArray(), stroke2.getDashPhase() );
	}
	
	
}
