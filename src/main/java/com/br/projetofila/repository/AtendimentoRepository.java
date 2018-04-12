package com.br.projetofila.repository;

import com.br.projetofila.bean.Atendimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends CrudRepository<Atendimento, Integer> {
    
}
