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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ATENDIMENTO")
public class Atendimento implements Serializable {
	
	public Atendimento() {
		
	}
       
    public Atendimento(Date dataInicio, Token token, Funcionario funcionario) {
		this.dataInicio = dataInicio;
		this.token = token;
		this.funcionario = funcionario;
	}

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATA_INICIO")
    private Date dataInicio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DATA_FIM")
    private Date dataFim;
    
    @OneToOne
    @JoinColumn(name="TOKEN_ID")
    private Token token;
    
    @ManyToOne
    @JoinColumn(name="FUNCIONARIO_ID")
    private Funcionario funcionario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    
}
