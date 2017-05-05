package widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;

public class TabButton extends JButton{
	static Vector<TabButton> tabButtons = new Vector<>();
	static TabButton selectedButton = null;
	
	private String icon = null;
	private int iconSize;
	
	public TabButton(String icon, int iconSize, String text){
		super(text);
		this.icon = icon;
		this.iconSize = iconSize;
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setFocusPainted(false);
		setBorderPainted(false);
		setFont(new Font("微软雅黑", Font.PLAIN, 14));
		setIcon(FontAwesome.getIcon(icon, iconSize, Color.WHITE));
		setMargin(new Insets(8, 0, 8, 0));
		if(tabButtons.isEmpty()){
			selectedButton = this;
		}
		tabButtons.add(this);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(TabButton tabButton: tabButtons){
					tabButton.setBackground(Color.DARK_GRAY);
					tabButton.setForeground(Color.WHITE);
					tabButton.setIcon(FontAwesome.getIcon(tabButton.icon, tabButton.iconSize, Color.WHITE));
				}
				selectedButton = (TabButton)e.getSource();
				selectedButton.setBackground(new Color(230, 230, 230));
				selectedButton.setForeground(Color.BLACK);
				selectedButton.setIcon(FontAwesome.getIcon(selectedButton.icon, selectedButton.iconSize, Color.BLACK));
			}
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				if(selectedButton != e.getSource()){
					TabButton temp = (TabButton)e.getSource();
					temp.setBackground(Color.DARK_GRAY);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(selectedButton != e.getSource()){
					TabButton temp = (TabButton)e.getSource();
					temp.setBackground(new Color(90, 90, 90));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/*public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawLine(0, 0, 0, getHeight()-1);
	}*/
}
