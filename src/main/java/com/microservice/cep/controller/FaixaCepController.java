package com.microservice.cep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.cep.controller.dto.FaixaCepDTO;
import com.microservice.cep.model.ResponseGeneric;
import com.microservice.cep.service.FaixaCepService;

@RestController
@RequestMapping("/faixaCep")
public class FaixaCepController {
	
	@Autowired
	private FaixaCepService faixaCepService;

	@PostMapping
	public ResponseEntity<ResponseGeneric> cadastrarFaixaCepe(@RequestBody  FaixaCepDTO faixaCepDTO) {		
		System.out.println(faixaCepDTO);
		ResponseGeneric resposta = this.faixaCepService.realizarCadastro(faixaCepDTO);		
		return ResponseEntity.ok(resposta);
	}
	
	@GetMapping("/{cep}")
	public ResponseEntity<ResponseGeneric> retornarLojaPorCEP(@PathVariable int cep){
		ResponseGeneric resposta = faixaCepService.buscarLojaPorCEP(cep);
		return ResponseEntity.ok(resposta);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseGeneric> editarFaixaCEP(@PathVariable Long id, @RequestBody FaixaCepDTO faixaCepDTO){
		
		ResponseGeneric resposta = faixaCepService.realizarEdicao(id, faixaCepDTO);
		
		return ResponseEntity.ok(resposta);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseGeneric> excluirFaixaCEP(@PathVariable Long id) {
		ResponseGeneric resposta = faixaCepService.realizarExclusao(id);	
		return ResponseEntity.ok(resposta);
		
	}
	
	
}
