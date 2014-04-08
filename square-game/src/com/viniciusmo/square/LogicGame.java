package com.viniciusmo.square;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import com.viniciusmo.square.pojo.Baixo;
import com.viniciusmo.square.pojo.Cima;
import com.viniciusmo.square.pojo.Direita;
import com.viniciusmo.square.pojo.Esquerda;
import com.viniciusmo.square.pojo.Movimentavel;

public class LogicGame extends MouseAdapter {
	private JPanel panel;
	private Integer[][] mGame;

	public LogicGame(Integer[][] mGame, JPanel panel) {
		this.mGame = mGame;
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int i = e.getY() / 70;
		int j = e.getX() / 70;
		if (i == 0 && j != 0 && j != 7) {
			movimenta(new Cima(mGame, i, j));

		}
		if (i == 7 && j != 7 && j != 0) {
			movimenta(new Baixo(mGame, i, j));
		}
		if (j == 0 && i != 7 && i != 0) {
			movimenta(new Esquerda(mGame, i, j));
		}
		if (j == 7 && i != 7 && i != 0) {
			movimenta(new Direita(mGame, i, j));
		}
		panel.repaint();
	}

	private void movimenta(Movimentavel mov) {
		mov.movimenta();
	}

}
