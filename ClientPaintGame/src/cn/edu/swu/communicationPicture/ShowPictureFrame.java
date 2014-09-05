package cn.edu.swu.communicationPicture;


import java.awt.Graphics;


import javax.swing.ImageIcon;
import javax.swing.JFrame;

import cn.edu.swu.model.Response;



public class ShowPictureFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon icon;
	private static ShowPictureFrame showPictureFrame;
	private Response response;
	
	private ShowPictureFrame(){
		
		this.setAlwaysOnTop(true);
		this.setFocusable(true);
	}
	
	public synchronized static ShowPictureFrame getInstance(ImageIcon icon, Response response){
		if(showPictureFrame==null){
			showPictureFrame = new ShowPictureFrame();
		}
		if(!showPictureFrame.isUndecorated()){
			showPictureFrame.setUndecorated(true);
		}
		showPictureFrame.icon = icon;
		showPictureFrame.response = response;
		showPictureFrame.setSize(showPictureFrame.icon.getIconWidth(), showPictureFrame.icon.getIconHeight());
		return showPictureFrame;
	}
	
	public synchronized static void resert(){
		showPictureFrame = null;
	}
	
	@Override
	public void paint(Graphics arg0) {
		arg0.drawImage(showPictureFrame.icon.getImage(), (int)showPictureFrame.response.getPoint().getX(), (int)showPictureFrame.response.getPoint().getY(), 
				(int)showPictureFrame.response.getPoint().getX()+showPictureFrame.icon.getIconWidth(), (int)showPictureFrame.response.getPoint().getY()+showPictureFrame.icon.getIconHeight(), 
				(int)showPictureFrame.response.getPoint().getX(), (int)showPictureFrame.response.getPoint().getY(), 
				(int)showPictureFrame.response.getPoint().getX()+showPictureFrame.icon.getIconWidth(), (int)showPictureFrame.response.getPoint().getY()+showPictureFrame.icon.getIconHeight(), 
				showPictureFrame);
	}
	
}
