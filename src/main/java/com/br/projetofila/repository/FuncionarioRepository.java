package com.br.projetofila.repository;

import com.br.projetofila.bean.Funcionario;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{
    
}
