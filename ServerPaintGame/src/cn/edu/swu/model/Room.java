package cn.edu.swu.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4827647391308473359L;
	
	private User homeownersUser;
	private List<User> playersUsersList = new ArrayList<User>();
	private int roomId;
	
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public User getHomeownersUser() {
		return homeownersUser;
	}
	public void setHomeownersUser(User homeownersUser) {
		this.homeownersUser = homeownersUser;
	}
	public List<User> getPlayersUsersList() {
		return playersUsersList;
	}
	public void setPlayersUsersList(List<User> playersUsersList) {
		for(User user : playersUsersList){
			this.playersUsersList.add(user);
		}
	}
}
