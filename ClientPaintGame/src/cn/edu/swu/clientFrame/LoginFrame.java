package cn.edu.swu.clientFrame;

import java.awt.Image;

import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.swu.Monitor.LoginButtonMonitor;
import cn.edu.swu.Monitor.LoginFrameMouseMotionMonitor;
import cn.edu.swu.Monitor.LoginFrameMouseMoveMonitor;
import cn.edu.swu.Monitor.LoginFrameminButtonImaMonitor;
import cn.edu.swu.Monitor.LoginFrameoffButtonImaMonitor;



public class LoginFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2898844283114850972L;
	private JTextField idField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginButton = new JButton("µÇÂ¼");
	private JButton registerButton = new JButton("×¢²á");
	private JLabel userIdLabel = new JLabel("ÕÊºÅ");
	private JLabel passwordLabel = new JLabel("ÃÜÂë");
	private JTextField userNameError = new JTextField();
	private JTextField passwordError = new JTextField();
	
	public static LoginFrame loginFrame;
	public static Point firstFrame =null;
	public static Point secondFrame = null;
	public static boolean isDragged = false;
	
	public JTextField getUserNameError() {
		return userNameError;
	}



	public JTextField getPasswordError() {
		return passwordError;
	}
	
	public JTextField getIdField() {
		return idField;
	}



	public JPasswordField getPasswordField() {
		return passwordField;
	}


	public LoginFrame(){
		this.addMouseListener(new LoginFrameMouseMoveMonitor());
		this.addMouseMotionListener(new LoginFrameMouseMotionMonitor());
		
		Image icon = this.getToolkit().getImage(LoginFrame.class.getClassLoader().getResource("cn/edu/swu/img/Icon.jpg"));
		ImageIcon loginIma = new ImageIcon(LoginFrame.class.getClassLoader().getResource("cn/edu/swu/img/Login.jpg"));
		this.setIconImage(icon);
		this.setSize(loginIma.getIconWidth(), loginIma.getIconHeight());
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		this.setLayout(null);
		JLabel label0 = new JLabel();
		label0.setBounds(0, 0, loginIma.getIconWidth(), loginIma.getIconHeight());
		this.add(label0);
		
		label0.setLayout(null);
		JLabel label1Ima = new JLabel(loginIma);
		label1Ima.setBounds(0, 0, loginIma.getIconWidth(), loginIma.getIconHeight());
		label0.add(label1Ima);
		
		label1Ima.setLayout(null);
		ImageIcon offButtonImag = new ImageIcon(LoginFrame.class.getClassLoader().getResource("cn/edu/swu/img/offButton.jpg"));
		ImageIcon minButtonImag = new ImageIcon(LoginFrame.class.getClassLoader().getResource("cn/edu/swu/img/minButton.jpg"));
		
		JButton offButtonIma = new JButton(offButtonImag);
		offButtonIma.setBounds(loginIma.getIconWidth()-offButtonImag.getIconWidth(), 0, offButtonImag.getIconWidth(), offButtonImag.getIconHeight());
		offButtonIma.setToolTipText("¹Ø±Õ");
		label1Ima.add(offButtonIma);
		offButtonIma.setBorder(null);
		offButtonIma.addMouseListener(new LoginFrameoffButtonImaMonitor());
		
		JButton minButtonIma = new JButton(minButtonImag);
		minButtonIma.setBounds(loginIma.getIconWidth()-offButtonImag.getIconWidth()-minButtonImag.getIconWidth(), 0, minButtonImag.getIconWidth(), minButtonImag.getIconHeight());
		minButtonIma.setToolTipText("×îÐ¡»¯");
		label1Ima.add(minButtonIma);
		minButtonIma.setBorder(null);
		minButtonIma.addMouseListener(new LoginFrameminButtonImaMonitor());
		
		userIdLabel.setBounds(120, 110, 30, 30);
		label1Ima.add(userIdLabel);
		idField.setBounds(160, 110, 182, 30);
		label1Ima.add(idField);
		userNameError.setBounds(350, 110, 120, 30);
		userNameError.setOpaque(false);
		userNameError.setBorder(null);
		userNameError.setEditable(false);
		label1Ima.add(userNameError);
		
		passwordLabel.setBounds(120, 155, 30, 30);
		label1Ima.add(passwordLabel);
		passwordField.setBounds(160, 155, 182, 30);
		label1Ima.add(passwordField);
		passwordError.setBounds(350, 155, 120, 30);
		passwordError.setOpaque(false);
		passwordError.setBorder(null);
		passwordError.setEditable(false);
		label1Ima.add(passwordError);

		LoginButtonMonitor buttonListener1= new LoginButtonMonitor();
		loginButton.addActionListener(buttonListener1);
		loginButton.setBounds(130, 230, 60, 30);
		label1Ima.add(loginButton);
		
//		registerButtonMonitor buttonListener2 = new registerButtonMonitor();
//		registerButton.addActionListener(buttonListener2);
		registerButton.setBounds(300, 230, 60, 30);
		label1Ima.add(registerButton);
	}
	
	public static void main(String[] args) {
		loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}
}
