package com.br.projetofila.controller;

import com.br.projetofila.bean.HistoricoAtendimento;
import com.br.projetofila.repository.HistoricoAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="historico_atendimento")
public class HistoricoAtendimentoController {
    
    @Autowired
    private HistoricoAtendimentoRepository historicoAtendimentoRepository;
    
    @GetMapping(value="/all")
	public @ResponseBody Iterable<HistoricoAtendimento> getAllTokens() {
		return historicoAtendimentoRepository.findAll();
	}

}
