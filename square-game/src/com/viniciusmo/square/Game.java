package com.viniciusmo.square;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Game extends JPanel implements Validador {
	public static final int SETA_CIMA = 0;
	public static final int SETA_BAIXO = 1;
	public static final int SETA_DIREITA = 2;
	public static final int SETA_ESQUERDA = 3;
	public static final int FND_PRETO = 4;
	private String level;

	private static final long serialVersionUID = 1L;
	private Integer[][] mGame;
	private Integer[][] mGameFinal;

	private LogicGame logic;

	public Game() {
	}

	public Game(String level) {
		setSize(560, 560);
		this.level = level;
		mGame = new Integer[8][8];
		mGameFinal = new Integer[8][8];
		initGame();
		loadLevel();
		logic = new LogicGame(mGame, this);
		addMouseListener(logic);
	}

	public Integer[][] getmGameFinal() {
		return mGameFinal;
	}

	private void initGame() {

		for (int i = 1; i < 7; i++) {
			mGame[i][0] = SETA_ESQUERDA;
			mGame[i][7] = SETA_DIREITA;
			mGame[7][i] = SETA_BAIXO;
			mGame[0][i] = SETA_CIMA;
			mGameFinal[i][0] = FND_PRETO;
			mGameFinal[i][7] = FND_PRETO;
			mGameFinal[7][i] = FND_PRETO;
			mGameFinal[0][i] = FND_PRETO;

		}

		mGameFinal[0][0] = mGame[0][0] = FND_PRETO;
		mGameFinal[7][0] = mGame[7][0] = FND_PRETO;
		mGameFinal[0][7] = mGame[0][7] = FND_PRETO;
		mGameFinal[7][7] = mGame[7][7] = FND_PRETO;
	}

	public void loadLevel() {
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(level);

		try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			List list = rootNode.getChildren("tela");

			for (int i = 0; i < list.size(); i++) {

				Element node = (Element) list.get(i);
				loadLine(node.getChildText("linha_1").split(","), 1, mGame);
				loadLine(node.getChildText("linha_2").split(","), 2, mGame);
				loadLine(node.getChildText("linha_3").split(","), 3, mGame);
				loadLine(node.getChildText("linha_4").split(","), 4, mGame);
				loadLine(node.getChildText("linha_5").split(","), 5, mGame);
				loadLine(node.getChildText("linha_6").split(","), 6, mGame);
			}
			list = rootNode.getChildren("tela_final");

			for (int i = 0; i < list.size(); i++) {

				Element node = (Element) list.get(i);
				loadLine(node.getChildText("linha_1").split(","), 1, mGameFinal);
				loadLine(node.getChildText("linha_2").split(","), 2, mGameFinal);
				loadLine(node.getChildText("linha_3").split(","), 3, mGameFinal);
				loadLine(node.getChildText("linha_4").split(","), 4, mGameFinal);
				loadLine(node.getChildText("linha_5").split(","), 5, mGameFinal);
				loadLine(node.getChildText("linha_6").split(","), 6, mGameFinal);
			}

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}

	}

	private void loadLine(String[] line, int i, Integer[][] mGame) {
		for (int j = 1; j <= line.length; j++) {
			mGame[i][j] = Integer.parseInt(line[j - 1].trim());
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(0, 0, 0));
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ImageIcon ii = new ImageIcon(this.getClass().getResource(
						"/images/" + mGame[i][j] + ".png"));
				g2.drawImage(ii.getImage(), j * 70, i * 70, this);
			}
		}
	}

	@Override
	public boolean valida() {
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				if (mGame[i][j] != mGameFinal[i][j]) {

					return false;
				}
			}

		}
		return true;
	}

	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
