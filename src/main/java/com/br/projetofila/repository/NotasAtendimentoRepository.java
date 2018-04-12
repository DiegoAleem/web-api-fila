package com.br.projetofila.repository;

import com.br.projetofila.bean.NotasAtendimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasAtendimentoRepository extends CrudRepository<NotasAtendimento, Integer> {
    
}
