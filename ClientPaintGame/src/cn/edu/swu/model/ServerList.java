package cn.edu.swu.model;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class ServerList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1862318614042594180L;
	private ImageIcon imageIco;
	private String constellationName;
	private int count;
	public ImageIcon getImageIco() {
		return imageIco;
	}
	public void setImageIco(ImageIcon imageIco) {
		this.imageIco = imageIco;
	}
	public String getConstellationName() {
		return constellationName;
	}
	public void setConstellationName(String constellationName) {
		this.constellationName = constellationName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
