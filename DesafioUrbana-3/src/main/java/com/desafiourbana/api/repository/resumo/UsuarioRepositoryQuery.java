package com.desafiourbana.api.repository.resumo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafiourbana.api.model.Usuario;
import com.desafiourbana.api.repository.filters.UsuarioFilter;

public interface UsuarioRepositoryQuery {
	
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);
	public Page<UsuarioResumo> resumir(UsuarioFilter usuarioFilter, Pageable pageable);

}
