package com.pedidosloja.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedidosloja.model.Cliente;
import com.pedidosloja.model.Frete;
import com.pedidosloja.model.ItensDoPedido;
import com.pedidosloja.model.Pedido;
import com.pedidosloja.repository.ClienteRepository;
import com.pedidosloja.repository.FreteRepository;
import com.pedidosloja.repository.ItensDoPedidoRepository;
import com.pedidosloja.repository.PedidoRepository;
import com.pedidosloja.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private FreteRepository freteRepository;
	@Autowired
	private ItensDoPedidoRepository itensDoPedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping(value = "/{codigo}", produces = "application/json")
	public ResponseEntity<Pedido> init(@PathVariable(value = "codigo") Long codigo) {
		Optional<Pedido> pedido = pedidoRepository.findById(codigo);
		return new ResponseEntity<Pedido>(pedido.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Pedido>> pedido() {
		List<Pedido> list = (List<Pedido>) pedidoRepository.findAll();
		return new ResponseEntity<List<Pedido>>(list, HttpStatus.OK);
	}
	/*-----------CADASTRANDO PEDIDO ATRELANDO AO CLIENTE E JÁ CALCULANDO O FRETE DE ACORDO COM A UF DO CLIENTE---------*/
	@PostMapping(value = "/codigo_cliente/{codigo_cliente}", produces = "application/json")
	public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido,
			@PathVariable(value = "codigo_cliente") Long codigo_cliente) {
		Optional<Cliente> cliente = clienteRepository.findById(codigo_cliente);
		pedido.setCliente(cliente.get());
		Frete frete = freteRepository.buscaPorUf(cliente.get().getUf());
		pedido.setValor_frete(frete.getValor_frete());
		Pedido pedidoAtualizado = pedidoRepository.save(pedido);
		return new ResponseEntity<Pedido>(pedidoAtualizado, HttpStatus.OK);
	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido) {
		Pedido pedidoAtualizado = pedidoRepository.save(pedido);
		return new ResponseEntity<Pedido>(pedidoAtualizado, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{codigo}", produces = "application/text")
	public String excluir(@PathVariable("codigo") Long codigo) {
		pedidoRepository.deleteById(codigo);
		return "Deletado com sucesso!";
	}

}
