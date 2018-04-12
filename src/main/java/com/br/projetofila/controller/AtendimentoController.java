package com.br.projetofila.controller;

import com.br.projetofila.bean.Atendimento;
import com.br.projetofila.repository.AtendimentoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @RequestMapping("/atendimento")
    public @ResponseBody
    Iterable<Atendimento> getAllAtendimentos() {
        return atendimentoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/atendimento/{idAtendimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Atendimento> getAtendimento(@PathVariable("idAtendimento") Integer idAtendimento) {
        return atendimentoRepository.findById(idAtendimento);
    }
}
