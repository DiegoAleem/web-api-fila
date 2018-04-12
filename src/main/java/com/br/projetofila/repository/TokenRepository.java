package com.br.projetofila.repository;

import com.br.projetofila.bean.TipoToken;
import org.springframework.data.repository.CrudRepository;
import com.br.projetofila.bean.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer>{
    
    @Query("SELECT count(t) FROM Token t WHERE t.tipoToken = 1")
    public Integer qtdTokenNormaisFila();
    
    @Query("SELECT count(t) FROM Token t WHERE t.tipoToken = 2")
    public Integer qtdTokenPreferencialFila();
    
    @Query("SELECT DATEDIFF(t.dataRetirada,a.dataInicio)"
            + " FROM Atendimento a INNER JOIN a.token t WHERE  t = 2")
    public Float mediaChamadaPreferencialFila();
    
}
