/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.projetofila.bean;

/**
 *
 * @author DELL
 */
public class Fila {
    
    HoraMinuto tempoEsperaGeralPreferencial;
    
    int qtdPessoasPreferencial; 
    
    HoraMinuto tempoEsperaGeralNormal;
    
    int qtdPessoasNormal;

    public Fila(HoraMinuto tempoEsperaGeralPreferencial, int qtdPessoasPreferencial, HoraMinuto tempoEsperaGeralNormal, int qtdPessoasNormal) {
        this.tempoEsperaGeralPreferencial = tempoEsperaGeralPreferencial;
        this.qtdPessoasPreferencial = qtdPessoasPreferencial;
        this.tempoEsperaGeralNormal = tempoEsperaGeralNormal;
        this.qtdPessoasNormal = qtdPessoasNormal;
    }

    public Fila() {
   
    }

    public HoraMinuto getTempoEsperaGeralPreferencial() {
        return tempoEsperaGeralPreferencial;
    }

    public void setTempoEsperaGeralPreferencial(HoraMinuto tempoEsperaGeralPreferencial) {
        this.tempoEsperaGeralPreferencial = tempoEsperaGeralPreferencial;
    }

    public int getQtdPessoasPreferencial() {
        return qtdPessoasPreferencial;
    }

    public void setQtdPessoasPreferencial(int qtdPessoasPreferencial) {
        this.qtdPessoasPreferencial = qtdPessoasPreferencial;
    }

    public HoraMinuto getTempoEsperaGeralNormal() {
        return tempoEsperaGeralNormal;
    }

    public void setTempoEsperaGeralNormal(HoraMinuto tempoEsperaGeralNormal) {
        this.tempoEsperaGeralNormal = tempoEsperaGeralNormal;
    }

    public int getQtdPessoasNormal() {
        return qtdPessoasNormal;
    }

    public void setQtdPessoasNormal(int qtdPessoasNormal) {
        this.qtdPessoasNormal = qtdPessoasNormal;
    }
    
    
}
