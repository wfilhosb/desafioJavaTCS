package com.pedidosloja.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pedidosloja.model.ItensDoPedido;
import com.pedidosloja.model.Pedido;
import com.pedidosloja.model.Produto;
import com.pedidosloja.repository.ItensDoPedidoRepository;
import com.pedidosloja.repository.PedidoRepository;
import com.pedidosloja.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/itensDoPedido")
public class ItensDoPedidoController {

	@Autowired
	private ItensDoPedidoRepository itensDoPedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping(value = "/{codigo}", produces = "application/json")
	public ResponseEntity<ItensDoPedido> init(@PathVariable(value = "codigo") Long codigo) {
		Optional<ItensDoPedido> itensDoPedido = itensDoPedidoRepository.findById(codigo);
		return new ResponseEntity<ItensDoPedido>(itensDoPedido.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<ItensDoPedido>> itensDoPedido() {
		List<ItensDoPedido> list = (List<ItensDoPedido>) itensDoPedidoRepository.findAll();
		return new ResponseEntity<List<ItensDoPedido>>(list, HttpStatus.OK);
	}

	/*----------------------ATRELA UM PRODUTO A UM PEDIDO E CALCULA O VALOR TOTAL (QUANTIDADE*VALOR UNITÁRIO)---------------------*/
	@PostMapping(value = "/codigo_produto/{codigo_produto}/codigo_pedido/{codigo_pedido}", produces = "application/json")
	public ResponseEntity<ItensDoPedido> cadastrar(@RequestBody ItensDoPedido itensDoPedido,
			@PathVariable(value = "codigo_produto") Long codigo_produto,
			@PathVariable(value = "codigo_pedido") Long codigo_pedido) {
		Optional<Produto> produto = produtoRepository.findById(codigo_produto);
		Optional<Pedido> pedido = pedidoRepository.findById(codigo_pedido);
		itensDoPedido.setProduto(produto.get());
		itensDoPedido.setPedido(pedido.get());
		itensDoPedido.setValor_total(itensDoPedido.getQuantidade() * produto.get().getValor_unitario());
		ItensDoPedido itensDoPedidoSalvar = itensDoPedidoRepository.save(itensDoPedido);
		return new ResponseEntity<ItensDoPedido>(itensDoPedidoSalvar, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{codigo}", produces = "application/text")
	public String excluir(@PathVariable("codigo") Long codigo) {
		itensDoPedidoRepository.deleteById(codigo);
		return "Deletado com sucesso!";
	}
}
