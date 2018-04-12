package com.br.projetofila.repository;

import com.br.projetofila.bean.StatusAtendimento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusAtendimentoRepository extends CrudRepository<StatusAtendimento, Integer>{
    
}
