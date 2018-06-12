package com.br.projetofila.controller;

import com.br.projetofila.bean.Funcionario;
import com.br.projetofila.repository.FuncionarioRepository;
import com.br.projetofila.vo.FuncionarioVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    @RequestMapping("/funcionario")
    public @ResponseBody
    Iterable<Funcionario> getFuncionarios() {
        return funcionarioRepository.findAll();
    }
    
    @RequestMapping(method =RequestMethod.POST, value="/funcionario")
    public void addAssunto(@RequestBody Funcionario funcionario){
        funcionarioRepository.save(funcionario);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.GET, value = "/funcionario")
    public @ResponseBody
    ResponseEntity<FuncionarioVO> getFuncionario(@RequestParam("matricula") String matricula) {
        return new ResponseEntity<FuncionarioVO> ( funcionarioRepository.findFuncionario(matricula), HttpStatus.OK);
    }

}
