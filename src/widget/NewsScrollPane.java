package widget;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class NewsScrollPane extends JScrollPane{
	
	public NewsScrollPane(Component view){
		super(view);
		this.setBackground(Color.WHITE);
		this.setWheelScrollingEnabled(true);
		this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {

				int w = thumbBounds.width;
				int h = thumbBounds.height;
				g.translate(thumbBounds.x, thumbBounds.y);
				// 用黑色填充矩形
				g.setColor(Color.DARK_GRAY);
				g.fillRect(0, 0, w - 1, h - 1);
				g.translate(-thumbBounds.x, -thumbBounds.y);
			}

			@Override
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				g.setColor(new Color(230, 230, 230));
				g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);

				// 始终没有进入这两个判断方法，通过单独调用它们中的任意一个即刻明白这两个方法的含义
				if (trackHighlight == DECREASE_HIGHLIGHT) {
					paintDecreaseHighlight(g);
				} else if (trackHighlight == INCREASE_HIGHLIGHT) {
					paintIncreaseHighlight(g);
				}
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				return new BasicArrowButton(orientation, Color.DARK_GRAY, Color.DARK_GRAY, new Color(230, 230, 230),
						Color.DARK_GRAY);
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				return new BasicArrowButton(orientation, Color.DARK_GRAY, Color.DARK_GRAY, new Color(230, 230, 230),
						Color.DARK_GRAY);
			}

		});
	}

}
