package com.pedidosloja.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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

import com.google.gson.Gson;
import com.pedidosloja.model.Frete;
import com.pedidosloja.model.ConsultaCEP;
import com.pedidosloja.repository.FreteRepository;

@RestController
@RequestMapping(value = "/frete")
public class FreteController {
	
	@Autowired
	private FreteRepository freteRepository;
	
	@GetMapping(value ="/{codigo}", produces = "application/json")
	public ResponseEntity<Frete> init(@PathVariable(value ="codigo") Long codigo) {
		Optional<Frete> frete = freteRepository.findById(codigo);
		return new ResponseEntity<Frete>(frete.get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces ="application/json")
	public ResponseEntity<List<Frete>> frete(){
		List<Frete> list = (List<Frete>) freteRepository.findAll();
		return new ResponseEntity<List<Frete>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value ="/", produces ="application/json")
	public ResponseEntity<List<Frete>> cadastrar(@RequestBody List<Frete> frete){
		List<Frete> freteSalvar = (List<Frete>) freteRepository.saveAll(frete);
		return new ResponseEntity<List<Frete>>(freteSalvar,HttpStatus.OK);
	}
	
	@PutMapping(value ="/", produces ="application/json")
	public ResponseEntity<Frete> atualizar(@RequestBody Frete frete){
		Frete freteAtualizado = freteRepository.save(frete);
		return new ResponseEntity<Frete>(freteAtualizado,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{codigo}", produces = "application/text")
	public String excluir (@PathVariable("codigo") Long codigo) {
		freteRepository.deleteById(codigo);
		return "Deletado com sucesso!";
	}
}
