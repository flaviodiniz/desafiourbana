package com.desafiourbana.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.desafiourbana.api.model.Permissao;
import com.desafiourbana.api.model.Usuario;
import com.desafiourbana.api.repository.PermissaoRepository;
import com.desafiourbana.api.repository.UsuarioRepository;
import com.desafiourbana.api.repository.filters.UsuarioFilter;
import com.desafiourbana.api.repository.resumo.UsuarioResumo;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usarioRepository;
	
	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Usuario buscarPeloId(Long id) {
		Usuario usuarioSalvo = usarioRepository.findOne(id);
		if(usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return usuarioSalvo;	
	}
	
	public Usuario atualizar(Long id, Usuario usuario) {
		Usuario usuarioSalvo = buscarPeloId(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return usarioRepository.save(usuarioSalvo);
	}
	
	public List<Usuario> listar() {
		return usarioRepository.findAll();
	}
	
	public Usuario salvar(Usuario usuario) {
		return usarioRepository.save(usuario);
	}
	
	public void remove(Long id) {
		if (id != 1) {
			usarioRepository.delete(id);
		}
	}

	public Page<UsuarioResumo> resumir(UsuarioFilter usuarioFilter, Pageable pageable) {
		return usarioRepository.resumir(usuarioFilter, pageable);
	}
	
	public List<Permissao> listarPermissoes() {
		return permissaoRepository.findAll();
	}
	
	public List<Permissao> listarPermissoesDeLeitura() {
		List<Permissao> permissoes = permissaoRepository.findAll();
		List<Permissao> permissoesLeitura = new ArrayList<Permissao>();
		for(Permissao p: permissoes) {
			if (p.getId() == 3) {
				permissoesLeitura.add(p);
			}
			if (p.getId() == 6) {
				permissoesLeitura.add(p);
			}
		}
		return permissoesLeitura;
	}

}
