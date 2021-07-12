package com.microservice.cep.model;

public class ResponseGeneric {
	private Object resultado;
	private String mensagem;
	private int status;
	public Object getResultado() {
		return resultado;
	}
	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	

}
