package com.viniciusmo.square.pojo;

public class Esquerda implements Movimentavel {

	private Integer[][] m;
	private int i;
	private int j;

	public Esquerda(Integer[][] m, int i, int j) {
		this.m = m;
		this.i = i;
		this.j = j;
	}

	@Override
	public void movimenta() {
		Integer cp[] = m[i].clone();
		for (int j = 1; j < cp.length - 1; j++) {
			if (j == 1) {
				m[i][m.length - 2] = cp[j];
			} else {
				m[i][j - 1] = cp[j];
			}
		}
	

	}

}
