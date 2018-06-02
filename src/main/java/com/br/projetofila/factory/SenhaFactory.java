package com.br.projetofila.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.projetofila.repository.TokenRepository;

public class SenhaFactory {
	
	@Autowired
    private TokenRepository tokenRepository;
	
	private String ultimaSenhaNormal = "";
	private String ultimaSenhaPreferencial;
	
	public String getProximaSenhaNormal() {
		ultimaSenhaNormal = tokenRepository.getUltimaSenhaByTipo("1");
		if (ultimaSenhaNormal.equals("")) {
			return "0";
		}else {
			int senhaInt = Integer.parseInt(ultimaSenhaNormal);
			return Integer.toString(senhaInt++);
		}
	}
	
	public String getProximaSenhaPreferencial() {
		ultimaSenhaPreferencial = tokenRepository.getUltimaSenhaByTipo("2");
		
		return tokenRepository.getUltimaSenhaByTipo("2");
	}
}
