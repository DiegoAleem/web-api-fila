package com.br.projetofila.controller;

import com.br.projetofila.bean.TipoFuncionario;
import com.br.projetofila.repository.TipoFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/tipo_funcionario")
public class TipoFuncionarioController {
    
     @Autowired
     private TipoFuncionarioRepository tipoFuncionarioRepository;
     
     @GetMapping(value="/all")
     public @ResponseBody Iterable<TipoFuncionario> getAllTipoFuncionarios(){
         return tipoFuncionarioRepository.findAll();
     }
}
