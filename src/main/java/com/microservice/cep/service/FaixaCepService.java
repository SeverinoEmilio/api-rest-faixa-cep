package com.microservice.cep.service;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.cep.controller.dto.FaixaCepDTO;
import com.microservice.cep.controller.dto.InfoLojaDTO;
import com.microservice.cep.model.FaixaCep;
import com.microservice.cep.model.ResponseGeneric;
import com.microservice.cep.repository.FaixaCepRepository;

@Service
public class FaixaCepService {
	
	@Autowired
	private FaixaCepRepository faixaCepRepository;

	public ResponseGeneric realizarCadastro(FaixaCepDTO faixaCepDTO) {
		ResponseGeneric resposta = new ResponseGeneric();	
		
		FaixaCep faixaCep = faixaCepDTO.converter();
		
		if(faixaCepDTO.getFaixaInicio() >= faixaCepDTO.getFaixaFim()) {
			resposta.setResultado(null);
			resposta.setMensagem("erro! a faixa inicio não pode ser maior ou igual que a faixa fim ");
			return resposta;
		}
		
		FaixaCep isConflito = verificarConflitoDeFaixaCEP(faixaCepDTO);
		if(isConflito != null) {
			resposta.setResultado(null);
			resposta.setMensagem("erro! essa faixa de CEP conflita com a faixa de CEP "+isConflito.getCodigoLoja());
			return resposta;
		}
		
		FaixaCep faixaCriado = this.faixaCepRepository.save(faixaCep);
		resposta.setResultado(faixaCriado);
		resposta.setMensagem("Faixa Cadastrado com sucesso !!!");
		return resposta;
	}

	private FaixaCep verificarConflitoDeFaixaCEP(FaixaCepDTO faixaCepDTO) {
		
		List<FaixaCep> listaFaixaCep = (List<FaixaCep>) this.faixaCepRepository.findAll();
		
		for(FaixaCep faixa: listaFaixaCep) {
			if( faixaCepDTO.getFaixaInicio() >= faixa.getFaixaInicio() && faixaCepDTO.getFaixaInicio() <= faixa.getFaixaFim())
				return faixa;
		}
		
		return null;	
	}

	public ResponseGeneric buscarLojaPorCEP(int cep) {
	
		ResponseGeneric resposta = new ResponseGeneric();
		InfoLojaDTO infoLoja = new InfoLojaDTO();
		List<FaixaCep> listaFaixaCep = (List<FaixaCep>) this.faixaCepRepository.findAll();	
			
		for(FaixaCep faixa: listaFaixaCep) {	
			if( cep >= faixa.getFaixaInicio() && cep <= faixa.getFaixaFim()) {
				System.out.println(faixa.getCodigoLoja());
				infoLoja.setCodigoLoja(faixa.getCodigoLoja());
			}
		}
		
		System.out.println(infoLoja);
		if(infoLoja.getCodigoLoja() == null) {
			resposta.setResultado(infoLoja);
			resposta.setMensagem("Esse CEP naõ pertence em nunhuma faixa de CEP");	
			return resposta;
		}
			
		resposta.setResultado(infoLoja);
		resposta.setMensagem("Loja encontrado com sucesso!!!");	
		return resposta;
		
		
	}

	public ResponseGeneric realizarEdicao(Long id, FaixaCepDTO faixaCepDTO) {
		ResponseGeneric resposta = new ResponseGeneric();
		Optional<FaixaCep> faixaCep = faixaCepRepository.findById(id);
		
		if(faixaCep.isPresent()) {
			
			if(faixaCepDTO.getFaixaInicio() >= faixaCepDTO.getFaixaFim()) {
				resposta.setResultado(null);
				resposta.setMensagem("erro! a faixa inicio não pode ser maior ou igual que a faixa fim ");
				return resposta;
			}
			
			FaixaCep isConflito = verificarConflitoDeFaixaCEP(faixaCepDTO);
			if(isConflito != null) {
				resposta.setResultado(null);
				resposta.setMensagem("erro! essa faixa de CEP conflita com a faixa de CEP "+isConflito.getCodigoLoja());
				return resposta;
			}
			
				
			FaixaCep faixaCepModel = faixaCepRepository.getById(id);
			faixaCepModel.setCodigoLoja(faixaCepDTO.getCodigoLoja());
			faixaCepModel.setFaixaInicio(faixaCepDTO.getFaixaInicio());	
			faixaCepModel.setFaixaFim(faixaCepDTO.getFaixaFim());

			resposta.setResultado(faixaCepRepository.save(faixaCepModel));
			resposta.setMensagem("Faixa de CEP editado com sucesso ");
			return resposta;			
		}
		
		resposta.setResultado(null);
		resposta.setMensagem("erro! Não existe faixa de CEP com esse id");
		return resposta;
		
	}

	public ResponseGeneric realizarExclusao(Long id) {
		
		ResponseGeneric resposta = new ResponseGeneric();
		
		Optional<FaixaCep> faixaCep = faixaCepRepository.findById(id);
		if(faixaCep.isPresent()) {
			
			faixaCepRepository.deleteById(id);
			resposta.setResultado(null);
			resposta.setMensagem("Faixa de CEP excluido com sucesso !!!");
			return resposta;	
		}
		
		resposta.setResultado(null);
		resposta.setMensagem("erro! Não existe faixa de CEP com esse id");
		return resposta;
		
	}
	
	
	

}
