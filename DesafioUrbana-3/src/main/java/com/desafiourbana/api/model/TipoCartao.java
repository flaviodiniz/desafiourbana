package com.desafiourbana.api.model;

public enum TipoCartao {
	
	COMUM ("COMUM"),
	ESTUDANTE ("ESTUDANTE"),
	TRABALHADOR ("TRABALHADOR");
	
	private String tipoCartao;
		
	private TipoCartao(String tipoCartao) {
		this.tipoCartao = tipoCartao;
	}

	 public String getTipoCartao() {
		 return tipoCartao;
	 }

}
