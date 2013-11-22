package torpedo.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JComponent;

@SuppressWarnings("serial")
class BoardCell extends JComponent {

	private boolean isWrecked;
	private boolean isReserved = true;

	public static Random random = new Random();

	public void paintComponent(Graphics graphics) {
		if (isWrecked) {
			graphics.setColor(Color.RED);
			graphics.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		} else if(isReserved) {
			graphics.setColor(Color.GRAY);
			graphics.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
		} else {
			graphics.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
		}
	}
	public boolean isWrecked() {
		return isWrecked;
	}

	public void setWrecked(boolean isLiving) {
		this.isWrecked = isLiving;
	}
	public boolean isReserved() {
		return isReserved;
	}

	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
}
