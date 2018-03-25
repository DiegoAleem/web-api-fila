package com.br.projetofila.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.projetofila.bean.AssuntoAtendimento;
import com.br.projetofila.repository.AssuntoAtendimentoRepository;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/assunto")
public class AssuntoAtendimentoController {

    @Autowired
    private AssuntoAtendimentoRepository assuntoRepository;

    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<AssuntoAtendimento> getAllAssuntos() {
        return assuntoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idAssunto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<AssuntoAtendimento> getAssunto(@PathVariable("idAssunto") Integer idAssunto) {
        return assuntoRepository.findById(idAssunto);
    }

}
