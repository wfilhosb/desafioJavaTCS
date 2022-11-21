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
import com.pedidosloja.model.Cliente;
import com.pedidosloja.model.ConsultaCEP;
import com.pedidosloja.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(value ="/{codigo}", produces = "application/json")
	public ResponseEntity<Cliente> init(@PathVariable(value ="codigo") Long codigo) {
		Optional<Cliente> cliente = clienteRepository.findById(codigo);
		return new ResponseEntity<Cliente>(cliente.get(),HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces ="application/json")
	public ResponseEntity<List<Cliente>> cliente(){
		List<Cliente> list = (List<Cliente>) clienteRepository.findAll();
		return new ResponseEntity<List<Cliente>>(list,HttpStatus.OK);
	}
	
	@PostMapping(value ="/", produces ="application/json")
	public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) throws Exception{
		/*CONSUMINDO API DE CONSULTA DE CEP*/
		URL url = new URL("https://viacep.com.br/ws/"+cliente.getCep()+"/json/");
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		String cep = "";
		StringBuilder jsonCep = new StringBuilder();
		while ((cep = br.readLine()) != null) {
			jsonCep.append(cep);
		}
		ConsultaCEP consultaCEP = new Gson().fromJson(jsonCep.toString(), ConsultaCEP.class);
		cliente.setBairro(consultaCEP.getBairro());
		cliente.setCidade(consultaCEP.getLocalidade());
		cliente.setEndereco(consultaCEP.getLogradouro());
		cliente.setUf(consultaCEP.getUf());
		Cliente clienteSalvar = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(clienteSalvar,HttpStatus.OK);
	}
	
	@PutMapping(value ="/", produces ="application/json")
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente){
		Cliente clienteAtualizado = clienteRepository.save(cliente);
		return new ResponseEntity<Cliente>(clienteAtualizado,HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{codigo}", produces = "application/text")
	public String excluir (@PathVariable("codigo") Long codigo) {
		clienteRepository.deleteById(codigo);
		return "Deletado com sucesso!";
	}
}
