package com.desafiourbana.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiourbana.api.model.Cartao;
import com.desafiourbana.api.repository.resumo.CartaoRepositoryQuery;

public interface CartaoRepository extends JpaRepository<Cartao, Long>, CartaoRepositoryQuery {

}
