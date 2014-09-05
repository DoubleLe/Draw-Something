package cn.edu.swu.clientFrame;

import java.awt.Color;








import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;





import cn.edu.swu.Monitor.ClearDrawPanelMonitor;
import cn.edu.swu.Monitor.RoomFrameDisplayEveryRoomjoinButtonMonitor;
import cn.edu.swu.Monitor.RoomFrameMouseMotionMonitor;
import cn.edu.swu.Monitor.RoomFrameMouseMoveMonitor;
import cn.edu.swu.Monitor.RoomFrameReturnServerListLabelMonitor;
import cn.edu.swu.Monitor.RoomFramecreatRoomlabelMonitor;
import cn.edu.swu.Monitor.RoomFramefreshRoomleftRoomImgLabelPaintMonitor;
import cn.edu.swu.Monitor.RoomFramelabelStartMonitor;
import cn.edu.swu.Monitor.RoomFramestartGameButtonstartGameLabelMonitor;
import cn.edu.swu.model.Response;
import cn.edu.swu.model.Room;
import cn.edu.swu.model.User;
import cn.edu.swu.paint.ButtonMonitor;
import cn.edu.swu.paint.ColorPanel;
import cn.edu.swu.paint.DrawPanel;
import cn.edu.swu.paint.SetPanel;

public class RoomFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1140784514114258790L;
	public static int everyServerInnerInterfaceState = 0;//0表示服务器列表界面
	public static int paint_state = 0;//0表示猜画状态,1表示画画状态,2表示离开画图界面状态
	public static int run_state = 1110;//1110表示结束
	private static ImageIcon RoomFrameImg;
	private static ImageIcon startimg;
	private static ImageIcon creatRoomImg;
	private static ImageIcon roomsLeft;
	private static ImageIcon cutImg;
	private static ImageIcon noUserImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/NOUser.jpg"));
	private static ImageIcon waitUserImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/waitUser.jpg"));
	private static ImageIcon everyRoomImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/199x224.jpg"));
	private static ImageIcon leftRoomImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/leftRoom.jpg"));
	private static ImageIcon joinRoomImg1 = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/joinRoomImg1.jpg"));
	private static ImageIcon returnServerImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/returnServer.jpg"));
	private static ImageIcon time_bg_img = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/timeImg.jpg"));
	private static ImageIcon start_game_img = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/startGame.jpg"));
	private static ImageIcon roomsRight = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/roomsRight.jpg"));
	public static RoomFrame roomFrame;
	public static JLabel returnServerList;
	public static JLabel labelLeft;
	public static JLabel labelRight;
	public static JLabel labelStart;
	public static JLabel creatRoomlabel;
	public static JPanel labelLeftPanel;
	public static JPanel labelLeftPanelRightRooms = new JPanel();
	public static Point firstFrame =null;
	public static Point secondFrame = null;
	public static boolean isDragged = false;
	public static JLabel label0bg;
	public static JPanel waitUsersPanel;
	public static JPanel addRoomUserPanel;
	public static JTextField roomNumberField;
	public static JLabel labelTipRoomName;
	public static JLabel count_down_time_label;
	public static JLabel timeCountLabel;
	public static JTextField count_down_time_field;
	public static JTextField timeCountField = new JTextField();
	public static JPanel addPaintUserHeadImgPanel = new JPanel();
	public static JPanel add_left_img = new JPanel();
	public static JPanel addpaintPanel = new JPanel();
	public static JLabel startGameLabel;
	public static boolean is_start_game = false;
	public static JTextField currentPaintThemeName = new JTextField();
	public static JTextArea message_area = new JTextArea();
	//画图盛放工具的面板
	//0內層,1繪圖區,2工具箱,3色塊,4屬性欄
	public static JPanel jPanel1 = new JPanel();
	public static JPanel jPanel2 = new JPanel();
	public static JPanel jPanel3 = new JPanel();
	public static JPanel jPanel4 = new JPanel();
	
	public RoomFrame(Response response, ObjectOutputStream oos){
		this.addMouseListener(new RoomFrameMouseMoveMonitor());
		this.addMouseMotionListener(new RoomFrameMouseMotionMonitor());
		
		Image icon = this.getToolkit().getImage(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/Icon.jpg"));
		RoomFrameImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/950580bg.jpg"));
		roomsLeft = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/roomsLeft.jpg"));
		startimg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/start.jpg"));
		creatRoomImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/creatRoom.jpg"));
		cutImg = new ImageIcon(RoomFrame.class.getClassLoader().getResource("cn/edu/swu/img/cut.jpg"));
		
		
		
		this.setIconImage(icon);
		this.setSize(RoomFrameImg.getIconWidth(), RoomFrameImg.getIconHeight());
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		this.setLayout(null);
		label0bg = new JLabel(RoomFrameImg);
		label0bg.setBounds(0, 0, RoomFrameImg.getIconWidth(), RoomFrameImg.getIconHeight());
		this.add(label0bg);
		
		label0bg.setLayout(null);
		
		JLabel labelTipServerName = new JLabel(response.getServerListName());
		labelTipServerName.setBounds(35, 5, 60, 20);
		label0bg.add(labelTipServerName);
		
		labelLeft = new JLabel(roomsLeft);
		labelLeft.setBounds(35, 30, roomsLeft.getIconWidth(), roomsLeft.getIconHeight());
		label0bg.add(labelLeft);
		
		labelRight = new JLabel(roomsRight);
		labelRight.setBounds(35+roomsLeft.getIconWidth(), 30, roomsRight.getIconWidth(), roomsRight.getIconHeight());
		label0bg.add(labelRight);
		
		labelRight.setLayout(null);
		
		
		
	}
	/**
	 * 返回服务器列表
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void freshReturnServerList(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		currentPaintThemeName.setText("");
		returnServerList = new JLabel(returnServerImg);
		returnServerList.setBounds(0, 255-(returnServerImg.getIconHeight()/2), returnServerImg.getIconWidth(), returnServerImg.getIconHeight());
		returnServerList.addMouseListener(new RoomFrameReturnServerListLabelMonitor(response, oos));
		label0bg.add(returnServerList);
		
		label0bg.repaint();
		
	}
	
	/**
	 * 显示进入频道(服务器)后的界面显示情况
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void freshRooms(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		everyServerInnerInterfaceState = 1;//1表示进入频道内的房间列表界面
		
		labelLeft.setLayout(null);
		labelLeftPanel = new JPanel();
		labelLeftPanel.setBounds(0, 0, roomsLeft.getIconWidth(), roomsLeft.getIconHeight());
		labelLeftPanel.setOpaque(false);
		labelLeft.add(labelLeftPanel);
		
		labelLeftPanel.setLayout(null);

		
		labelStart = new JLabel(startimg);
		labelStart.setBounds(5, 10, startimg.getIconWidth(), startimg.getIconHeight());
		labelStart.addMouseListener(new RoomFramelabelStartMonitor());
		labelLeftPanel.add(labelStart);
		
		creatRoomlabel = new JLabel(creatRoomImg);
		creatRoomlabel.setBounds(5, 300, creatRoomImg.getIconWidth(), creatRoomImg.getIconHeight());
		creatRoomlabel.addMouseListener(new RoomFramecreatRoomlabelMonitor(response, oos));
		labelLeftPanel.add(creatRoomlabel);
		
		labelLeft.repaint();
		labelLeftPanel.repaint();
	}
	/**
	 * 显示每个房间的空间，并使每个房间显示已经入房间的房主和玩家的头像
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void displayEveryRoom(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		everyServerInnerInterfaceState = 1;//1表示进入频道内的房间列表界面
		
		labelLeftPanel.setLayout(null);
		
		labelLeftPanelRightRooms.setBounds(roomsLeft.getIconWidth()-600, 0, 600, roomsLeft.getIconHeight());
		labelLeftPanelRightRooms.setOpaque(false);
		labelLeftPanel.add(labelLeftPanelRightRooms);
		labelLeftPanelRightRooms.setLayout(null);
		
		JLabel[] everyRoomImgLabel = new JLabel[6];
		
		int rx = 0;
		int ry = 0;
		int loopCount = 0;
		for(int i = 0; i<6; i++){
			
			
			everyRoomImgLabel[i] = new JLabel(everyRoomImg);
			everyRoomImgLabel[i].setBounds(rx, ry, everyRoomImg.getIconWidth(), everyRoomImg.getIconHeight());
			//为JLabel设置边框的颜色
			everyRoomImgLabel[i].setBorder(BorderFactory.createLineBorder(Color.red));
			labelLeftPanelRightRooms.add(everyRoomImgLabel[i]);
			
			loopCount++;
			if(3==loopCount){
				ry += 225;
				rx = 0;
			}else{
				rx += 200;
			}
		}
		
		int roomsNumbers = 0;
		
		
		if(null!=response.getServersListMap().get(String.valueOf(response.getServerListCount()))){
			
			
			for(Map.Entry<String, Room> entrys : response.getServersListMap().get(String.valueOf(response.getServerListCount())).entrySet()){
				everyRoomImgLabel[roomsNumbers].setLayout(null);
				
				ImageIcon homeOwnerUserImg = entrys.getValue().getHomeownersUser().getUserHeadImg();
				ImageIcon homeOwnerUserImg2 = new ImageIcon(homeOwnerUserImg.getImage());
				
				
				homeOwnerUserImg2.setImage(homeOwnerUserImg2.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
				
				JLabel homeOwnerUserImgLabel = new JLabel(homeOwnerUserImg2);
				
				homeOwnerUserImgLabel.setBounds(15, 10, 60, 60);
				
				everyRoomImgLabel[roomsNumbers].add(homeOwnerUserImgLabel);
				
				List<User> playUsersList = entrys.getValue().getPlayersUsersList();
				
				JPanel[] addPlayUserImgPanel = new JPanel[6];
				JLabel[] playUserImgLabel = new JLabel[6];
				int ty = 10;
				for(int t = 0; t<6; t++){
					addPlayUserImgPanel[t] = new JPanel();
					addPlayUserImgPanel[t].setBounds(90, ty, 34, 34);
					addPlayUserImgPanel[t].setBackground(new Color(221, 201, 168));
					everyRoomImgLabel[roomsNumbers].add(addPlayUserImgPanel[t]);
					
					ty += 35;
				}
				
				
				int ht = 0;
				for(User u : playUsersList){
					addPlayUserImgPanel[ht].setLayout(null);
					ImageIcon playUserImg = u.getUserHeadImg();
					ImageIcon playUserImg1 = new ImageIcon(playUserImg.getImage());
					playUserImg1.setImage(playUserImg1.getImage().getScaledInstance(34, 34, Image.SCALE_DEFAULT));
					
					playUserImgLabel[ht] = new JLabel(playUserImg1);
					playUserImgLabel[ht].setBounds(0, 0, 34, 34);
					addPlayUserImgPanel[ht].add(playUserImgLabel[ht]);
					
					ht++;
				}
				
				JButton joinButton = new JButton(joinRoomImg1);
				
				joinButton.setBackground(Color.RED);
				joinButton.setBounds(134, 10, 30, 208);
				joinButton.addMouseListener(new RoomFrameDisplayEveryRoomjoinButtonMonitor(response, oos, joinButton, entrys.getValue().getRoomId()));
				everyRoomImgLabel[roomsNumbers].add(joinButton);
				
				JTextField roomNumField;
				Font font2=new Font("SansSerif",Font.BOLD,60);
				if(entrys.getValue().getRoomId()<10){
					roomNumField = new JTextField("0"+String.valueOf(entrys.getValue().getRoomId()));			
				}else{
					roomNumField = new JTextField(String.valueOf(entrys.getValue().getRoomId()));
				}
				roomNumField.setOpaque(false);
				roomNumField.setEditable(false);
				roomNumField.setBounds(5, 150, 80, 50);
				roomNumField.setBorder(null);
				roomNumField.setHorizontalAlignment(JTextField.CENTER);
				roomNumField.setForeground(new Color(210, 180, 140));
				roomNumField.setFont(font2);
				everyRoomImgLabel[roomsNumbers].add(roomNumField);
				
				roomsNumbers++;
			}
		}
		
		labelLeftPanelRightRooms.repaint();
		labelLeftPanel.repaint();
	}
	
	public static void reloadwaitface(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		//当当前界面为等待界面时
		if(2==RoomFrame.everyServerInnerInterfaceState){
			//删除离开房间按钮——加载离开房间按钮
			RoomFrame.add_left_img.removeAll();
			RoomFrame.labelRight.remove(RoomFrame.add_left_img);//将JPanel的声明与定义都写在最前面，也就是类加载时便有JPanel这样才能remove掉
			RoomFrame.add_left_img.repaint();
			RoomFrame.labelRight.repaint();
			RoomFrame.refreshLeftRoom(response, ois, oos);
			//删除返回服务器按钮——加载返回服务器按钮
			RoomFrame.label0bg.remove(RoomFrame.returnServerList);
			RoomFrame.freshReturnServerList(response, ois, oos);
			
			RoomFrame.freshRoom(response, ois, oos);
			RoomFrame.addRoomUser(response, ois, oos);
			RoomFrame.refreshLeftRoom(response, ois, oos);
			//移除开始按钮
			if(RoomFrame.is_start_game){
				RoomFrame.labelLeftPanel.remove(RoomFrame.startGameLabel);
				RoomFrame.labelLeftPanel.repaint();
				RoomFrame.is_start_game = false;//true表示开始按钮存在
			}
			
			if(response.isStart_YesNo()){//true表示此用户可以加载开始按钮
				RoomFrame.startGameButton(response, oos);
			}
		}
	}
	
	/**
	 * 显示进入房间后等待状态中时的界面的现实情况
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void freshRoom(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		everyServerInnerInterfaceState = 2;//2表示房间内的等待状态界面
		
		labelLeft.remove(labelLeftPanel);
		
		//离开房间按钮的建立
//		leftRoomImgLabel = new JLabel(leftRoomImg);
//		leftRoomImgLabel.setBounds(100, 30, leftRoomImg.getIconWidth(), leftRoomImg.getIconHeight());
//		leftRoomImgLabel.addMouseListener(new RoomFramefreshRoomleftRoomImgLabelPaintMonitor(response, oos));
//		labelRight.add(leftRoomImgLabel);
		
		labelLeft.setLayout(null);
		labelLeftPanel = new JPanel();
		labelLeftPanel.setBounds(0, 0, roomsLeft.getIconWidth(), roomsLeft.getIconHeight());
		labelLeftPanel.setOpaque(false);
		labelLeft.add(labelLeftPanel);
		
		labelLeftPanel.setLayout(null);
		waitUsersPanel = new JPanel();
		waitUsersPanel.setBounds(roomsLeft.getIconWidth()-roomsLeft.getIconHeight()-100, 0, roomsLeft.getIconHeight()+100, roomsLeft.getIconHeight());
		waitUsersPanel.setOpaque(false);
		labelLeftPanel.add(waitUsersPanel);
		
		waitUsersPanel.setLayout(null);
		addRoomUserPanel = new JPanel();
		addRoomUserPanel.setBounds(0, 0, roomsLeft.getIconHeight()+100, roomsLeft.getIconHeight());
		addRoomUserPanel.setOpaque(false);
		waitUsersPanel.add(addRoomUserPanel);
		
		Font font2=new Font("SansSerif",Font.BOLD,60);
		if(response.getRoomNumber()<10){
			roomNumberField = new JTextField("0"+String.valueOf(response.getRoomNumber()));			
		}else{
			roomNumberField = new JTextField(String.valueOf(response.getRoomNumber()));
		}
		roomNumberField.setOpaque(false);
		roomNumberField.setEditable(false);
		roomNumberField.setBounds(0, 50, 100, 50);
		roomNumberField.setBorder(null);
		roomNumberField.setHorizontalAlignment(JTextField.CENTER);
		roomNumberField.setForeground(new Color(210, 180, 140));
		roomNumberField.setFont(font2);
		labelLeftPanel.add(roomNumberField);
		
		labelTipRoomName = new JLabel("房间"+String.valueOf(response.getRoomNumber()));
		labelTipRoomName.setBounds(100, 5, 60, 20);
		label0bg.add(labelTipRoomName);
		
		
		
		labelLeftPanel.repaint();
		label0bg.repaint();
	}
	
	/**
	 * 开始游戏按钮
	 * @param response
	 * @param oos
	 */
	public static void startGameButton(Response response, ObjectOutputStream oos){
		everyServerInnerInterfaceState = 2;//2表示房间内的等待状态界面
		is_start_game = true;
		startGameLabel = new JLabel(start_game_img);
		startGameLabel.setBounds(5, 200, start_game_img.getIconWidth()-1, start_game_img.getIconHeight()-1);
		startGameLabel.addMouseListener(new RoomFramestartGameButtonstartGameLabelMonitor(response, oos));
		
		labelLeftPanel.add(startGameLabel);
		labelLeftPanel.repaint();
	}
//UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU	
	/**
	 * 画画所用的时间计时器
	 */
	public static void timeCountPaintin(int get_time_count){
		count_down_time_label = new JLabel(time_bg_img);
		count_down_time_label.setBounds(5, 450-30-time_bg_img.getIconHeight(), time_bg_img.getIconWidth(), time_bg_img.getIconHeight());
		labelLeftPanel.add(count_down_time_label);
		
		count_down_time_label.setLayout(null);
		
		int test_number = get_time_count;
		
		Font font2=new Font("SansSerif",Font.BOLD, 45);
		if(test_number<10){
			count_down_time_field = new JTextField("0"+String.valueOf(test_number));			
		}else{
			count_down_time_field = new JTextField(String.valueOf(test_number));
		}
		count_down_time_field.setOpaque(false);
		count_down_time_field.setEditable(false);
		count_down_time_field.setBounds(10, 10, 60, 60);
		count_down_time_field.setBorder(null);
		count_down_time_field.setHorizontalAlignment(JTextField.CENTER);
		count_down_time_field.setForeground(new Color(245, 238, 222));
		count_down_time_field.setFont(font2);
		count_down_time_label.add(count_down_time_field);
		
		labelLeftPanel.repaint();
		count_down_time_label.repaint();
	}
//UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
	
	/**
	 * 计时器，当房主开始按开始按钮时进入画图准备界面，并倒计时5秒后正式进入画图界面
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void timeCountInWaitRoom(int get_time_count){
		everyServerInnerInterfaceState = 21;  //表示房间内出现倒计时时的等待界面
		timeCountLabel = new JLabel();
		timeCountLabel.setOpaque(false);
		timeCountLabel.setBounds(210, 120, 80, 80);
		timeCountLabel.setLayout(null);
		addpaintPanel.add(timeCountLabel);
		
		int test_number = get_time_count;
		
		Font font3=new Font("SansSerif",Font.BOLD, 70);
		if(test_number<10){
			timeCountField.setText("0"+String.valueOf(test_number));
		}else{
			timeCountField.setText(String.valueOf(test_number));
		}
		timeCountField.setOpaque(false);
		timeCountField.setEditable(false);
		timeCountField.setBounds(0, 0, 80, 80);
		timeCountField.setBorder(null);
		timeCountField.setHorizontalAlignment(JTextField.CENTER);
		timeCountField.setForeground(Color.RED);
		timeCountField.setFont(font3);
		timeCountLabel.add(timeCountField);
		
		timeCountField.repaint();
		timeCountLabel.repaint();
		addpaintPanel.repaint();
		labelLeftPanel.repaint();
	}
	
	
	/**
	 * 显示进入房间后等待状态中的房主与玩家的头像
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void addRoomUser(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		everyServerInnerInterfaceState = 2;//2表示房间内的等待状态界面
		
		Room room = response.getServersListMap().get(String.valueOf(response.getServerListCount())).get(String.valueOf(response.getRoomNumber()*100+response.getServerListCount()));
		
		waitUsersPanel.remove(addRoomUserPanel);
		
		addRoomUserPanel = new JPanel();
		addRoomUserPanel.setBounds(0, 0, roomsLeft.getIconHeight()+100, roomsLeft.getIconHeight());
		addRoomUserPanel.setOpaque(false);
		waitUsersPanel.add(addRoomUserPanel);
		addRoomUserPanel.setLayout(null);
		
		JLabel cut = new JLabel(cutImg);
		cut.setBounds(0, 0, cutImg.getIconWidth(), cutImg.getIconHeight());
		addRoomUserPanel.add(cut);
		
		JLabel[] userPanel = new JLabel[8];
		int kY = 70;
		int userNum = 0;
		for(int i = 0; i<2; i++){
			int kX = 45;
			for(int j = 0; j<4; j++){
				if(7==userNum){
					userPanel[userNum] = new JLabel(noUserImg);
				}else if(0==userNum){
					userPanel[userNum] = new JLabel(room.getHomeownersUser().getUserHeadImg());
					JLabel userNameTipLabel = new JLabel(room.getHomeownersUser().getUserName());
					userNameTipLabel.setBounds(kX, kY+100+5, 100, 20);
					addRoomUserPanel.add(userNameTipLabel);
				}else{
					userPanel[userNum] = new JLabel(waitUserImg);
				}
				userPanel[userNum].setBounds(kX, kY, 100, 100);
				
				addRoomUserPanel.add(userPanel[userNum]);
				userNum++;
				kX = kX + 120;
			}
			kY += 210;
		}
		
		int playerUserNum = 1;
		int dx = 165;
		int dy = 70;
		for(User playerUser : room.getPlayersUsersList()){
			addRoomUserPanel.remove(userPanel[playerUserNum]);
			userPanel[playerUserNum] = new JLabel(playerUser.getUserHeadImg());
			JLabel play_name_tip_label = new JLabel(playerUser.getUserName());
			play_name_tip_label.setBounds(dx, dy+100+5, 100, 20);
			addRoomUserPanel.add(play_name_tip_label);
			userPanel[playerUserNum].setBounds(dx, dy, 100, 100);
			addRoomUserPanel.add(userPanel[playerUserNum]);
			
			if(3==playerUserNum){
				dy += 210;
				dx = 165;
			}else{
				dx += 120;
			}
			playerUserNum++;
		}
		
		waitUsersPanel.repaint();
	}
	
	/**
	 * 此函数用来离开当前房间
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void refreshLeftRoom(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		currentPaintThemeName.setText("");
		add_left_img.setBounds(0, 0, 240, 70);
		labelRight.add(add_left_img);
		add_left_img.setLayout(null);
		add_left_img.setOpaque(false);
	
		JLabel leftRoomImgLabel = new JLabel(leftRoomImg);
		leftRoomImgLabel.setBounds(100, 30, leftRoomImg.getIconWidth(), leftRoomImg.getIconHeight());
		leftRoomImgLabel.addMouseListener(new RoomFramefreshRoomleftRoomImgLabelPaintMonitor(response, oos, leftRoomImgLabel));
		add_left_img.add(leftRoomImgLabel);
		
		
		add_left_img.repaint();
		labelRight.repaint();
	}
	
	public static void cancel_sketchpad(){
		//移除画板
			RoomFrame.jPanel2.removeAll();
			RoomFrame.jPanel2.repaint();
			RoomFrame.jPanel3.removeAll();
			RoomFrame.jPanel3.repaint();
			RoomFrame.jPanel4.removeAll();
			RoomFrame.jPanel4.repaint();
			RoomFrame.jPanel1.remove(DrawPanel.drawPanel);
			RoomFrame.jPanel1.repaint();
			RoomFrame.addpaintPanel.remove(RoomFrame.jPanel1);
			RoomFrame.addpaintPanel.repaint();
			RoomFrame.labelLeftPanel.remove(RoomFrame.addpaintPanel);
			RoomFrame.labelLeftPanel.repaint();
			
			DrawPanel.paintPanel.removeAll();
			DrawPanel.paintPanel.repaint();
			RoomFrame.labelRight.remove(DrawPanel.paintPanel);
			RoomFrame.labelRight.repaint();
			
			RoomFrame.paint_state = 2;
	}
	/**
	 * 此函数用来加载画图面板与画图工具
	 */
	public static void paintTool(Response response,ObjectOutputStream oos){
		paint_state = 1;
		//盛放画图工具和颜色选择面板
		DrawPanel.paintPanel.setOpaque(false);
		
		DrawPanel.paintPanel.setLayout(null);
		
		DrawPanel.jLabel[0] = new JLabel();
//		for(int i=0;i<5;i++){
//			jPanel[i]=new JPanel();
//		}
		
		//铅笔和橡皮擦
		DrawPanel.buttonGroup = new ButtonGroup();
		JToolBar jToolBar=new JToolBar("工具箱",JToolBar.VERTICAL);
		DrawPanel.jToggleButton=new JToggleButton[DrawPanel.ButtonName.length];
		
		for(int i=0;i<DrawPanel.ButtonName.length;i++){
			DrawPanel.tool[i] = new ImageIcon(RoomFrame.class.getClassLoader().getResource("swu/edu/paint/"+DrawPanel.toolname[i]));
			DrawPanel.jToggleButton[i] = new JToggleButton(DrawPanel.tool[i]);
			DrawPanel.jToggleButton[i].addActionListener(new ButtonMonitor());
			DrawPanel.jToggleButton[i].setFocusable( false );
			DrawPanel.jToggleButton[i].setBorder(null);
			DrawPanel.jToggleButton[i].setOpaque(false);
			DrawPanel.buttonGroup.add(DrawPanel.jToggleButton[i]);
		}
		DrawPanel.jToggleButton[2].addMouseListener(new ClearDrawPanelMonitor(response, oos));
		jToolBar.add(DrawPanel.jToggleButton[0]);
		jToolBar.add(DrawPanel.jToggleButton[1]);
		jToolBar.add(DrawPanel.jToggleButton[2]);
		DrawPanel.jToggleButton[1].setSelected(true);
		jToolBar.setLayout(new GridLayout(1, 3, 2, 2));
		jToolBar.setFloatable(false);//無法移動
		jToolBar.setOpaque(false);
		jPanel2.add(jToolBar);
		jPanel2.setBounds(0, 110, 100, 50);//放橡皮擦和铅笔的Panel
		jPanel2.setOpaque(false);
		
		
		//画图颜色选择面板
		DrawPanel.colorPanel=new ColorPanel();
		DrawPanel.colorPanel.setOpaque(false);
		jPanel3.setBounds(0, 0, 240, 40);
		jPanel3.setOpaque(false);
		jPanel3.add(DrawPanel.colorPanel);
		
		//画图面板
		DrawPanel.drawPanel=new DrawPanel(response, oos);
		DrawPanel.drawPanel.setBounds(new Rectangle(2, 2, DrawPanel.draw_panel_width, DrawPanel.draw_panel_height));
		DrawPanel.drawPanel.repaint();
		//笔画大小或粗细选择栏
		DrawPanel.setPanel=new SetPanel();
		jPanel4.setBounds(0, 40, 240, 80);
		DrawPanel.setPanel.setOpaque(false);
		jPanel4.setOpaque(false);
		jPanel4.add(DrawPanel.setPanel);
		
		jPanel1.setBounds(0, 0, 500, 380);
		jPanel1.setLayout(new BorderLayout());
		jPanel1.add(DrawPanel.drawPanel,BorderLayout.CENTER);
		jPanel1.setBackground(Color.GREEN);
		addpaintPanel.add(jPanel1);
		
		DrawPanel.paintPanel.add(jPanel2);
		DrawPanel.paintPanel.add(jPanel3);
		DrawPanel.paintPanel.add(jPanel4);
		
		
		DrawPanel.jLabel[0].setBounds(100, 110, 240, 50);
		DrawPanel.paintPanel.add(DrawPanel.jLabel[0]);
		
		jPanel2.setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(172,168,153)));
		jPanel3.setBorder(BorderFactory.createMatteBorder(1,0,0,0,new Color(172,168,153)));
		DrawPanel.jLabel[0].setBorder(BorderFactory.createMatteBorder(0,0,1,0,new Color(172,168,153)));
		
		DrawPanel.paintPanel.setBounds(0, 70, 240, 160);
		DrawPanel.paintPanel.repaint();
		labelRight.add(DrawPanel.paintPanel);

		DrawPanel.drawPanel.repaint();
		DrawPanel.paintPanel.repaint();
		jPanel1.repaint();
		addpaintPanel.repaint();
		labelRight.repaint();
		labelLeftPanel.repaint();
		label0bg.repaint();
		RoomFrame.roomFrame.repaint();
	}
	
	public static void messageArea(Response response){
		message_area.setBounds(0, roomsRight.getIconHeight()-215, 240, 215);
		labelRight.add(message_area);
		
	}
	
	/**
	 * 此函数用来更新或显示画图界面中的画图面板
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void freshRoomPaint(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		everyServerInnerInterfaceState = 3;//3表示房间内的画图界面
		labelLeftPanel.remove(waitUsersPanel);
		labelLeftPanel.remove(roomNumberField);
		
		
		addpaintPanel.setBounds(roomsLeft.getIconWidth()-500-3, roomsLeft.getIconHeight()-380-3, 500, 380);
		addpaintPanel.setBorder(BorderFactory.createLoweredBevelBorder());
	
		labelLeftPanel.add(addpaintPanel);
		
		addpaintPanel.setLayout(null);
		addpaintPanel.setBackground(Color.WHITE);
		
		addpaintPanel.repaint();
		labelLeftPanel.repaint();
		
	}
	
	/**
	 * 此函数用来在画图界面左侧显示创建房间者的头像
	 * @param response
	 * @param ois
	 * @param oos
	 */
	
	public static void freshRoomPaintUserHeadImg(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		
		everyServerInnerInterfaceState = 3;//3表示房间内的画图界面
		
		
		addPaintUserHeadImgPanel.setBounds(0, 0, 140, 450);
		addPaintUserHeadImgPanel.setOpaque(false);
		labelLeftPanel.add(addPaintUserHeadImgPanel);
		addPaintUserHeadImgPanel.setLayout(null);
		
		
		Room room = response.getServersListMap().get(String.valueOf(response.getServerListCount())).get(String.valueOf(response.getRoomNumber()*100+response.getServerListCount()));
		
		JPanel addHomeOwnerUserPanel = new JPanel();
		JLabel HomeOwnerUserImgLabel = new JLabel(room.getHomeownersUser().getUserHeadImg());
		
		addHomeOwnerUserPanel.setBounds(30, 70, 70, 70);
		
		addPaintUserHeadImgPanel.add(addHomeOwnerUserPanel);
		
		addHomeOwnerUserPanel.setLayout(null);
		
		HomeOwnerUserImgLabel.setBounds(2, 2, 66, 66);
		
		addHomeOwnerUserPanel.add(HomeOwnerUserImgLabel);
		
		int fy = 145;
		for(User user : room.getPlayersUsersList()){
			JPanel addPlayerUserPanel = new JPanel();
			JLabel playerUserImgLabel = new JLabel(user.getUserHeadImg());
			addPlayerUserPanel.setBounds(30, fy, 70, 70);
			addPaintUserHeadImgPanel.add(addPlayerUserPanel);
			addPlayerUserPanel.setLayout(null);
			playerUserImgLabel.setBounds(2, 2, 66, 66);
			addPlayerUserPanel.add(playerUserImgLabel);
			
			fy += 75;
		}
				
		addPaintUserHeadImgPanel.repaint();
		labelLeftPanel.repaint();
	}
	
	/**
	 * 此函数用来更新正在画图者的头像和所画图像的题目
	 * @param response
	 * @param ois
	 * @param oos
	 */
	public static void currentPaintUserHeadImg(Response response, ObjectInputStream ois,
			ObjectOutputStream oos){
		everyServerInnerInterfaceState = 3;//3表示房间内的画图界面
		
		JPanel addcurrentPaintUserHeadImgPanel = new JPanel();
		addcurrentPaintUserHeadImgPanel.setBounds(530, 10, 50, 50);
		labelLeftPanel.add(addcurrentPaintUserHeadImgPanel);
		
		//重新复制一份头像，以防干扰所有使用此头像的大小
		ImageIcon currentPaintUserHeadImg = response.getHostUser().getUserHeadImg();
		ImageIcon currentPaintUserHeadImg1 = new ImageIcon(currentPaintUserHeadImg.getImage());
		
		currentPaintUserHeadImg1.setImage(currentPaintUserHeadImg1.getImage().getScaledInstance(46, 46, Image.SCALE_DEFAULT));
		JLabel currentPaintUserHeadImgLabel = new JLabel(currentPaintUserHeadImg1);
		currentPaintUserHeadImgLabel.setBounds(2, 2, 46, 46);
		addcurrentPaintUserHeadImgPanel.setLayout(null);
		addcurrentPaintUserHeadImgPanel.add(currentPaintUserHeadImgLabel);
		
		
		//所画内容题目提示
		currentPaintThemeName.setForeground(Color.RED);
		currentPaintThemeName.setBounds(310, 35, 120, 20);
		currentPaintThemeName.setBorder(null);
		currentPaintThemeName.setOpaque(false);
		labelLeftPanel.add(currentPaintThemeName);
		labelLeftPanel.repaint();
		
	}
}
