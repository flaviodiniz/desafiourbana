package com.desafiourbana.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafiourbana.api.event.RecursoCriadoEvent;
import com.desafiourbana.api.model.Permissao;
import com.desafiourbana.api.model.Usuario;
import com.desafiourbana.api.repository.filters.UsuarioFilter;
import com.desafiourbana.api.repository.resumo.UsuarioResumo;
import com.desafiourbana.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<?> listar() {
		List<Usuario> usuarios = usuarioService.listar();
		return !usuarios.isEmpty() ? ResponseEntity.ok(usuarios) : ResponseEntity.noContent().build();
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public Page<UsuarioResumo> resumir(UsuarioFilter usuarioFilter, Pageable pageable) {
		return usuarioService.resumir(usuarioFilter, pageable);
	}
	
	@PostMapping("/novo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario, HttpServletResponse response) {
		//Adiciona as permissoes de leitura
		Usuario user = addPermissoesLeitura(usuario);
		
		// Criptografa a senha para ser realizado o login
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaCriptografada = encoder.encode(user.getSenha());
		user.setSenha(senhaCriptografada);
		
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getId()));	
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarPeloId(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscarPeloId(id);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/novo/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long id) {
		usuarioService.remove(id);
	}
	
	public Usuario addPermissoesLeitura(Usuario usuario) {
		List<Permissao> permissoes = usuarioService.listarPermissoesDeLeitura();
		usuario.setPermissoes(permissoes);
		return usuario;
	}

}
