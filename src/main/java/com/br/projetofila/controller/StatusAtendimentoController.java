package com.br.projetofila.controller;

import com.br.projetofila.bean.StatusAtendimento;
import com.br.projetofila.repository.StatusAtendimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="status_atendimento")
public class StatusAtendimentoController {
    
    @Autowired
    private StatusAtendimentoRepository statusAtendimentoRepository;
     
     @GetMapping(value="/all")
     public @ResponseBody Iterable<StatusAtendimento> getAllTipoFuncionarios(){
         return statusAtendimentoRepository.findAll();
     }
}
