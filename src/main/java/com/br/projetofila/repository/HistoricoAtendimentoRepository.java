package com.br.projetofila.repository;

import com.br.projetofila.bean.HistoricoAtendimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoAtendimentoRepository extends CrudRepository<HistoricoAtendimento, Integer>{
    
}
