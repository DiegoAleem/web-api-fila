package com.br.projetofila.vo;

public class SituacaoFilasVO {
	private int tempoEsperaPreferencial;
	private int qtdPessoasPreferencial;
	
	private int tempoEsperaNormal;
	private int qtdPessoasNormal;
	
	
	
	public SituacaoFilasVO() {	}
	
	public int getTempoEsperaPreferencial() {
		return tempoEsperaPreferencial;
	}
	public void setTempoEsperaPreferencial(int tempoEsperaPreferencial) {
		this.tempoEsperaPreferencial = tempoEsperaPreferencial;
	}
	public int getQtdPessoasPreferencial() {
		return qtdPessoasPreferencial;
	}
	public void setQtdPessoasPreferencial(int qtdPessoasPreferencial) {
		this.qtdPessoasPreferencial = qtdPessoasPreferencial;
	}
	public int getTempoEsperaNormal() {
		return tempoEsperaNormal;
	}
	public void setTempoEsperaNormal(int tempoEsperaNormal) {
		this.tempoEsperaNormal = tempoEsperaNormal;
	}
	public int getQtdPessoasNormal() {
		return qtdPessoasNormal;
	}
	public void setQtdPessoasNormal(int qtdPessoasNormal) {
		this.qtdPessoasNormal = qtdPessoasNormal;
	}

}
