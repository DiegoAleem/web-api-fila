package com.br.projetofila.controller;

import com.br.projetofila.bean.Funcionario;
import com.br.projetofila.repository.FuncionarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/funcionario")
    public @ResponseBody
    Iterable<Funcionario> getFuncionarios() {
        return funcionarioRepository.findAll();
    }
    
    @RequestMapping(method =RequestMethod.POST, value="/funcionario")
    public void addAssunto(@RequestBody Funcionario funcionario){
        funcionarioRepository.save(funcionario);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/funcionario/{idFuncionario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Funcionario> getFuncionario(@PathVariable("idFuncionario") Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario);
    }

}
