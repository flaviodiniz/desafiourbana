package com.desafiourbana.api.repository.resumo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafiourbana.api.model.Cartao;
import com.desafiourbana.api.repository.filters.CartaoFilter;

public interface CartaoRepositoryQuery {
	
	public Page<Cartao> filtrar(CartaoFilter cartaoFilter, Pageable pageable);
	public Page<CartaoResumo> resumir(CartaoFilter cartaoFilter, Pageable pageable);

}
