package com.br.projetofila.controller;

import com.br.projetofila.bean.Funcionario;
import com.br.projetofila.repository.FuncionarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<Funcionario> getFuncionarios() {
        return funcionarioRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idFuncionario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Funcionario> getFuncionario(@PathVariable("idFuncionario") Integer idFuncionario) {
        return funcionarioRepository.findById(idFuncionario);
    }

}
