package com.br.projetofila.controller;

import com.br.projetofila.bean.StatusAtendimento;
import com.br.projetofila.repository.StatusAtendimentoRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusAtendimentoController {

    @Autowired
    private StatusAtendimentoRepository statusAtendimentoRepository;

    @RequestMapping("/statusAtendimento")
    public @ResponseBody
    Iterable<StatusAtendimento> getAllStatusAtendimento() {
        return statusAtendimentoRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statusAtendimento/{idStatusAtendimento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<StatusAtendimento> getStatusAtendimento(@PathVariable("idStatusAtendimento") Integer idStatusAtendimetno) {
        return statusAtendimentoRepository.findById(idStatusAtendimetno);
    }
}
