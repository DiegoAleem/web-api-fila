package com.br.projetofila.controller;

import com.br.projetofila.bean.HistoricoAtendimento;
import com.br.projetofila.repository.HistoricoAtendimentoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoricoAtendimentoController {

    @Autowired
    private HistoricoAtendimentoRepository historicoAtendimentoRepository;

    @RequestMapping("/historicoAtendimento")
    public @ResponseBody
    Iterable<HistoricoAtendimento> getAllHistoricosAtendimentos() {
        return historicoAtendimentoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/historicoAtendimento/{idHA}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<HistoricoAtendimento> getHistoricoAtendimento(@PathVariable("idHA") Integer idHA) {
        return historicoAtendimentoRepository.findById(idHA);
    }
}
