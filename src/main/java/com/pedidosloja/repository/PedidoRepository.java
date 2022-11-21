package com.pedidosloja.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pedidosloja.model.Pedido;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
