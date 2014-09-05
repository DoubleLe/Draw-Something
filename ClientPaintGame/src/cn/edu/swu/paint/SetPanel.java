package cn.edu.swu.paint;

import java.awt.BasicStroke;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;



public class SetPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ImageIcon min_img = new ImageIcon(SetPanel.class.getClassLoader().getResource("swu/edu/paint/img/1.jpg"));
	private ImageIcon middle_min_img = new ImageIcon(SetPanel.class.getClassLoader().getResource("swu/edu/paint/img/5.jpg"));
	private ImageIcon middle_max_img = new ImageIcon(SetPanel.class.getClassLoader().getResource("swu/edu/paint/img/15.jpg"));
	private ImageIcon max_img = new ImageIcon(SetPanel.class.getClassLoader().getResource("swu/edu/paint/img/30.jpg"));
	
	private JToggleButton placed_img_button[] = new JToggleButton[4];
	private ButtonGroup buttonGroup;
	
	private	JPanel jPanel_set1=new JPanel();
	public static int number=5;
    
	public SetPanel(){//產生版面//
		this.setLayout(null);
		this.add(jPanel_set1);

		jPanel_set1.setLayout(new FlowLayout());
		jPanel_set1.setBounds(new Rectangle(0, 0, 180, 50));
		jPanel_set1.setOpaque(false);
  
		placed_img_button[0] = new JToggleButton(min_img);
		placed_img_button[1] = new JToggleButton(middle_min_img);
		placed_img_button[2] = new JToggleButton(middle_max_img);
		placed_img_button[3] = new JToggleButton(max_img);
		
		buttonGroup = new ButtonGroup();
		JToolBar jToolBar=new JToolBar("工具箱",JToolBar.VERTICAL);
		for(int j = 0; j<4; j++){
			placed_img_button[j].setFocusable( false );
			placed_img_button[j].addMouseListener(new SizePanLabelMonitor(j));
			buttonGroup.add(placed_img_button[j]);
			jToolBar.add(placed_img_button[j]);
		}
		placed_img_button[1].setSelected(true);
		jToolBar.setLayout(new GridLayout(1, 4, 0, 0));
		jToolBar.setFloatable(false);//無法移動
		jToolBar.setOpaque(false);
		jPanel_set1.add(jToolBar);
		

		DrawPanel.stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
	}
	
	public void pencil_add_ctrl(){
		BasicStroke stroke2 = (BasicStroke)DrawPanel.stroke;
		DrawPanel.stroke = new BasicStroke(stroke2.getLineWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(180, 50);
	}
}
