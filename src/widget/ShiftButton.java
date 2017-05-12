package widget;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ShiftButton extends JButton {
	public ShiftButton(String icon, int iconSize, String text) {
		super(text);
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setFocusPainted(false);
		setBorderPainted(false);
		setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		setIcon(FontAwesome.getIcon(icon, iconSize, Color.WHITE));
		setMargin(new Insets(0, 0, 0, 0));

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
				JButton temp = (JButton) e.getSource();
				temp.setBackground(Color.DARK_GRAY);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				JButton temp = (JButton) e.getSource();
				temp.setBackground(new Color(90, 90, 90));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
}
