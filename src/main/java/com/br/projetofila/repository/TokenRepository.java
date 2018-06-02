package com.br.projetofila.repository;

import org.springframework.data.repository.CrudRepository;
import com.br.projetofila.bean.Token;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer>{
	
	@Query(value = "SELECT ROUND(AVG(MINUTE(TIMEDIFF(a.data_inicio, t.data_retirada)))) " + 
			"FROM atendimento a " + 
			"JOIN token t " + 
			"ON a.token_id = t.id " + 
			"WHERE MINUTE(TIMEDIFF(CURRENT_TIMESTAMP(),a.data_inicio)) < 60 " + 
			"AND   DATE(a.data_inicio) = CURRENT_DATE() " + 
			"AND t.tipo_token_id = ?1", nativeQuery = true)
	public Integer getMediaTempoEsperaByTipo(String tipoToken);
	
	@Query(value = "SELECT COUNT(*) " + 
			"FROM token t " + 
			"LEFT JOIN atendimento a " + 
			"ON t.id = a.token_id " + 
			"WHERE a.id IS NULL " + 
			"AND tipo_token_id = ?1", nativeQuery = true)
    public Integer getQuantidadeTokensNormaisAguardando(String tipoToken);
	
	@Query(value = "SELECT MAX(senha) " + 
			"FROM token " + 
			"WHERE tipo_token_id = ?1 " + 
			"AND DATE(data_retirada) = DATE(NOW())", nativeQuery = true)
	public String getUltimaSenhaByTipo(String tipoToken);
    
    @Query("SELECT count(a)"
            + " FROM Atendimento a inner join a.token t WHERE t.tipoToken = 1")
    public Integer qtdPessoasNormaisAtendimento();
    

    @Query("SELECT count(a)"
            + " FROM Atendimento a inner join a.token t WHERE t.tipoToken = 2")
    public Integer qtdPessoasNPreferenciaisAtendimento();
    
    @Query("SELECT t FROM Token t WHERE t.tipoToken = 2")
    public ArrayList<Token> getTokensPreferencial();

    @Query("SELECT t FROM Token t WHERE t.tipoToken = 1")
    public ArrayList<Token> getTokensNormal();

    @Query("SELECT t.dataRetirada"
            + " FROM Atendimento a inner join a.token t WHERE t.tipoToken = 2")
    public ArrayList<Date> dataRetiradaPreferencialFila();
    
    @Query("SELECT a.dataInicio"
            + " FROM Atendimento a inner join a.token t WHERE t.tipoToken = 2")
    public ArrayList<Date> dataAtendimentoPreferencialFila();
    
    @Query("SELECT t.dataRetirada"
            + " FROM Atendimento a inner join a.token t WHERE t.tipoToken = 1")
    public ArrayList<Date> dataRetiradaNormalFila();
    
    @Query("SELECT a.dataInicio"
            + " FROM Atendimento a inner join a.token t WHERE t.tipoToken = 1")
    public ArrayList<Date> dataAtendimentoNormalFila();
    
    @Query("SELECT tk"
            + " FROM Token tk WHERE tk.id = ?1 AND tk.tipoToken = 1")
    public Token achaPosicaoFilaNormal(int id);
    
    @Query("SELECT tk"
            + " FROM Token tk WHERE tk.id = ?1 AND tk.tipoToken = 2")
    public Token achaPosicaoFilaPreferencial(int id);
}
