package com.br.projetofila.vo;

public class SituacaoFilasVO {
	private Integer tempoEsperaPreferencial;
	private Integer qtdPessoasPreferencial;
	
	private Integer tempoEsperaNormal;
	private Integer qtdPessoasNormal;
	
	
	
	public SituacaoFilasVO() {	}
	
	public SituacaoFilasVO(Integer tempoEsperaPreferencial, Integer qtdPessoasPreferencial, Integer tempoEsperaNormal,
			Integer qtdPessoasNormal) {
		super();
		this.tempoEsperaPreferencial = tempoEsperaPreferencial;
		this.qtdPessoasPreferencial = qtdPessoasPreferencial;
		this.tempoEsperaNormal = tempoEsperaNormal;
		this.qtdPessoasNormal = qtdPessoasNormal;
	}


	public Integer getTempoEsperaPreferencial() {
		return tempoEsperaPreferencial;
	}
	public void setTempoEsperaPreferencial(Integer tempoEsperaPreferencial) {
		this.tempoEsperaPreferencial = tempoEsperaPreferencial;
	}
	public Integer getQtdPessoasPreferencial() {
		return qtdPessoasPreferencial;
	}
	public void setQtdPessoasPreferencial(Integer qtdPessoasPreferencial) {
		this.qtdPessoasPreferencial = qtdPessoasPreferencial;
	}
	public Integer getTempoEsperaNormal() {
		return tempoEsperaNormal;
	}
	public void setTempoEsperaNormal(Integer tempoEsperaNormal) {
		this.tempoEsperaNormal = tempoEsperaNormal;
	}
	public Integer getQtdPessoasNormal() {
		return qtdPessoasNormal;
	}
	public void setQtdPessoasNormal(Integer qtdPessoasNormal) {
		this.qtdPessoasNormal = qtdPessoasNormal;
	}

}
