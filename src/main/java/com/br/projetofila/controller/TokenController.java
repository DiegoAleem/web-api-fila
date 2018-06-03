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
import com.br.projetofila.vo.StatusTokenVO;
import java.util.Collection;
import java.util.LinkedHashMap;

@RestController
public class TokenController {
    
    private LinkedHashMap<Integer, Token> senhasAtendimento = new LinkedHashMap<>();
	
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
        String ultimaSenhaNormal = "";
        String ultimaSenhaPreferencial = "";
    	if (novoToken.getTipoToken().getId() == 1) {
            ultimaSenhaNormal = tokenRepository.getUltimaSenhaByTipo("1");
		if (ultimaSenhaNormal == null) {
			novoToken.setSenha("1");
		}else {
			int senhaInt = Integer.parseInt(ultimaSenhaNormal);
			novoToken.setSenha(Integer.toString(++senhaInt));
		}
    	}else {
            int ultimaSenhaFormatada;
            ultimaSenhaPreferencial = ultimaSenhaPreferencial = tokenRepository.getUltimaSenhaByTipo("2");
            if(ultimaSenhaPreferencial == null){
                novoToken.setSenha("P1");
            }
            else{
                ultimaSenhaFormatada = Integer.parseInt(ultimaSenhaPreferencial.substring(ultimaSenhaPreferencial.indexOf("P")+1));
                novoToken.setSenha("P"+Integer.toString(++ultimaSenhaFormatada));
            }
    	}
    	//novoToken.setSenha("0");
    	novoToken.setDataRetirada(TimeFactory.getCurrentTime());
    	tokenRepository.save(novoToken); //Salva no banco
    	Token ultimoTokenInserido = getTokenById(novoToken.getId()).get(); 
    	senhasAtendimento.put(ultimoTokenInserido.getId(), ultimoTokenInserido);
        return new ResponseEntity<Token>(ultimoTokenInserido, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/status/{senha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<StatusTokenVO> getFilaBySenha(@PathVariable("senha") String senha) {
        StatusTokenVO status = new StatusTokenVO();
        Collection<Token> tokens = senhasAtendimento.values();
        int pos = 0;
        int tempo = 0;
        try{
            if(senha.contains("P"))
                tempo = tokenRepository.getMediaTempoEsperaByTipo("2");
            else
                tempo = tokenRepository.getMediaTempoEsperaByTipo("1");
        }
        catch(NullPointerException e){
            System.out.println(e);
        }
        for(Token token: tokens){
            pos++;
            if(token.getSenha().equals(senha))
                break;
        }
        status.setPosicaoFila(pos);
        if(tempo > 0)
            status.setTempoAtendimento(tempo * pos);
        else 
            status.setTempoAtendimento(0);
        status.setStatus("NENHUM");
        return new ResponseEntity<>(status, HttpStatus.OK);
    }
    
    @RequestMapping("/status")
    public ResponseEntity<SituacaoFilasVO> getFila() throws ParseException {
    	SituacaoFilasVO situacao = new SituacaoFilasVO(0,0,0,0);
    	
    	// NORMAL
    	situacao.setTempoEsperaNormal(tokenRepository.getMediaTempoEsperaByTipo("1"));
    	if (situacao.getTempoEsperaNormal() == null) {
			situacao.setTempoEsperaNormal(0);
		}
    	situacao.setQtdPessoasNormal(tokenRepository.getQuantidadeTokensAguardando("1"));
    	
    	// PREFERENCIAL
    	situacao.setTempoEsperaPreferencial(tokenRepository.getMediaTempoEsperaByTipo("2"));
    	if (situacao.getTempoEsperaPreferencial() == null) {
			situacao.setTempoEsperaPreferencial(0);
		}
    	situacao.setQtdPessoasPreferencial(tokenRepository.getQuantidadeTokensAguardando("2"));
    	
        return new ResponseEntity<SituacaoFilasVO>(situacao, HttpStatus.OK);
    }
    
    
    public Token getNovo(){
        Token novo = new Token();
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