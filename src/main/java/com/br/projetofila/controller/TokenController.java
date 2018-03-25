package com.br.projetofila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.projetofila.bean.Token;
import com.br.projetofila.repository.TokenRepository;

@Controller
@RequestMapping(value="/token")
public class TokenController {
	@Autowired
	private TokenRepository tokenRepository;
	
	@GetMapping(value="/all")
	public @ResponseBody Iterable<Token> getAllTokens() {
		return tokenRepository.findAll();
	}
}
