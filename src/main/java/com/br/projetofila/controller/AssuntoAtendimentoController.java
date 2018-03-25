package com.br.projetofila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.projetofila.bean.AssuntoAtendimento;
import com.br.projetofila.repository.AssuntoAtendimentoRepository;

@Controller
@RequestMapping(value="/assunto")
public class AssuntoAtendimentoController {
	
	@Autowired
	private AssuntoAtendimentoRepository assuntoRepository;
	
	@GetMapping(value="/all")
	public @ResponseBody Iterable<AssuntoAtendimento> getAllAssuntos() {
		return assuntoRepository.findAll();
	}
	
	
	
}
