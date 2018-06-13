/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetofila.vo;

/**
 *
 * @author DELL
 */
public class StatusTokenVO {
    
    Integer posicaoFila;
    Integer tempoAtendimento;
    String status;

    public StatusTokenVO(int posicaoFila, int tempoAtendimento, String status) {
        this.posicaoFila = posicaoFila;
        this.tempoAtendimento = tempoAtendimento;
        this.status = status;
    }

    public StatusTokenVO() {
    
    }

    public int getPosicaoFila() {
        return posicaoFila;
    }

    public void setPosicaoFila(int posicaoFila) {
        this.posicaoFila = posicaoFila;
    }

    public int getTempoAtendimento() {
        return tempoAtendimento;
    }

    public void setTempoAtendimento(int tempoAtendimento) {
        this.tempoAtendimento = tempoAtendimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
