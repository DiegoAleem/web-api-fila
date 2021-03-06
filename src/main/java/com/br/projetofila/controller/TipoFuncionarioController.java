package com.br.projetofila.controller;

import com.br.projetofila.bean.TipoFuncionario;
import com.br.projetofila.repository.TipoFuncionarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoFuncionarioController {

    @Autowired
    private TipoFuncionarioRepository tipoFuncionarioRepository;

    @RequestMapping("/tipoFuncionario")
    public @ResponseBody
    Iterable<TipoFuncionario> getAllTipoFuncionarios() {
        return tipoFuncionarioRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tipoFuncionario/{idTipoFuncionario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<TipoFuncionario> getTipoFuncionario(@PathVariable("idTipoFuncionario") Integer idTipoFuncionario) {
        return tipoFuncionarioRepository.findById(idTipoFuncionario);
    }

}
