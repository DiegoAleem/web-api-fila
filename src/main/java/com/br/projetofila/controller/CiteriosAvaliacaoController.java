package com.br.projetofila.controller;

import com.br.projetofila.bean.CriteriosAvaliacao;
import com.br.projetofila.repository.CriteriosAvaliacaoRepository;
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
@RequestMapping(value = "/criterios_avaliacao")
public class CiteriosAvaliacaoController {

    @Autowired
    private CriteriosAvaliacaoRepository criteriosAvaliacaoRepository;

    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<CriteriosAvaliacao> getAllCriteriosAvaliaco() {
        return criteriosAvaliacaoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idCA}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<CriteriosAvaliacao> getAssunto(@PathVariable("idCA") Integer idCA) {
        return criteriosAvaliacaoRepository.findById(idCA);
    }

}
