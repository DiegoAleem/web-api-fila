package com.br.projetofila.controller;

import com.br.projetofila.bean.NotasAtendimento;
import com.br.projetofila.repository.NotasAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/notas_atendimento")
public class NotasAtendimentoController {
    
    @Autowired
    private NotasAtendimentoRepository notasAtendimentoRepository;
    
    @GetMapping(value="/all")
	public @ResponseBody Iterable<NotasAtendimento> getAllTokens() {
		return notasAtendimentoRepository.findAll();
	}
    
}
