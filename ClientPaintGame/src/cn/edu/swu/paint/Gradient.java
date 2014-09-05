package cn.edu.swu.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Gradient extends JPanel{//ùuå”ÓA”[”√
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Color G_color_left = new Color(255,255,255);
	public Color G_color_right = new Color(0,0,0);
	
	public Gradient(){
		repaint();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setPaint( new GradientPaint( 0, 0, G_color_left, 100, 0, G_color_right, true ) );
		g2d.fill( new Rectangle2D.Double( 0, 0, 100, 25 ) );
	}
	
	public Dimension getPreferredSize(){
		return new Dimension( 100, 25 );
	}
}
