package com.br.projetofila.repository;

import org.springframework.data.repository.CrudRepository;
import com.br.projetofila.bean.AssuntoAtendimento;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoAtendimentoRepository extends CrudRepository<AssuntoAtendimento, Integer> {
    
}
