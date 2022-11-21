package com.pedidosloja.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pedidosloja.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
