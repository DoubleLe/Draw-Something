package cn.edu.swu.model;




import java.awt.Point;
import java.io.Serializable;



public class Request implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5956589536573292200L;
	
	private User hostUser;
	private User guestUser;
	private String ServiceName;
	private int ServerListCount;
	private int competitionNumber;
	private int roomNumber;
	private boolean painting_YesNo;
	private boolean start_YesNo;
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
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getCompetitionNumber() {
		return competitionNumber;
	}
	public void setCompetitionNumber(int competitionNumber) {
		this.competitionNumber = competitionNumber;
	}
	public int getServerListCount() {
		return ServerListCount;
	}
	public void setServerListCount(int serverListCount) {
		ServerListCount = serverListCount;
	}
	public String getServiceName() {
		return ServiceName;
	}
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
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

}
