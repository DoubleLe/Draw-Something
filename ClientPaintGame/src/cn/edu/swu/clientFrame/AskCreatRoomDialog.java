package cn.edu.swu.clientFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.swu.Monitor.AskCreatRoomDialogNoPanelMonitor;
import cn.edu.swu.Monitor.AskCreatRoomDialogYesPanelMonitor;
import cn.edu.swu.Monitor.AskCreatRoomDialognumberTextFieldMonitor;
import cn.edu.swu.model.Response;


public class AskCreatRoomDialog extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5351809962317807802L;
	public static JPanel yesPanel;
	public static JPanel noPanel;
	public static AskCreatRoomDialog askCreatRoomDialog;
	public static JTextField[] numberTextField = new JTextField[7];
	public static int joinNumber;
	
	public AskCreatRoomDialog(Response response, ObjectOutputStream oos){
		Image icon = this.getToolkit().getImage(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/Icon.jpg"));
		ImageIcon askCreatRoomImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/askCreat.jpg"));
		ImageIcon yesNoImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/YN.jpg"));
		
		this.setIconImage(icon);
		this.setSize(askCreatRoomImg.getIconWidth(), askCreatRoomImg.getIconHeight()+yesNoImg.getIconHeight());
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		this.setLayout(null);
		JLabel topLabel = new JLabel(askCreatRoomImg);
		topLabel.setBounds(0, 0, askCreatRoomImg.getIconWidth(), askCreatRoomImg.getIconHeight());
		this.add(topLabel);
		
		JLabel underLabel = new JLabel(yesNoImg);
		underLabel.setBounds(0, askCreatRoomImg.getIconHeight(), yesNoImg.getIconWidth(), yesNoImg.getIconHeight());
		this.add(underLabel);
		
		underLabel.setLayout(null);
		yesPanel = new JPanel();
		yesPanel.setBounds(68, 12, 75, 25);
		yesPanel.setOpaque(false);
		yesPanel.addMouseListener(new AskCreatRoomDialogYesPanelMonitor(response, oos));
		underLabel.add(yesPanel);
		
		noPanel = new JPanel();
		noPanel.setBounds(170, 12, 75, 25);
		noPanel.setOpaque(false);
		noPanel.addMouseListener(new AskCreatRoomDialogNoPanelMonitor());
		underLabel.add(noPanel);
		
		topLabel.setLayout(null);
		
		JLabel numberTipLabel = new JLabel("比赛人数：");
		numberTipLabel.setBounds(130, 25, 80, 15);
		topLabel.add(numberTipLabel);
		
		int k = 70;
		//设置JTextField中所要显示字体的属性(字体，字形，大小)
		Font font1=new Font("SansSerif",Font.BOLD,18);
		for(int i = 1; i<7; i++){
			numberTextField[i] = new JTextField(String.valueOf(i+1));
			numberTextField[i].setBounds(k, 40, 30, 30);
			numberTextField[i].setOpaque(false);
			numberTextField[i].setBorder(null);
			numberTextField[i].setEditable(false);
			numberTextField[i].setHorizontalAlignment(JTextField.CENTER);//设置JTextField中的字体居中显示
			if(6==i){
				Font font2=new Font("SansSerif",Font.BOLD,30);
				AskCreatRoomDialog.numberTextField[6].setFont(font2);
				AskCreatRoomDialog.numberTextField[6].setForeground(Color.RED);
				AskCreatRoomDialog.joinNumber = 7;
			}else{
				numberTextField[i].setFont(font1);
			}
			numberTextField[i].addMouseListener(new AskCreatRoomDialognumberTextFieldMonitor(i));
			k = k+30;
			topLabel.add(numberTextField[i]);
		}
		
	}
}
