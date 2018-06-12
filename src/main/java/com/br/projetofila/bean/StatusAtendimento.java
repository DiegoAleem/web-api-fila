package com.br.projetofila.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="STATUS_ATENDIMENTO")
public class StatusAtendimento implements Serializable {
    
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public StatusAtendimento(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	@Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="DESCRICAO")
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
