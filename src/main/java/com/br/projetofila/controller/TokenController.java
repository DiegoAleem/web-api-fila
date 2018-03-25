package com.br.projetofila.controller;

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

@Controller
@RequestMapping(value = "/token")
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Token> getToken(@PathVariable("idToken") Integer idToken) {
        return tokenRepository.findById(idToken);
    }
}
