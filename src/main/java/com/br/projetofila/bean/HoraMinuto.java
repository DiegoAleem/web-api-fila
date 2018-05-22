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
public class HoraMinuto {
    
    String hora;
 

    public HoraMinuto(int hora, int minuto) {
        this.hora = hora+":"+minuto;
    
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
}
