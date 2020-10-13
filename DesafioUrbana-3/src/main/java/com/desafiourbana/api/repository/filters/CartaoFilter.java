package com.desafiourbana.api.repository.filters;

public class CartaoFilter {
	
	private int numeroCartao;
	private String nome;
	private boolean status;
	
	public int getNumeroCartao() {
		return numeroCartao;
	}
	
	public void setNumeroCartao(int numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}

}
