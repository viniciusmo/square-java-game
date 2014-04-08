package com.viniciusmo.square;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VisualizarLevel extends JFrame {

	private static final long serialVersionUID = 1L;
	private Game game;

	public VisualizarLevel(Game game) {
		this.game = game;
		setSize(560, 560);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setLocation(screenWidth / 2, screenHeight / 4);

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ImageIcon ii = new ImageIcon(this.getClass().getResource(
						"/images/" + game.getmGameFinal()[i][j] + ".png"));
				g2.drawImage(ii.getImage(), j * 70, i * 70, this);
			}
		}
	}

}
