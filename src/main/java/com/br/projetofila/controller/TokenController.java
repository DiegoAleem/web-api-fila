package com.br.projetofila.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.br.projetofila.repository.TipoTokenRepository;
import com.br.projetofila.repository.TokenRepository;
import com.br.projetofila.vo.SituacaoFilasVO;

@RestController
public class TokenController {
    
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TipoTokenRepository tipoTokenRepository;
    
    @RequestMapping("/token")
    public @ResponseBody
    Iterable<Token> getAllTokens() {
        return tokenRepository.findAll();
    }
    
    @RequestMapping("/status")
    public ResponseEntity<SituacaoFilasVO> getFila() throws ParseException {
    	SituacaoFilasVO situacao = new SituacaoFilasVO();
    	
    	// NORMAL    	
    	situacao.setTempoEsperaNormal(tokenRepository.mediaTempo("1"));
    	situacao.setQtdPessoasNormal(0);
    	
    	// PREFERENCIAL
    	situacao.setTempoEsperaPreferencial(tokenRepository.mediaTempo("2"));
    	situacao.setQtdPessoasPreferencial(0);
    	
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
    
    @RequestMapping(method=RequestMethod.GET, value = "/token/normal/qtdPessoasFila", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Integer getQtdTokenNormais(){
        return tokenRepository.qtdTokenNormaisFila() - tokenRepository.qtdPessoasNormaisAtendimento();
    }
    
    @RequestMapping(method=RequestMethod.GET, value = "/token/preferencial/qtdPessoasFila", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Integer getQtdTokenPreferencial(){
        return tokenRepository.qtdTokenPreferencialFila() - tokenRepository.qtdPessoasNPreferenciaisAtendimento();
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/token/{idToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Token> getTokenById(@PathVariable("idToken") Integer idToken) {
        return tokenRepository.findById(idToken);
    }
    
    
    @RequestMapping(method =RequestMethod.POST, value="/token")
    public void addAssunto(@RequestBody Token token){
        tokenRepository.save(token);
    }
    
    
    
}