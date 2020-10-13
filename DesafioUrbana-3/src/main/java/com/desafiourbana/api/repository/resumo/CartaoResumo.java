package com.desafiourbana.api.repository.resumo;

public class CartaoResumo {
	
	private Long id;
	private int numeroCartao;
	private String nome;
	private boolean status;
	
	public CartaoResumo(Long id, int numeroCartao, String nome, boolean status) {
		this.id = id;
		this.numeroCartao = numeroCartao;
		this.nome = nome;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
