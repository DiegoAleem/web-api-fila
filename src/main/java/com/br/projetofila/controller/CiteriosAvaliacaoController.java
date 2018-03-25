package com.br.projetofila.controller;

import com.br.projetofila.bean.CriteriosAvaliacao;
import com.br.projetofila.repository.CriteriosAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/criterios_avaliacao")
public class CiteriosAvaliacaoController {
    
    @Autowired
    private CriteriosAvaliacaoRepository criteriosAvaliacaoRepository;
    
    @GetMapping(value="/all")
	public @ResponseBody Iterable<CriteriosAvaliacao> getAllTokens() {
		return criteriosAvaliacaoRepository.findAll();
	}
    
}
