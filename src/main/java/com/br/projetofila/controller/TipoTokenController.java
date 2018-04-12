package com.br.projetofila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.projetofila.bean.TipoToken;
import com.br.projetofila.repository.TipoTokenRepository;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TipoTokenController {

    @Autowired
    private TipoTokenRepository tipoTokenRepository;

    @RequestMapping("/tipoToken")
    public @ResponseBody
    Iterable<TipoToken> getAllTipoTokens() {
        return tipoTokenRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tipoToken/{idTipoToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<TipoToken> getAssunto(@PathVariable("idTipoToken") Integer idTipoToken) {
        return tipoTokenRepository.findById(idTipoToken);
    }

}
