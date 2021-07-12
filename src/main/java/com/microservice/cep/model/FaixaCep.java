package com.microservice.cep.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
public class FaixaCep {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String codigoLoja;
	private int faixaInicio;
	private int faixaFim;
	
	public FaixaCep() {
		
	}
	
	
	
	public FaixaCep(String codigoLoja, int faixaInicio, int faixaFim) {
		this.codigoLoja = codigoLoja;
		this.faixaInicio = faixaInicio;
		this.faixaFim = faixaFim;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodigoLoja() {
		return codigoLoja;
	}
	public void setCodigoLoja(String codigoLoja) {
		this.codigoLoja = codigoLoja;
	}
	public int getFaixaInicio() {
		return faixaInicio;
	}
	public void setFaixaInicio(int faixaInicio) {
		this.faixaInicio = faixaInicio;
	}
	public int getFaixaFim() {
		return faixaFim;
	}
	public void setFaixaFim(int faixaFim) {
		this.faixaFim = faixaFim;
	}
	
	
	


}
