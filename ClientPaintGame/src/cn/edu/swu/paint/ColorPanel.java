package cn.edu.swu.paint;


import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import cn.edu.swu.clientFrame.RoomFrame;

public class ColorPanel extends JPanel implements MouseListener,ActionListener{//調色盤class
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private	JPanel jPanel_color0[]=new JPanel[5];
	private	JPanel jPanel_color1[]=new JPanel[20];
	private	JPanel jPanel_color2[]=new JPanel[20];
	private BufferedImage bufImg = new BufferedImage(12 ,12,BufferedImage.TYPE_3BYTE_BGR) ,bufImg2 = new BufferedImage(12 ,12,BufferedImage.TYPE_3BYTE_BGR);
	private JLabel jlbImg=new JLabel() ,jlbImg2=new JLabel();
	private JDialog jDialog;
	private JButton ok, cancel,left,right;
	private Gradient center = new Gradient();
	
	private	int rgb[][]={
			{0,255,128,192,128,255,128,255,  0,  0,  0,  0,  0,  0,128,255,128,255,  0,  0,  0,128,  0,128,128,255,128,255},
			{0,255,128,192,  0,  0,128,255,128,255,128,255,  0,  0,  0,  0,128,255, 64,255,128,255, 64,128,  0,  0, 64,128},
			{0,255,128,192,  0,  0,  0,  0,  0,  0,128,255,128,255,128,255, 64,128, 64,128,255,255,128,255,255,128,  0, 64}
		};
	
	public ColorPanel(){//產生版面//
		addMouseListener(this);
		jlbImg.setIcon(new ImageIcon(bufImg));
		jlbImg2.setIcon(new ImageIcon(bufImg2));
		
		this.setLayout(null);
		DrawPanel.color_border=new Color(0,0,0);
		DrawPanel.color_inside=null;
		
		for(int i=0;i<jPanel_color0.length;i++){
			jPanel_color0[i]=new JPanel();
			if(i<=2){
				jPanel_color0[i].setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
				jPanel_color0[i].setLayout(null);
			}
			else{
				jPanel_color0[i].setBackground(new Color(rgb[0][i-3],rgb[1][i-3],rgb[2][i-3]));
				jPanel_color0[i].setLayout(new GridLayout(1,1));
				jPanel_color0[i-2].add(jPanel_color0[i]);
			}
		}
		for(int i=0;i<jPanel_color2.length;i++){
			jPanel_color2[i]=new JPanel();
			jPanel_color2[i].setLayout(new GridLayout(1,1));
			jPanel_color2[i].setBounds(new Rectangle(2, 2, 12, 12));
			jPanel_color2[i].setBackground(new Color(rgb[0][i],rgb[1][i],rgb[2][i]));
		}
		
		for(int i=0;i<jPanel_color1.length;i++){
			jPanel_color1[i]=new JPanel();
			jPanel_color1[i].setLayout(null);
			jPanel_color1[i].add(jPanel_color2[i]);
			this.add(jPanel_color1[i]);
			
			if(i%2==0){
				jPanel_color1[i].setBounds(new Rectangle(32+i/2*16, 0, 16, 16));
			}else{
				jPanel_color1[i].setBounds(new Rectangle(32+i/2*16, 16, 16, 16));
			}
			jPanel_color1[i].setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		}
		
		jPanel_color0[3].add(jlbImg);
		jPanel_color0[4].add(jlbImg2);
		
		Graphics2D g2d = bufImg2.createGraphics();
		g2d.setPaint( Color.white );
		g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
		g2d.setPaint( Color.red ); 
		g2d.draw( new Line2D.Double( 0, 0, 12, 12 ) );
		g2d.draw( new Line2D.Double( 11, 0, 0, 11 ) );
		repaint();
		
		this.add(jPanel_color0[1]);
		this.add(jPanel_color0[2]);
		this.add(jPanel_color0[0]);

		jPanel_color0[0].setBounds(new Rectangle(0, 0, 32, 32));//外围大边框
		jPanel_color0[1].setBounds(new Rectangle(4, 4, 16, 16));//大边框中上边框
		jPanel_color0[2].setBounds(new Rectangle(12,12,16, 16));//大边框中下边框
		jPanel_color0[3].setBounds(new Rectangle(2, 2, 12, 12));//上边框颜色
		jPanel_color0[4].setBounds(new Rectangle(2, 2, 12, 12));//下边框颜色
		
		jDialog = new JDialog(RoomFrame.roomFrame, "请选择两种颜色做渐层", true);
    	jDialog.getContentPane().setLayout(new FlowLayout());
    	jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
    	jDialog.setSize(250, 110);
    	JPanel temp = new JPanel(new GridLayout(2,1));
    	JPanel up = new JPanel(new FlowLayout());
    	JPanel down = new JPanel(new FlowLayout());
    	
		ok = new JButton("確定");
		cancel = new JButton("取消");
		left = new JButton(" ");
		right = new JButton(" ");
		center.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		up.add(left);
		up.add(center);
		up.add(right);
		down.add(ok);
		down.add(cancel);
		temp.add(up);
		temp.add(down);
		jDialog.getContentPane().add(temp);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		left.addActionListener(this);
		right.addActionListener(this);
	}
	
	
	/**
	 * 颜色选择框的流式布局
	 */
	public Dimension getPreferredSize(){
		return new Dimension(200, 32);
	}
	
	public void mousePressed( MouseEvent e ){
		Graphics2D g2d;
		if(e.getX()>=5 && e.getX()<=20 && e.getY()>=5 && e.getY()<=20){
			g2d = bufImg.createGraphics();
			DrawPanel.color_border = JColorChooser.showDialog(RoomFrame.roomFrame, "请选择边线颜色", (Color)DrawPanel.color_border );
			g2d.setPaint(DrawPanel.color_border);
			g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
			repaint();
		}
		else if(e.getX()>=13 && e.getX()<=28 && e.getY()>=13 && e.getY()<=28){
			g2d = bufImg2.createGraphics();
			DrawPanel.color_inside = JColorChooser.showDialog(RoomFrame.roomFrame, "请选择填充颜色", (Color)DrawPanel.color_inside );
			g2d.setPaint(DrawPanel.color_inside);
			g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
			repaint();
		}
		
		if(!(e.getX()>=32 && e.getX()<=32*6)){
			return;
		}
		
		int choose=(e.getX()-32)/16*2+e.getY()/16;
		
		if(e.getButton()==1){//判斷填充邊框或填滿內部
			g2d = bufImg.createGraphics();			
		}
		else{
			g2d = bufImg2.createGraphics();			
		}
		
		if(choose==28){//填充無顏色
			g2d.setPaint( Color.white );
			g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
			g2d.setPaint( Color.red ); 
			g2d.draw( new Line2D.Double( 0, 0, 12, 12 ) );
			g2d.draw( new Line2D.Double( 11, 0, 0, 11 ) );
			repaint();
				
			if(e.getButton()==1)
				DrawPanel.color_border=null;
			else
				DrawPanel.color_inside=null;
		}
		else{//填充一般色
			g2d.setPaint(new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]));
			g2d.fill( new Rectangle2D.Double( 0, 0, 12, 12 ) );
			repaint();
			
			if(e.getButton()==1)
				DrawPanel.color_border=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
			else
				DrawPanel.color_inside=new Color(rgb[0][choose],rgb[1][choose],rgb[2][choose]);
		}
	}

	public void mouseReleased( MouseEvent e ){}
	public void mouseEntered( MouseEvent e ){}
	public void mouseExited( MouseEvent e ){}
	public void mouseClicked( MouseEvent e ){}
	public void actionPerformed( ActionEvent e ){}
}
