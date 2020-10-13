package com.desafiourbana.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafiourbana.api.model.Usuario;
import com.desafiourbana.api.repository.resumo.UsuarioRepositoryQuery;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryQuery {
	
	public Optional<Usuario> findByEmail(String email);	

}
