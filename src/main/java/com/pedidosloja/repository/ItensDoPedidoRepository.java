package com.pedidosloja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pedidosloja.model.Frete;
import com.pedidosloja.model.ItensDoPedido;

@Repository
public interface ItensDoPedidoRepository extends CrudRepository<ItensDoPedido, Long>{
	//@Query(value = "select i from ItensDoPedido i where i.pedido_codigo == ?1)
	//List<ItensDoPedido> buscaItensDoPedido(String codigo_pedido);
}
