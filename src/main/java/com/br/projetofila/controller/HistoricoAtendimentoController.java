package com.br.projetofila.controller;

import com.br.projetofila.bean.HistoricoAtendimento;
import com.br.projetofila.repository.HistoricoAtendimentoRepository;
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
@RequestMapping(value = "historico_atendimento")
public class HistoricoAtendimentoController {

    @Autowired
    private HistoricoAtendimentoRepository historicoAtendimentoRepository;

    @GetMapping(value = "/all")
    public @ResponseBody
    Iterable<HistoricoAtendimento> getAllHistoricosAtendimentos() {
        return historicoAtendimentoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{idHA}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<HistoricoAtendimento> getHistoricoAtendimento(@PathVariable("idHA") Integer idHA) {
        return historicoAtendimentoRepository.findById(idHA);
    }
}
