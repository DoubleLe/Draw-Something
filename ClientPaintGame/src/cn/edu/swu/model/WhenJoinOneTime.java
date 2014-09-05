package cn.edu.swu.model;

import java.io.Serializable;

public class WhenJoinOneTime implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6071819090594680803L;
	
	private long join_one_time;

	public long getJoin_one_time() {
		return join_one_time;
	}

	public void setJoin_one_time(long join_one_time) {
		this.join_one_time = join_one_time;
	}

}
