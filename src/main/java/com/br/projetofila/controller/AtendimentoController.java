package com.br.projetofila.controller;

import com.br.projetofila.bean.Atendimento;
import com.br.projetofila.repository.AtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/atendimento")
public class AtendimentoController {
    
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    
    @GetMapping(value="/all")
    public @ResponseBody Iterable<Atendimento> getAllTokens() {
	return atendimentoRepository.findAll();
    }
}
