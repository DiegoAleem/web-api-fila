package com.br.projetofila.controller;

import com.br.projetofila.bean.Atendimento;
import com.br.projetofila.repository.AtendimentoRepository;
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
@RequestMapping(value = "/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<Atendimento> getAllAtendimentos() {
        return atendimentoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idAtendimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Atendimento> getAtendimento(@PathVariable("idAtendimento") Integer idAtendimento) {
        return atendimentoRepository.findById(idAtendimento);
    }
}
