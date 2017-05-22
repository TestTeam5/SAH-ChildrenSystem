package widget;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;

public class TagButtonGroup {
	private Vector<JButton> buttons = new Vector<>();
	
	private JButton selectedButton = null;
	
	// 设置再次点击是否可以取消选择
	private boolean canReSelected = false;
	
	public void add(JButton button){
		buttons.add(button);
		
		button.setForeground(Color.WHITE);
		button.setBackground(Color.DARK_GRAY);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setMargin(new Insets(2, 0, 2, 0));
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(JButton temp: buttons){
					temp.setBackground(Color.DARK_GRAY);
					temp.setForeground(Color.WHITE);
				}
				// 可以取消选择的情况，需要先判断点击按钮是否已被点击
				if(canReSelected){
					if(selectedButton != (JButton)e.getSource()){
						selectedButton = (JButton)e.getSource();
						selectedButton.setBackground(new Color(230, 230, 230));
						selectedButton.setForeground(Color.BLACK);
					}else{
						selectedButton = null;
					}
				}else{
					selectedButton = (JButton)e.getSource();
					selectedButton.setBackground(new Color(230, 230, 230));
					selectedButton.setForeground(Color.BLACK);
				}
			}
		});
		
		button.addMouseListener(new MouseListener() {
			
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
					JButton temp = (JButton)e.getSource();
					temp.setBackground(Color.DARK_GRAY);
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				if(selectedButton != e.getSource()){
					JButton temp = (JButton)e.getSource();
					temp.setBackground(new Color(90, 90, 90));
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public JButton get(int index){
		return buttons.get(index);
	}
	
	public void clearSelection(){
		selectedButton=null;
		for(JButton temp: buttons){
			temp.setBackground(Color.DARK_GRAY);
			temp.setForeground(Color.WHITE);
		}
	}
	
	public void select(int index){
		for(JButton temp: buttons){
			temp.setBackground(Color.DARK_GRAY);
			temp.setForeground(Color.WHITE);
		}
		selectedButton = buttons.get(index);
		selectedButton.setBackground(new Color(230, 230, 230));
		selectedButton.setForeground(Color.BLACK);
	}
	
	// 设置再次点击是否可以取消选择
	public void setCanReSelected(boolean setting){
		this.canReSelected = setting;
	}
}
