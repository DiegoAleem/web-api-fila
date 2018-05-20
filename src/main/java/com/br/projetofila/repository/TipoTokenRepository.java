package com.br.projetofila.repository;

import org.springframework.data.repository.CrudRepository;
import com.br.projetofila.bean.TipoToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTokenRepository extends CrudRepository<TipoToken, Integer>{
	
    @Query("SELECT tk"
            + " FROM TipoToken tk WHERE tk.id = 1")
    public TipoToken getTipoTokenNormal();
    
     @Query("SELECT tk"
            + " FROM TipoToken tk WHERE tk.id = 2")
    public TipoToken getTipoTokenPreferencial();
}
