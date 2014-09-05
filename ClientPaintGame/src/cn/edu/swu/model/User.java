package cn.edu.swu.model;

import java.io.Serializable;
import java.net.Socket;

import javax.swing.ImageIcon;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2436459404270212017L;
	
	private String userId;
	private String userName;
	private String password;
	private String ip;
	private int port;
	private Socket socket;
	private ImageIcon UserHeadImg;
	
	public Socket getSocket() {
		return socket;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public ImageIcon getUserHeadImg() {
		return UserHeadImg;
	}
	public void setUserHeadImg(ImageIcon userHeadImg) {
		UserHeadImg = userHeadImg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	

}
