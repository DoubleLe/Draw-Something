package cn.edu.swu.model;




import java.awt.Point;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



public class Response implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531336482257083971L;
	public static final int LOGIN_SUCCESS = 1001;
	public static final int LOGIN_PASSWORD_ERROR = 1002;
	public static final int LOGIN_ACCOUNT_NOT = 1003;
	
	private User hostUser;
	private User guestUser;
	private Map<String, ServerList> serverListMap;
	private String tip;
	private int responseCode;
	private Map<String, Map<String, Room>> serversListMap = new HashMap<String, Map<String, Room>>();
	private int roomNumber = 13;
	private String serverListName;
	private int time_count;
	private boolean painting_YesNo;
	private boolean start_YesNo;
	private String topic_hint;
	private MyIcon image;
	private Point point;
	
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}

	
	public MyIcon getImage() {
		return image;
	}
	public void setImage(MyIcon image) {
		this.image = image;
	}
	public String getTopic_hint() {
		return topic_hint;
	}
	public void setTopic_hint(String topic_hint) {
		this.topic_hint = topic_hint;
	}
	public boolean isStart_YesNo() {
		return start_YesNo;
	}
	public void setStart_YesNo(boolean start_YesNo) {
		this.start_YesNo = start_YesNo;
	}
	public boolean isPainting_YesNo() {
		return painting_YesNo;
	}
	public void setPainting_YesNo(boolean painting_YesNo) {
		this.painting_YesNo = painting_YesNo;
	}
	public int getTime_count() {
		return time_count;
	}
	public void setTime_count(int time_count) {
		this.time_count = time_count;
	}
	public String getServerListName() {
		return serverListName;
	}
	public void setServerListName(String serverListName) {
		this.serverListName = serverListName;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Map<String, Map<String, Room>> getServersListMap() {
		return serversListMap;
	}
	public void setServersListMap(Map<String, Map<String, Room>> roomsMap) {
		Room room;
		for(Map.Entry<String, Map<String, Room>> entry : roomsMap.entrySet()){
			Map<String, Room> roomsListMap = new HashMap<String, Room>();
			for(Map.Entry<String, Room> roomM : entry.getValue().entrySet()){
				room = new Room();
				room.setPlayersUsersList(roomM.getValue().getPlayersUsersList());
				room.setHomeownersUser(roomM.getValue().getHomeownersUser());
				room.setRoomId(roomM.getValue().getRoomId());
				roomsListMap.put(roomM.getKey(), room);
			}
			serversListMap.put(entry.getKey(), roomsListMap);
		}
	}
	private String responseName;
	private int ServerListCount;
	
	public int getServerListCount() {
		return ServerListCount;
	}
	public void setServerListCount(int serverListCount) {
		ServerListCount = serverListCount;
	}
	public String getResponseName() {
		return responseName;
	}
	public void setResponseName(String responseName) {
		this.responseName = responseName;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public User getHostUser() {
		return hostUser;
	}
	public void setHostUser(User hostUser) {
		this.hostUser = hostUser;
	}
	public User getGuestUser() {
		return guestUser;
	}
	public void setGuestUser(User guestUser) {
		this.guestUser = guestUser;
	}
	public Map<String, ServerList> getServerListMap() {
		return serverListMap;
	}
	public void setServerListMap(Map<String, ServerList> serverListMap) {
		this.serverListMap = serverListMap;
	}

}
