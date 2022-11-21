package com.pedidosloja.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pedidosloja.model.Frete;

@Repository
public interface FreteRepository extends CrudRepository<Frete, Long>{
	@Query(value = "select f from Frete f where f.uf like %?1%")
	Frete buscaPorUf(String uf);
}
