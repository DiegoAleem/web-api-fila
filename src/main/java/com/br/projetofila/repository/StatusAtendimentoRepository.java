package com.br.projetofila.repository;

import com.br.projetofila.bean.StatusAtendimento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusAtendimentoRepository extends CrudRepository<StatusAtendimento, Integer>{
     
	@Query(value = "SELECT descricao " + 
			"FROM StatusAtendimento WHERE"
                    +   " id = ?1", nativeQuery = true)
         public String getDescricaoById(String id);
}
