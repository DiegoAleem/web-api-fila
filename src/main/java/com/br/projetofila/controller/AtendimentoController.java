package com.br.projetofila.controller;

import com.br.projetofila.bean.Atendimento;
import com.br.projetofila.factory.TimeFactory;
import com.br.projetofila.repository.AtendimentoRepository;
import com.br.projetofila.repository.FuncionarioRepository;
import com.br.projetofila.repository.TokenRepository;
import com.br.projetofila.vo.AtendimentoVO;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;
    
    @Autowired
    private TokenRepository tokenRepository;
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

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
    
    @RequestMapping(method = RequestMethod.POST, value="/atendimento")
    public ResponseEntity<AtendimentoVO> addNovoAtendimento(@RequestBody AtendimentoVO atendimentoVO){
    	
    	atendimentoVO.setDataInicio(TimeFactory.getCurrentTime());
    	
    	Atendimento novoAtendimento = new Atendimento();
    	novoAtendimento.setDataInicio(atendimentoVO.getDataInicio());
    	novoAtendimento.setToken(tokenRepository.getProximoTokenByTipo(atendimentoVO.getTipoAtendimento()));
    	novoAtendimento.setFuncionario(funcionarioRepository.findFuncionarioByMatricula(atendimentoVO.getFuncionario()));
    	
    	Atendimento atendimentoSalvo = atendimentoRepository.save(novoAtendimento);
    	atendimentoVO.setId(atendimentoSalvo.getId());
    	atendimentoVO.setToken(atendimentoSalvo.getToken().getSenha());
    	
    	tokenRepository.changeStatusAtendimentoById("3", Integer.toString(atendimentoSalvo.getToken().getId()));
    	
        return new ResponseEntity<>(atendimentoVO, HttpStatus.OK);
    }
}
