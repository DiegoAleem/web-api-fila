package com.br.projetofila.repository;

import com.br.projetofila.bean.TipoFuncionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoFuncionarioRepository extends CrudRepository<TipoFuncionario, Integer>{
    
}
