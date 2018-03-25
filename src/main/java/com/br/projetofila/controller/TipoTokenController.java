package com.br.projetofila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.projetofila.bean.TipoToken;
import com.br.projetofila.repository.TipoTokenRepository;

@Controller
@RequestMapping(value="/tipo_token")
public class TipoTokenController {
	
	@Autowired
	private TipoTokenRepository tipoTokenRepository;
	
	@GetMapping(value="/all")
	public @ResponseBody Iterable<TipoToken> getAllTipoTokens() {
		return tipoTokenRepository.findAll();
	}
	
}
