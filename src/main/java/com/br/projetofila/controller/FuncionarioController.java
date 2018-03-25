
package com.br.projetofila.controller;

import com.br.projetofila.bean.Funcionario;
import com.br.projetofila.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/funcionario")
public class FuncionarioController {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @GetMapping(value="/all")
	public @ResponseBody Iterable<Funcionario> getAllTokens() {
		return funcionarioRepository.findAll();
	}
}
