package cn.edu.swu.paint;



import java.awt.Color;


import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ObjectOutputStream;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import cn.edu.swu.communicationPicture.SendPicture;
import cn.edu.swu.model.Response;



public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, ItemListener, ActionListener, ChangeListener{//���뮋��
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage bufImg;//ӛ����®��棬�K�ڴ�������
	public static BufferedImage bufImg_data[];//ӛ����Ю����D�棬����ֵԽ��Խ�£���������
	private JLabel jlbImg;
	private int x1=-1,y1=-1;
	public static int count;
	private int x2, y2;
	private Line2D.Double line2D = new Line2D.Double();//ֱ��
	public String filename;
	public int pie_shape=Arc2D.PIE;
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//�����ǻ�ͼ�������õ��ı���
	public static ButtonGroup buttonGroup;
	public static String toolname[]={"img/tool6.gif","img/tool7.gif","img/clear.jpg"};
	public static Icon tool[]=new ImageIcon[11];
	public static ColorPanel colorPanel;
	
	public static JLabel jLabel[]=new JLabel[1];//��B��
	public static JToggleButton jToggleButton[];
	public static String ButtonName[]={"Ǧ��","��Ƥ��","���"};
	public static int i,show_x,show_y,drawMethod=1,draw_panel_width=500,draw_panel_height=380;
	public static Paint color_border,color_inside;
	public static SetPanel setPanel;
	public static Stroke stroke;
	public static Shape shape;
	public static DrawPanel drawPanel;
	public static JPanel paintPanel = new JPanel();
	private Response response;
	private ObjectOutputStream oos;
//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	public DrawPanel(Response response,ObjectOutputStream oos) {
		this.response = response;
		this.oos = oos;
		
		bufImg_data = new BufferedImage[1000];
		bufImg = new BufferedImage(DrawPanel.draw_panel_width, DrawPanel.draw_panel_height,BufferedImage.TYPE_3BYTE_BGR);
		jlbImg = new JLabel(new ImageIcon(bufImg));//��JLabel�Ϸ���bufImg���Á��L�D

		this.setLayout(null);
		this.add(jlbImg);
		this.setBackground(Color.ORANGE);
		jlbImg.setBounds(new Rectangle(0, 0, DrawPanel.draw_panel_width, DrawPanel.draw_panel_height));
		
		clearPaintPanel();
		repaint();
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public static void clearPaintPanel(){
		//�����װ�
		Graphics2D g2d_bufImg = (Graphics2D) bufImg.getGraphics();
		g2d_bufImg.setPaint(Color.WHITE);
		g2d_bufImg.fill(new Rectangle2D.Double(0,0,DrawPanel.draw_panel_width,DrawPanel.draw_panel_height));
		
		bufImg_data[count] = new BufferedImage(DrawPanel.draw_panel_width, DrawPanel.draw_panel_height, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g2d_bufImg_data = (Graphics2D) bufImg_data[count].getGraphics();
		g2d_bufImg_data.drawImage(bufImg,0,0, DrawPanel.drawPanel);
	}
	
	public void mousePressed(MouseEvent e) {
			
		SendPicture.isLoop = false;
		SendPicture.sendPictures(response, oos);			
		
		x1=e.getX();
		y1=e.getY();
	}

	
	
	
	
	//���ߴ��ػ滭
	public void mouseDragged(MouseEvent e){
		x2=e.getX();
		y2=e.getY();
		if(DrawPanel.drawMethod==0 || DrawPanel.drawMethod==1){
			draw(x1,y1,x2,y2);
			x1=e.getX();
			y1=e.getY();
		}
		repaint();
	}

	
	
	public void draw(int input_x1,int input_y1,int input_x2,int input_y2){

		if(DrawPanel.drawMethod==0 || DrawPanel.drawMethod==1){//���⾀����Ƥ��
			Graphics2D g2d_bufImg = (Graphics2D) bufImg.getGraphics();
			
			DrawPanel.shape=line2D;
			line2D.setLine(input_x1,input_y1,input_x2,input_y2);
			
			if(1==DrawPanel.drawMethod){
				g2d_bufImg.setPaint(DrawPanel.color_border);				
			}else if(0==DrawPanel.drawMethod){
				g2d_bufImg.setPaint(Color.white);				
			}
			
			g2d_bufImg.setStroke(DrawPanel.stroke);//�����ʻ���ϸ
			g2d_bufImg.draw(DrawPanel.shape);//�����ʻ���״����ʾ
		}

	}
	
	//�����ı����ָ�����״
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paint(g2d);//���L�׌�JPanel�Լ���������Ԫ��

		//���S�Θ˵ĈA��//
		if(DrawPanel.drawMethod==0 || DrawPanel.drawMethod==1){
			if(1==DrawPanel.drawMethod){
				Toolkit tk = Toolkit.getDefaultToolkit(); 
			    Image img = tk.getImage(DrawPanel.class.getClassLoader().getResource("swu/edu/paint/img/tool7.gif")); 
			    Cursor dynamiteCursor = tk.createCustomCursor(img, new Point(10, 25), "dynamite stick "); 
			    setCursor(dynamiteCursor); 
			}else{
				Toolkit tk = Toolkit.getDefaultToolkit(); 
			    Image img = tk.getImage(DrawPanel.class.getClassLoader().getResource("swu/edu/paint/img/tool6.gif")); 
			    Cursor dynamiteCursor = tk.createCustomCursor(img, new Point(10, 25), "dynamite stick "); 
			    setCursor(dynamiteCursor); 
			}
		}
	}
	
	public void actionPerformed( ActionEvent e ){}
	public void itemStateChanged( ItemEvent e ){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent e) {
		SendPicture.isLoop = true;
	}
	public void mouseMoved(MouseEvent e) {}
	public void stateChanged(ChangeEvent e){}
}



