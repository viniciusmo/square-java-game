package com.g3lab.square;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private Game game;
	private JButton btnLevel;

	public Main() {
		game = new Game("resource/levels/level_1.xml");
		btnLevel = new JButton("Visualizar Level");
		btnLevel.setBounds(0, 570, 560, 50);
		btnLevel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				VisualizarLevel v = new VisualizarLevel(game);
				v.show();
			}

		});
		add(btnLevel);
		add(game);
		setBackground(new Color(238, 238, 238));
		setSize(560, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setTitle(":D");
	}

	public static void main(String[] args) {
		final Main main = new Main();
		new Thread() {

			@Override
			public void run() {
				while (true) {
					if (main.game.valida()) {
						System.out.println("Ganhou!");
						JOptionPane.showMessageDialog(main,
								"Parabéns voce ganhou!", "Next Level",
								JOptionPane.INFORMATION_MESSAGE);

						main.game.setLevel("resource/levels/level_2.xml");
						main.game.loadLevel();
						main.game.repaint();

					}
					System.out.println();
				}

			}

		}.start();
	}
}
