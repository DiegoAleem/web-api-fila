package com.br.projetofila.controller;

import java.text.ParseException;
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

import com.br.projetofila.bean.StatusAtendimento;
import com.br.projetofila.bean.Token;
import com.br.projetofila.factory.TimeFactory;
import com.br.projetofila.repository.StatusAtendimentoRepository;
import com.br.projetofila.repository.TokenRepository;
import com.br.projetofila.vo.SituacaoFilasVO;
import com.br.projetofila.vo.StatusTokenVO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

@RestController
public class TokenController {
    
    private final LinkedHashMap<String, Token> senhasAtendimentoNormais = new LinkedHashMap<>();
       
    private final LinkedHashMap<String, Token> senhasAtendimentoPreferenciais = new LinkedHashMap<>();
    
    @Autowired
    private TokenRepository tokenRepository;
    
    @Autowired
    private StatusAtendimentoRepository satAtendimentoRepository;
    
    
    @RequestMapping("/token")
    public @ResponseBody
    Iterable<Token> getAllTokens() {
        return tokenRepository.findAll();
    }
    
    
    @RequestMapping("/token/normal")
    public @ResponseBody
    LinkedHashMap<String, Token> getAllTokensNormal() {
        return senhasAtendimentoNormais;
    }
    
    @RequestMapping("/token/preferencial")
    public @ResponseBody
    LinkedHashMap<String, Token> getAllTokensPrefenciais() {
        return senhasAtendimentoPreferenciais;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/token/{idToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Token> getTokenById(@PathVariable("idToken") Integer idToken) {
        return tokenRepository.findById(idToken);
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/encerratoken/{senha}")
    public String encerraToken(@PathVariable("senha") String senha){
    	tokenRepository.changeStatusAtendimentoConcluidoBySenha(senha);
    	return "";
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/token")
    public ResponseEntity<Token> addNovoToken(@RequestBody Token novoToken){
        Integer ultimaSenhaNormal;
        Integer ultimaSenhaPreferencial;
    	if (novoToken.getTipoToken().getId() == 1) {
            ultimaSenhaNormal = tokenRepository.getUltimaSenhaByTipo("1");
			if (ultimaSenhaNormal == null) {
				novoToken.setSenha("1");
			}else {
				int senhaInt = ultimaSenhaNormal + 1;
				novoToken.setSenha(Integer.toString(senhaInt));
			}
    	}else {
            ultimaSenhaPreferencial = tokenRepository.getUltimaSenhaByTipo("2");
            if(ultimaSenhaPreferencial == null){
                novoToken.setSenha("P1");
            }
            else{
                int ultimaSenhaFormatada = ultimaSenhaPreferencial + 1;
                novoToken.setSenha("P"+Integer.toString(ultimaSenhaFormatada));
            }
    	}
    	
    	novoToken.setDataRetirada(TimeFactory.getCurrentTime());
    	novoToken.setStatusAtendimento(new StatusAtendimento(1, "AGUARDANDO"));
    	tokenRepository.save(novoToken); //Salva no banco
    	Token ultimoTokenInserido = getTokenById(novoToken.getId()).get();
    	
        if(novoToken.getTipoToken().getId() == 1) {
            senhasAtendimentoNormais.put(ultimoTokenInserido.getSenha(), ultimoTokenInserido);
        }else {
           senhasAtendimentoPreferenciais.put(ultimoTokenInserido.getSenha(), ultimoTokenInserido);
        }
        
        return new ResponseEntity<>(ultimoTokenInserido, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/status/{senha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<StatusTokenVO> getFilaBySenha(@PathVariable("senha") String senha) {
        StatusTokenVO status = new StatusTokenVO();
        Collection<Token> tokens = new ArrayList<Token>();
        Token tokenconfig = tokenRepository.getTokenBySenhaDiaAtual(senha);
        Integer pos = 0;
        Integer tempo = 0;
        
        if(senha.contains("P")){
            tempo = tokenRepository.getMediaTempoEsperaByTipo("2");
            tokens = senhasAtendimentoPreferenciais.values();
        } else {
            tempo = tokenRepository.getMediaTempoEsperaByTipo("1");
            tokens = senhasAtendimentoNormais.values();
        }
        
        if (tempo == null) {
        	tempo = 0;
        }
        
        for(Token token: tokens){
            pos++;
            if(token.getSenha().equals(senha))
                break;
        }
        
        if (pos == 0) {
        	status.setPosicaoFila(pos+1);
        }else {
        	status.setPosicaoFila(pos);
        }
 
        if(tempo.intValue() > 0)
            status.setTempoAtendimento(tempo * pos);
        else 
            status.setTempoAtendimento(0);
        
        if(pos < 3){
            if(status.getTempoAtendimento() <= 5)
                status.setStatus(satAtendimentoRepository.getDescricaoById("2"));
            else
                status.setStatus(satAtendimentoRepository.getDescricaoById("1"));
        }else {
            status.setStatus(tokenconfig.getStatusAtendimento().getDescricao());
        }
        status.setStatus(tokenconfig.getStatusAtendimento().getDescricao());
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

    
}