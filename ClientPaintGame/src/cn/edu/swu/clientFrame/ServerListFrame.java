package cn.edu.swu.clientFrame;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.swu.Monitor.ServerListFrameMouseMotionMonitor;
import cn.edu.swu.Monitor.ServerListFrameMouseMoveMonitor;
import cn.edu.swu.Monitor.ServerListFrameminButtonImaMonitor;
import cn.edu.swu.Monitor.ServerListFrameoffButtonImaMonitor;
import cn.edu.swu.Monitor.ServerListLabelMonitor;
import cn.edu.swu.model.Response;

public class ServerListFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3544784962675127154L;
	private static ImageIcon serverListFrameImg;
	public static JPanel jpServerListLabel = new JPanel();
	public static ServerListFrame serverListFrame;
	public static JLabel[] labelList = new JLabel[12];
	public static Point firstFrame =null;
	public static Point secondFrame = null;
	public static boolean isDragged = false;
	
	public ServerListFrame(){
		this.addMouseListener(new ServerListFrameMouseMoveMonitor());
		this.addMouseMotionListener(new ServerListFrameMouseMotionMonitor());
		
		Image icon = this.getToolkit().getImage(ServerListFrame.class.getClassLoader().getResource("cn/edu/swu/img/Icon.jpg"));
		serverListFrameImg = new ImageIcon(ServerListFrame.class.getClassLoader().getResource("cn/edu/swu/img/950580bg.jpg"));
		
		
		this.setIconImage(icon);
		this.setSize(serverListFrameImg.getIconWidth(), serverListFrameImg.getIconHeight());
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		this.setLayout(null);
		JLabel label0bg = new JLabel(serverListFrameImg);
		label0bg.setBounds(0, 0, serverListFrameImg.getIconWidth(), serverListFrameImg.getIconHeight());
		this.add(label0bg);
		
		label0bg.setLayout(null);
		ImageIcon offButtonImag = new ImageIcon(ServerListFrame.class.getClassLoader().getResource("cn/edu/swu/img/ServerListFrameOff.jpg"));
		ImageIcon minButtonImag = new ImageIcon(ServerListFrame.class.getClassLoader().getResource("cn/edu/swu/img/ServerListFrameMin.jpg"));
		
		JButton offButtonIma = new JButton(offButtonImag);
		offButtonIma.setBounds(serverListFrameImg.getIconWidth()-offButtonImag.getIconWidth(), 0, offButtonImag.getIconWidth(), offButtonImag.getIconHeight());
		offButtonIma.setToolTipText("关闭");
		label0bg.add(offButtonIma);
		offButtonIma.setBorder(null);
		offButtonIma.addMouseListener(new ServerListFrameoffButtonImaMonitor());
		
		JButton minButtonIma = new JButton(minButtonImag);
		minButtonIma.setBounds(serverListFrameImg.getIconWidth()-offButtonImag.getIconWidth()-minButtonImag.getIconWidth(), 0, minButtonImag.getIconWidth(), minButtonImag.getIconHeight());
		minButtonIma.setToolTipText("最小化");
		label0bg.add(minButtonIma);
		minButtonIma.setBorder(null);
		minButtonIma.addMouseListener(new ServerListFrameminButtonImaMonitor());
		
		jpServerListLabel.setOpaque(false);
		jpServerListLabel.setBounds(0, 0, serverListFrameImg.getIconWidth(), serverListFrameImg.getIconHeight());
		label0bg.add(jpServerListLabel);
		
	}
	
	public static void freshServerList(Response response){
		ImageIcon tip = new ImageIcon(ServerListFrame.class.getClassLoader().getResource("cn/edu/swu/img/tip.jpg"));
		
		jpServerListLabel.setLayout(null);
		JLabel tipLabel = new JLabel(tip);
		tipLabel.setBounds(250, 90, tip.getIconWidth(), tip.getIconHeight());
		
		for(int ct = 0; ct<12; ct++){
			labelList[ct] = new JLabel("  "+response.getServerListMap().get(String.valueOf(ct)).getConstellationName(), response.getServerListMap().get(String.valueOf(ct)).getImageIco(),JLabel.LEFT);
			labelList[ct].addMouseListener(new ServerListLabelMonitor(ct));
		}
		
		
		int county = 120;
		int k = 0;
		for(int i = 0; i<3; i++){
			int countx = 30;
			for(int j = 0; j<4; j++){
				labelList[k].setBounds(countx, county, 130+70, response.getServerListMap().get(String.valueOf(k)).getImageIco().getIconHeight());
				jpServerListLabel.add(labelList[k]);
				countx = countx+100+130;
				k++;
			}
			county = county+140;
		}
		jpServerListLabel.add(tipLabel);
		
		jpServerListLabel.repaint();
	}
}
