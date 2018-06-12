package com.br.projetofila.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="TOKEN")
public class Token implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
        @Column(name="ID")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="TIPO_TOKEN_ID")
	private TipoToken tipoToken;
	
	@ManyToOne
	@JoinColumn(name="ASSUNTO_ATENDIMENTO_ID")
	private AssuntoAtendimento assuntoAtendimento;
	
	@Column(name="SENHA")
	private String senha;
	
        @Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATA_RETIRADA")
	private Date dataRetirada;
   
    @ManyToOne
	@JoinColumn(name="STATUS_ATENDIMENTO_ID")     
    private StatusAtendimento statusAtendimento;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoToken getTipoToken() {
		return tipoToken;
	}

	public void setTipoToken(TipoToken tipoToken) {
		this.tipoToken = tipoToken;
	}

	public AssuntoAtendimento getAssuntoAtendimento() {
		return assuntoAtendimento;
	}

	public void setAssuntoAtendimento(AssuntoAtendimento assuntoAtendimento) {
		this.assuntoAtendimento = assuntoAtendimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataRetirada() {
		return dataRetirada;
	}

	public void setDataRetirada(Date dataRetirada) {
		this.dataRetirada = dataRetirada;
	}
	
	public StatusAtendimento getStatusAtendimento() {
		return statusAtendimento;
	}

	public void setStatusAtendimento(StatusAtendimento statusAtendimento) {
		this.statusAtendimento = statusAtendimento;
	}
	
	
}
