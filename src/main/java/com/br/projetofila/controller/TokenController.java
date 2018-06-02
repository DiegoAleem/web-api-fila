package com.br.projetofila.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

import com.br.projetofila.bean.TipoToken;
import com.br.projetofila.bean.Token;
import com.br.projetofila.factory.SenhaFactory;
import com.br.projetofila.factory.TimeFactory;
import com.br.projetofila.repository.TipoTokenRepository;
import com.br.projetofila.repository.TokenRepository;
import com.br.projetofila.vo.SituacaoFilasVO;

@RestController
public class TokenController {
	
    private HashMap<Integer, Token> senhasAtendimento = new HashMap<>();
	
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TipoTokenRepository tipoTokenRepository;
    
    private SenhaFactory senhaFactory;
    
    
    @RequestMapping("/token")
    public @ResponseBody
    Iterable<Token> getAllTokens() {
    	return senhasAtendimento.values();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/token/{idToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Token> getTokenById(@PathVariable("idToken") Integer idToken) {
        return tokenRepository.findById(idToken);
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/token")
    public ResponseEntity<Token> addNovoToken(@RequestBody Token novoToken){
//    	if (novoToken.getTipoToken().getId() == 1) {
//    		novoToken.setSenha(senhaFactory.getProximaSenhaNormal());
//    	}else {
//    		novoToken.setSenha(senhaFactory.getProximaSenhaPreferencial());
//    	}
    	novoToken.setSenha("0");
    	novoToken.setDataRetirada(TimeFactory.getCurrentTime());
    	tokenRepository.save(novoToken); //Salva no banco
    	Token ultimoTokenInserido = getTokenById(novoToken.getId()).get(); 
    	senhasAtendimento.put(ultimoTokenInserido.getId(), ultimoTokenInserido);
        return new ResponseEntity<Token>(ultimoTokenInserido, HttpStatus.OK);
    }
    
    @RequestMapping("/status")
    public ResponseEntity<SituacaoFilasVO> getFila() throws ParseException {
    	SituacaoFilasVO situacao = new SituacaoFilasVO(0,0,0,0);
    	
    	// NORMAL
    	situacao.setTempoEsperaNormal(tokenRepository.getMediaTempoEsperaByTipo("1"));
    	if (situacao.getTempoEsperaNormal() == null) {
			situacao.setTempoEsperaNormal(0);
		}
    	situacao.setQtdPessoasNormal(tokenRepository.getQuantidadeTokensNormaisAguardando("1"));
    	
    	// PREFERENCIAL
    	situacao.setTempoEsperaPreferencial(tokenRepository.getMediaTempoEsperaByTipo("2"));
    	if (situacao.getTempoEsperaPreferencial() == null) {
			situacao.setTempoEsperaPreferencial(0);
		}
    	situacao.setQtdPessoasPreferencial(tokenRepository.getQuantidadeTokensNormaisAguardando("2"));
    	
        return new ResponseEntity<SituacaoFilasVO>(situacao, HttpStatus.OK);
    }
    
    
    public Token getNovo(){
        Token novo = new Token();
        //ArrayList<Token> tks = (ArrayList<Token>) tokenRepository.findAll();
        //novo.setId(tks.get(tks.size()-1).getId()+1);
        Calendar c = Calendar.getInstance();  
        c.add(Calendar.HOUR_OF_DAY, -3);
       
        novo.setDataRetirada(c.getTime());
               
        return novo;
    }
    
    @RequestMapping(method=RequestMethod.GET, value = "/token/normal/proximo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Token getNovoTokenNormal(){
        Token novo = getNovo();
        ArrayList<Token> token = tokenRepository.getTokensNormal();
        int senha = Integer.parseInt(token.get(token.size()-1).getSenha())+1;  
        TipoToken tk = tipoTokenRepository.getTipoTokenNormal();
        novo.setTipoToken(tk);
        novo.setSenha(Integer.toString(senha));
        return novo;
    }
    
    @RequestMapping(method=RequestMethod.GET, value = "/token/preferencial/proximo", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Token getNovoTokenPreferencial(){
        Token novo = getNovo();
        ArrayList<Token> token = tokenRepository.getTokensPreferencial();
        int senha = Integer.parseInt(token.get(token.size()-1).getSenha())+1;        
        TipoToken tk = tipoTokenRepository.getTipoTokenNormal();
        
        novo.setTipoToken(tk);
        novo.setSenha(Integer.toString(senha));
        return novo;
    } 
    
}