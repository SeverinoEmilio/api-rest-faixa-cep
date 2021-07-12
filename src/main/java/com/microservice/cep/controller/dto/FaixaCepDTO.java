package com.microservice.cep.controller.dto;

import com.microservice.cep.model.FaixaCep;

public class FaixaCepDTO {
	
	private String codigoLoja;
	private int faixaInicio;
	private int faixaFim;
	
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
	public FaixaCep converter() {
		// TODO Auto-generated method stub
		return new FaixaCep(codigoLoja, faixaInicio, faixaFim);
	}
	
	
}
