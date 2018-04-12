package com.br.projetofila.repository;

import org.springframework.data.repository.CrudRepository;
import com.br.projetofila.bean.TipoToken;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTokenRepository extends CrudRepository<TipoToken, Integer>{
	
}
