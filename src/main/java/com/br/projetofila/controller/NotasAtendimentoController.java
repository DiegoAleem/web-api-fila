package com.br.projetofila.controller;

import com.br.projetofila.bean.NotasAtendimento;
import com.br.projetofila.repository.NotasAtendimentoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotasAtendimentoController {

    @Autowired
    private NotasAtendimentoRepository notasAtendimentoRepository;

    @RequestMapping("/notasAtendimento")
    public @ResponseBody
    Iterable<NotasAtendimento> getAllNotas() {
        return notasAtendimentoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/notasAtendimento/{idNota}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<NotasAtendimento> getNota(@PathVariable("idNotas") Integer idNota) {
        return notasAtendimentoRepository.findById(idNota);
    }
}
