package widget;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class PageSelectButton extends JButton{
	public PageSelectButton(String text){
		super(text);
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		setFocusPainted(false);
		setBorderPainted(false);
		
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
				PageSelectButton temp = (PageSelectButton)e.getSource();
				temp.setBackground(Color.DARK_GRAY);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				PageSelectButton temp = (PageSelectButton)e.getSource();
				temp.setBackground(new Color(90, 90, 90));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
