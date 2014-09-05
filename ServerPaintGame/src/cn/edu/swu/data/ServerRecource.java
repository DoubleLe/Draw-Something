package cn.edu.swu.data;

import java.io.File;






import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;

import cn.edu.swu.model.Room;
import cn.edu.swu.model.ServerList;
import cn.edu.swu.model.User;
import cn.edu.swu.model.WhenJoinOneTime;

@SuppressWarnings("unchecked")
public class ServerRecource {
	private static ImageIcon UserHeadImgsuns = new ImageIcon(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/img/constellation/suns.jpg"));
	private static ImageIcon UserHeadImgxiao = new ImageIcon(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/img/constellation/xiaojiayu.jpg"));
	private static ImageIcon xiaotuImg = new ImageIcon(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/img/constellation/xiaotu.jpg"));
	
	private static final Properties PROPERTIES = new Properties();
	private static Map<String,User> USER_MAP;
	private static final Map<String, ObjectOutputStream> OOS_MAP = new HashMap<String,ObjectOutputStream>();
	private static final Map<String, ObjectInputStream> OIS_MAP = new HashMap<String,ObjectInputStream>();
	private static final Map<String, User> ONLINE_MAP = new HashMap<String, User>();
	private static final Map<String, ServerList> SERVER_MAP = new HashMap<String, ServerList>();
	private static final Map<String, Map<String, Room>> ServersListMap = new HashMap<String, Map<String, Room>>();
	private static final Map<String, Room> Rooms_List_Map = new HashMap<String, Room>();
	private static final Map<String, List<User>> ROOM_PLAYSUSERS_MAP = new HashMap<String, List<User>>();
	private static final Map<String, List<User>> Every_Server_Users_Map = new HashMap<String, List<User>>();
	private static final Map<String, Socket> User_Socket_Map = new HashMap<String, Socket>();
	private static final Map<String, WhenJoinOneTime> Timer_Join_Room_One_Map = new HashMap<String, WhenJoinOneTime>();
	private static final String[] constellationName = new String[12];
	private static final List<String> Topic_Hint = new ArrayList<String>();

	public static List<String> getTopicHint() {
		return Topic_Hint;
	}

	
	public static Map<String, WhenJoinOneTime> getTimerJoinRoomOneMap() {
		return Timer_Join_Room_One_Map;
	}


	public static Map<String, Socket> getUserSocketMap() {
		return User_Socket_Map;
	}

	
	public static Map<String, List<User>> getEveryServerUsersMap() {
		return Every_Server_Users_Map;
	}
	
	public static Map<String, Room> getRoomsListMap() {
		return Rooms_List_Map;
	}
	
	public static String[] getConstellationname() {
		return constellationName;
	}

	public static Map<String, List<User>> getRoomPlaysusersMap() {
		return ROOM_PLAYSUSERS_MAP;
	}



	public static Map<String, Map<String, Room>> getServersListMap() {
		return ServersListMap;
	}

	private static int A_ROOM_USERS_COUNT = 0;
	
	public static int getA_ROOM_USERS_COUNT() {
		return A_ROOM_USERS_COUNT;
	}

	public static void setA_ROOM_USERS_COUNT(int a_ROOM_USERS_COUNT) {
		A_ROOM_USERS_COUNT = a_ROOM_USERS_COUNT;
	}

	
	

	public static Map<String, User> getUSER_MAP() {
		return USER_MAP;
	}
	public static Map<String, User> getOnlineMap() {
		return ONLINE_MAP;
	}
	public static Map<String, ServerList> getSERVER_MAP() {
		return SERVER_MAP;
	}
	
	static{
		Topic_Hint.add("Ïã½¶");
		Topic_Hint.add("Æ»¹û");
		Topic_Hint.add("ÊÖÇ¹");
		Topic_Hint.add("½ð¼¦¶ÀÁ¢");
		Topic_Hint.add("ÎÅ¼¦ÆðÎè");
		Topic_Hint.add("¸ßÌú");
		Topic_Hint.add("·É»ú");
		Topic_Hint.add("ÂæÍÕ");
		Topic_Hint.add("Ë«É«Çò");
		Topic_Hint.add("ÑöÓ¾");
		Topic_Hint.add("±Ê¼Ç±¾");
		Topic_Hint.add("³ÄÉÀ");
		
		
		for(int i = 0; i<12; i++){
			ServerRecource.Every_Server_Users_Map.put(String.valueOf(i), new ArrayList<User>());
		}
		
		constellationName[0] = "Ìì³Ó×ù";
		constellationName[1] = "ÉäÊÖ×ù";
		constellationName[2] = "Ë«Óã×ù";
		constellationName[3] = "°×Ñò×ù";
		constellationName[4] = "Ë®Æ¿×ù";
		constellationName[5] = "½ðÅ£×ù";
		constellationName[6] = "Ä¦ôÉ×ù";
		constellationName[7] = "Ë«×Ó×ù";
		constellationName[8] = "Ê¨×Ó×ù";
		constellationName[9] = "´¦Å®×ù";
		constellationName[10] = "ÌìÐ«×ù";
		constellationName[11] = "¾ÞÐ·×ù";
		
		ImageIcon imageIco = null;
		for(int i = 0;i<12;i++){
			ServerList serverList = new ServerList();
			imageIco = new ImageIcon(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/img/constellation/"+i+".jpg")); 
			serverList.setConstellationName(constellationName[i]);
			serverList.setImageIco(imageIco);
			serverList.setCount(i);
			SERVER_MAP.put(String.valueOf(i), serverList);
		}
		
		InputStream in = null;
		ObjectInputStream oisUser = null;
		File fioisUser;
		
		try {
			
			File fiin = new File(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/data/ServiceName.properties").toURI());
			in = new FileInputStream(fiin);
			PROPERTIES.load(in);
			
			fioisUser = new File(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/data/Users.dat").toURI());
			oisUser = new ObjectInputStream(new FileInputStream(fioisUser));
			USER_MAP = (Map<String,User>)oisUser.readObject();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					
					if(oisUser!=null){
						oisUser.close();
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public static String getClassName(String serviceName){
		return ServerRecource.PROPERTIES.getProperty(serviceName);
	}
	public static User getUser(String userId){
		return USER_MAP.get(userId);
	}
	public static void putObjectStream(Socket socket) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		String key = ServerTool.getSocketKey(socket);
		OIS_MAP.put(key, ois);
		OOS_MAP.put(key, oos);
	}
	public static ObjectInputStream getObjectInputStream(String key){
		return OIS_MAP.get(key);
	}
	public static ObjectOutputStream getObjectOutputStream(String key){
		return OOS_MAP.get(key);
	}
	public static int getConnectionCount(){
		return OIS_MAP.size();
	}
	public static void removeObjectStream(String key){
		OOS_MAP.remove(key);
		OIS_MAP.remove(key);
	}
	
	public synchronized static void addUser(User user){
		USER_MAP.put(user.getUserId(), user);
		ObjectOutputStream oosUser = null;
		
		File filet;
		try {
			
			filet = new File(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/data/Users.dat").toURI());
			oosUser = new ObjectOutputStream(new FileOutputStream(filet));
			oosUser.writeObject(USER_MAP);
			oosUser.flush();
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(oosUser!= null){
					oosUser.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		OutputStream out;
		try {
			File file = new File(ServerRecource.class.getClassLoader().getResource("cn/edu/swu/data/Users.dat").toURI());
			out = new FileOutputStream(file);
			ObjectOutputStream os = new ObjectOutputStream(out);
			Map<String,User> map = new HashMap<String, User>();
			
			User user = new User();
			user.setUserId("10000");
			user.setUserName("Ð¡¼×Óã");
			user.setPassword("111");
			user.setUserHeadImg(UserHeadImgxiao);
			
			map.put(user.getUserId(), user);
			
			User user1 = new User();
			user1.setUserId("11111");
			user1.setUserName("suns");
			user1.setPassword("111");
			user1.setUserHeadImg(UserHeadImgsuns);
			
			map.put(user1.getUserId(), user1);
			
			User user2 = new User();
			user2.setUserId("22222");
			user2.setUserName("xiaotu");
			user2.setPassword("111");
			user2.setUserHeadImg(xiaotuImg);
			
			map.put(user2.getUserId(), user2);
			
			os.writeObject(map);
			os.flush();
			os.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			System.out.println(USER_MAP.get("10000").getUserName());
			
			for(int j = 0;j<12;j++){
				System.out.println(SERVER_MAP.get(String.valueOf(j)).getConstellationName());
			}
		}
	}
}
