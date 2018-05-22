package com.br.projetofila.controller;

import com.br.projetofila.bean.Fila;
import com.br.projetofila.bean.Funcionario;
import com.br.projetofila.bean.HoraMinuto;
import com.br.projetofila.bean.TipoToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.projetofila.bean.Token;
import com.br.projetofila.repository.TipoTokenRepository;
import com.br.projetofila.repository.TokenRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.joda.time.DateTime;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    
    @RequestMapping("/fila")
    public @ResponseBody
    Fila getFila() throws ParseException {
        Fila fila = new Fila();
        fila.setQtdPessoasNormal(getQtdTokenNormais());
        fila.setQtdPessoasPreferencial(getQtdTokenPreferencial());
        fila.setTempoEsperaGeralNormal(getMediaTempoTokenNormal());
        fila.setTempoEsperaGeralPreferencial(getMediaTempoTokenPreferencial());
        return fila;
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
    
    /**
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping(method=RequestMethod.GET, value = "/token/preferencial/mediaTempoGeral", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HoraMinuto getMediaTempoTokenPreferencial() throws ParseException{
      ArrayList<Date> datasRetiradas = tokenRepository.dataRetiradaPreferencialFila();
      ArrayList<Date> datasAtendimento = tokenRepository.dataAtendimentoPreferencialFila();
      return calculaHoraMinuto(datasRetiradas, datasAtendimento);
      
    }
    
    /**
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping(method=RequestMethod.GET, value = "/token/normal/mediaTempoGeral", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HoraMinuto getMediaTempoTokenNormal() throws ParseException{
       ArrayList<Date> datasRetiradas = tokenRepository.dataRetiradaNormalFila();
       ArrayList<Date> datasAtendimento = tokenRepository.dataAtendimentoNormalFila();
       return calculaHoraMinuto(datasRetiradas, datasAtendimento);
      
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/token/normal/mediaTempoGeral/{idToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HoraMinuto getTempoEsperaPessoaNormal(@PathVariable("idToken") Integer idToken) throws ParseException{
        Token tk = tokenRepository.achaPosicaoFilaNormal(idToken);
        ArrayList<Token> tokens = tokenRepository.getTokensNormal();
        
        ArrayList<Date> datasRetiradas = tokenRepository.dataRetiradaNormalFila();
        ArrayList<Date> datasAtendimento = tokenRepository.dataAtendimentoNormalFila();
       
       int pos = 0;
        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i) == tk){
                pos++;
                break;
            }
             pos++;   
        }
        return esperaNormalHoraMinuto(datasRetiradas, datasAtendimento, pos);
  
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/token/preferencial/mediaTempoGeral/{idToken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    HoraMinuto getTempoEsperaPessoaPreferencial(@PathVariable("idToken") Integer idToken) throws ParseException{
        Token tk = tokenRepository.achaPosicaoFilaPreferencial(idToken);
        ArrayList<Token> tokens = tokenRepository.getTokensNormal();
       
        ArrayList<Date> datasRetiradas = tokenRepository.dataRetiradaPreferencialFila();
        ArrayList<Date> datasAtendimento = tokenRepository.dataAtendimentoPreferencialFila();
       
        int pos = 0;
        for(int i = 0; i < tokens.size(); i++){
            if(tokens.get(i) == tk){
                pos++;
                break;
            }
             pos++;   
        }
        return esperaNormalHoraMinuto(datasRetiradas, datasAtendimento, pos);
    }
    
    public HoraMinuto esperaNormalHoraMinuto(ArrayList<Date> datasRetiradas ,ArrayList<Date> datasAtendimento, int qtd) throws ParseException{
       ArrayList<String> hrsMimRetirada = new ArrayList();
       ArrayList<String> hrsMimAtendimento = new ArrayList();
       ArrayList<DateTime> tempoRetirada = new ArrayList();
       ArrayList<DateTime> tempoAtendimento = new ArrayList();
       int media = 0, hora, min;
       
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
   
       for(int i=0; i<datasRetiradas.size();i++){
           hrsMimRetirada.add(sdf.format(datasRetiradas.get(i)));
           tempoRetirada.add(new DateTime(sdf.parse(hrsMimRetirada.get(i))));
           
           hrsMimAtendimento.add(sdf.format(datasAtendimento.get(i)));
           tempoAtendimento.add(new DateTime(sdf.parse(hrsMimAtendimento.get(i))));
           media += (tempoAtendimento.get(i).getMinuteOfDay() - tempoRetirada.get(i).getMinuteOfDay());
       }
       media = (media/datasRetiradas.size());
       media = media * qtd;
       hora = media / 60;
       min = media % 60;
       HoraMinuto tempo = new HoraMinuto(hora, min);
       return tempo; 
    }
    
    public HoraMinuto calculaHoraMinuto(ArrayList<Date> datasRetiradas ,ArrayList<Date> datasAtendimento) throws ParseException{
       ArrayList<String> hrsMimRetirada = new ArrayList();
       ArrayList<String> hrsMimAtendimento = new ArrayList();
       ArrayList<DateTime> tempoRetirada = new ArrayList();
       ArrayList<DateTime> tempoAtendimento = new ArrayList();
       int media = 0, hora, min;
       
       SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
   
       for(int i=0; i<datasRetiradas.size();i++){
           hrsMimRetirada.add(sdf.format(datasRetiradas.get(i)));
           tempoRetirada.add(new DateTime(sdf.parse(hrsMimRetirada.get(i))));
           
           hrsMimAtendimento.add(sdf.format(datasAtendimento.get(i)));
           tempoAtendimento.add(new DateTime(sdf.parse(hrsMimAtendimento.get(i))));
           media += (tempoAtendimento.get(i).getMinuteOfDay() - tempoRetirada.get(i).getMinuteOfDay());
       }
       media = (media/datasRetiradas.size());
       hora = media / 60;
       min = media % 60;
       HoraMinuto tempo = new HoraMinuto(hora, min);
       return tempo; 
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