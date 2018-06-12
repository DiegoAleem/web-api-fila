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
import java.util.LinkedHashMap;

@RestController
public class TokenController {
    
    private final LinkedHashMap<Integer, Token> senhasAtendimentoNormais = new LinkedHashMap<>();
       
    private final LinkedHashMap<Integer, Token> senhasAtendimentoPreferenciais = new LinkedHashMap<>();
    
    @Autowired
    private TokenRepository tokenRepository;
    
    @Autowired
    private StatusAtendimentoRepository satAtendimentoRepository;
    
    
    @RequestMapping("/token")
    public @ResponseBody
    Iterable<Token> getAllTokens() {
        LinkedHashMap<Integer, Token> senhasAtendimento = new LinkedHashMap<>();
        for(int i = 0; i <= senhasAtendimentoNormais.size();i++ )
            senhasAtendimento.put(senhasAtendimentoNormais.get(i).getId(), senhasAtendimentoNormais.get(i));
        
        for(int i = 0; i <= senhasAtendimentoPreferenciais.size();i++ )
            senhasAtendimento.put(senhasAtendimentoPreferenciais.get(i).getId(), senhasAtendimentoPreferenciais.get(i));
        
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
            ultimaSenhaPreferencial = tokenRepository.getUltimaSenhaByTipo("2");
            if(ultimaSenhaPreferencial == null){
                novoToken.setSenha("P1");
            }
            else{
                ultimaSenhaFormatada = Integer.parseInt(ultimaSenhaPreferencial.substring(ultimaSenhaPreferencial.indexOf("P")+1));
                novoToken.setSenha("P"+Integer.toString(++ultimaSenhaFormatada));
            }
    	}
    	
    	novoToken.setDataRetirada(TimeFactory.getCurrentTime());
    	// ESSA LINHA
    	novoToken.setStatusAtendimento(new StatusAtendimento(1, "AGUARDANDO"));
    	tokenRepository.save(novoToken); //Salva no banco
    	Token ultimoTokenInserido = getTokenById(novoToken.getId()).get();
        if(novoToken.getTipoToken().getId() == 1)
            senhasAtendimentoNormais.put(ultimoTokenInserido.getId(), ultimoTokenInserido);
        else
           senhasAtendimentoPreferenciais.put(ultimoTokenInserido.getId(), ultimoTokenInserido);
        return new ResponseEntity<>(ultimoTokenInserido, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/status/{senha}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<StatusTokenVO> getFilaBySenha(@PathVariable("senha") String senha) {
        StatusTokenVO status = new StatusTokenVO();
        ArrayList<Token> tokens = new ArrayList<>();
        Token tokenconfig = tokenRepository.getTokenBySenhaDiaAtual(senha);
        int pos = 0;
        int tempo = 0;
        try{
            if(senha.contains("P")){
                tempo = tokenRepository.getMediaTempoEsperaByTipo("2");
                tokens = (ArrayList<Token>) senhasAtendimentoPreferenciais.values();
            }
            else{
                tempo = tokenRepository.getMediaTempoEsperaByTipo("1");
                tokens = (ArrayList<Token>) senhasAtendimentoNormais.values();
            }
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
        if(tokenconfig.getStatusAtendimento().getId() < 3){
            if(status.getTempoAtendimento() <= 5)
                status.setStatus(satAtendimentoRepository.getDescricaoById("2"));
            else
                status.setStatus(satAtendimentoRepository.getDescricaoById("1"));
        }
        else
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