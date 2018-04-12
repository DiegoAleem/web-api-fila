package com.br.projetofila.controller;

import com.br.projetofila.bean.TipoToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.projetofila.bean.Token;
import com.br.projetofila.repository.TokenRepository;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @RequestMapping("/token")
    public @ResponseBody
    Iterable<Token> getAllTokens() {
        return tokenRepository.findAll();
    }
    
    @RequestMapping(method=RequestMethod.GET, value = "/token/normal/qtdPessoas", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Integer getQtdTokenNormais(){
        return tokenRepository.qtdTokenNormaisFila();
    }
    
    @RequestMapping(method=RequestMethod.GET, value = "/token/preferencial/qtdPessoas", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Integer getQtdTokenPreferencial(){
        return tokenRepository.qtdTokenPreferencialFila();
    }
    
    @RequestMapping(method=RequestMethod.GET, value = "/token/preferencial/mediaTempo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Float getMediaTempoTokenPreferencial(){
        return tokenRepository.mediaChamadaPreferencialFila();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/token/{idToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Token> getTokenById(@PathVariable("idToken") Integer idToken) {
        return tokenRepository.findById(idToken);
    }
}
