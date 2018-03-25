package com.br.projetofila.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "NOTAS_ATENDIMENTO")
public class NotasAtendimento implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
       
    @ManyToOne
    @JoinColumn(name="CRITERIOS_AVALIACAO_ID")
    private CriteriosAvaliacao criteriosAvaliacao;
    
    @Column(name="NOTA")
    private int nota;
    
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="ATENDIMENTO_ID")
    private Atendimento atendimento;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public CriteriosAvaliacao getCriteriosAvaliacao() {
        return criteriosAvaliacao;
    }

    public void setCriteriosAvaliacao(CriteriosAvaliacao criteriosAvaliacao) {
        this.criteriosAvaliacao = criteriosAvaliacao;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    
}
