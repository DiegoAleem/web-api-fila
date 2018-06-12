package com.br.projetofila.repository;

import com.br.projetofila.bean.Funcionario;
import com.br.projetofila.vo.FuncionarioVO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer>{

    public Iterable<Funcionario> findAllById(Integer idFuncionario);
    
    @Query(value="SELECT new com.br.projetofila.vo.FuncionarioVO(f.nome, f.matricula, f.senha, f.tipoFuncionario.cargo) FROM Funcionario f WHERE f.matricula=?1")
    public FuncionarioVO findFuncionario(@RequestParam("matricula") String matricula);
    
}
