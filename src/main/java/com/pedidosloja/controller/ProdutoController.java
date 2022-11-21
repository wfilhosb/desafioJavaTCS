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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pedidosloja.model.Produto;
import com.pedidosloja.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(value ="/{codigo}", produces = "application/json")
	public ResponseEntity<Produto> init(@PathVariable(value ="codigo") Long codigo) {
		Optional<Produto> produto = produtoRepository.findById(codigo);
		return new ResponseEntity<Produto>(produto.get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces ="application/json")
	public ResponseEntity<List<Produto>> produto(){
		List<Produto> list = (List<Produto>) produtoRepository.findAll();
		return new ResponseEntity<List<Produto>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value ="/", produces ="application/json")
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto){
		Produto produtoSalvar = produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produtoSalvar,HttpStatus.OK);
	}
	
	@PutMapping(value ="/", produces ="application/json")
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto){
		Produto produtoAtualizado = produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produtoAtualizado,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{codigo}", produces = "application/text")
	public String excluir (@PathVariable("codigo") Long codigo) {
		produtoRepository.deleteById(codigo);
		return "Deletado com sucesso!";
	}
}

