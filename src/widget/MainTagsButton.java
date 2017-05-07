package widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;

public class MainTagsButton extends JButton{
	static Vector<MainTagsButton> mainTagsButtons = new Vector<>();
	static MainTagsButton selectedButton = null;
	
	public MainTagsButton(String text){
		super(text);
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setFocusPainted(false);
		setBorderPainted(false);
		setMargin(new Insets(2, 0, 2, 0));
		mainTagsButtons.add(this);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(MainTagsButton mainTagsButton: mainTagsButtons){
					mainTagsButton.setBackground(Color.DARK_GRAY);
					mainTagsButton.setForeground(Color.WHITE);
				}
				selectedButton = (MainTagsButton)e.getSource();
				selectedButton.setBackground(new Color(230, 230, 230));
				selectedButton.setForeground(Color.BLACK);
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
					MainTagsButton temp = (MainTagsButton)e.getSource();
					temp.setBackground(Color.DARK_GRAY);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(selectedButton != e.getSource()){
					MainTagsButton temp = (MainTagsButton)e.getSource();
					temp.setBackground(new Color(90, 90, 90));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.WHITE);
		g.drawLine(0, 0, 0, getHeight()-1);
	}
}
