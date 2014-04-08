package com.g3lab.square.pojo;

public class Baixo implements Movimentavel {
	private Integer[][] m;
	private int iMat;
	private int jMat;

	public Baixo(Integer[][] m, int i, int j) {
		this.m = m;
		this.iMat = i;
		this.jMat = j;
	}

	@Override
	public void movimenta() {
		
		Integer cp[] = new Integer[8];
		for (int i = 0; i < cp.length; i++) {
			cp[i] = m[i][jMat];
		}
		

		for (int i = 1; i < cp.length - 1; i++) {
			if (i == 1) {
				m[i][jMat] = cp[m.length - 2];
			} else {
				m[i][jMat] = cp[i - 1];
			}
		}

	}

}
