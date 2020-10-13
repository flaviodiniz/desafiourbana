package com.desafiourbana.api.repository.resumo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.desafiourbana.api.model.Cartao;
import com.desafiourbana.api.model.Cartao_;
import com.desafiourbana.api.repository.filters.CartaoFilter;

public class CartaoRepositoryImpl implements CartaoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Cartao> filtrar(CartaoFilter cartaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Cartao> criteria = builder.createQuery(Cartao.class);
		Root<Cartao> root = criteria.from(Cartao.class);
		Predicate[] predicates = criarRestricoes(cartaoFilter, builder, root);
		criteria.where(predicates);
		TypedQuery<Cartao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(cartaoFilter));
	}

	@Override
	public Page<CartaoResumo> resumir(CartaoFilter cartaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CartaoResumo> criteria = builder.createQuery(CartaoResumo.class);
		Root<Cartao> root = criteria.from(Cartao.class);
		criteria.select(builder.construct(CartaoResumo.class
				, root.get(Cartao_.id)
				, root.get(Cartao_.numeroCartao)
				, root.get(Cartao_.nome)
				, root.get(Cartao_.status)));
		
		Predicate[] predicates = criarRestricoes(cartaoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<CartaoResumo> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(cartaoFilter));
	}
	
	private Predicate[] criarRestricoes(CartaoFilter cartaoFilter, CriteriaBuilder builder,
			Root<Cartao> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(cartaoFilter.getNome())) {
			predicates.add(builder.like(
					builder.lower(root.get(Cartao_.nome)), "%" + cartaoFilter.getNome().toLowerCase() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(CartaoFilter cartaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Cartao> root = criteria.from(Cartao.class);
		Predicate[] predicates = criarRestricoes(cartaoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
