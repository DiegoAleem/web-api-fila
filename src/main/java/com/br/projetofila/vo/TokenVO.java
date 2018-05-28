package com.br.projetofila.vo;

public class TokenVO {
	private int id;
	private String tipoToken;
	private String assuntoAtendimento;
	private String senha;
	private String dataRetirada;
	
	
	
	public TokenVO(int id, String tipoToken, String assuntoAtendimento, String senha, String dataRetirada) {
		this.id = id;
		this.tipoToken = tipoToken;
		this.assuntoAtendimento = assuntoAtendimento;
		this.senha = senha;
		this.dataRetirada = dataRetirada;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoToken() {
		return tipoToken;
	}
	public void setTipoToken(String tipoToken) {
		this.tipoToken = tipoToken;
	}
	public String getAssuntoAtendimento() {
		return assuntoAtendimento;
	}
	public void setAssuntoAtendimento(String assuntoAtendimento) {
		this.assuntoAtendimento = assuntoAtendimento;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDataRetirada() {
		return dataRetirada;
	}
	public void setDataRetirada(String dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
}
