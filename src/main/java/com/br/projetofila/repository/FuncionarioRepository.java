package com.br.projetofila.repository;

import com.br.projetofila.bean.Funcionario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{

    public Iterable<Funcionario> findAllById(Integer idFuncionario);

    
}
