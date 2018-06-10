package com.br.projetofila.vo;

public class FuncionarioVO {
	private String nome;
	private String matricula;
	private String senha;
	private String tipoFuncionario;
	
	public FuncionarioVO(String nome, String matricula, String senha, String tipoFuncionario) {
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.tipoFuncionario = tipoFuncionario;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTipoFuncionario() {
		return tipoFuncionario;
	}
	public void setTipoFuncionario(String tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}
	
}
