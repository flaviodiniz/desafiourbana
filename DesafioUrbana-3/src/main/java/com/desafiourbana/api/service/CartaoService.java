package com.desafiourbana.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafiourbana.api.model.Cartao;
import com.desafiourbana.api.repository.CartaoRepository;
import com.desafiourbana.api.repository.filters.CartaoFilter;
import com.desafiourbana.api.repository.resumo.CartaoResumo;

@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	public Cartao buscarCartaoPeloId(Long id) {
		Cartao cartaoSalvo = cartaoRepository.getOne(id);
		if(cartaoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return cartaoSalvo;	
	}
	
	public Cartao atualizar(Long id, Cartao cartao) {
		Cartao cartaoSalvo = buscarCartaoPeloId(id);
		BeanUtils.copyProperties(cartao, cartaoSalvo, "id");
		return cartaoRepository.save(cartaoSalvo);
	}
	
	public void atualizarPropriedadeAtivo(Long id, Boolean status) {
		Cartao cartaoSalvo = buscarCartaoPeloId(id);
		cartaoSalvo.setStatus(status);
		cartaoRepository.save(cartaoSalvo);
	}
	
	public List<Cartao> listarPorUsuario(Long id) {
		List<Cartao> todosCartoes = listar();
		List<Cartao> cartoesUsuario = new ArrayList<Cartao>();
		for(Cartao c: todosCartoes) {
			if (c.getUsuario().getId() == (id)) {
				cartoesUsuario.add(c);
			}
		}
		return cartoesUsuario;
	}
	
	public List<Cartao> listar() {
		return cartaoRepository.findAll();
	}
	
	public Cartao salvar(Cartao cartao) {
		return cartaoRepository.save(cartao);
	}
	
	public void remove(Long id) {
		cartaoRepository.delete(id);
	}

	public Page<CartaoResumo> resumir(CartaoFilter cartaoFilter, Pageable pageable) {
		return cartaoRepository.resumir(cartaoFilter, pageable);
	}

}
