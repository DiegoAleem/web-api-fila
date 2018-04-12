package com.br.projetofila.repository;

import com.br.projetofila.bean.CriteriosAvaliacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriosAvaliacaoRepository  extends CrudRepository<CriteriosAvaliacao, Integer>{
    
}
