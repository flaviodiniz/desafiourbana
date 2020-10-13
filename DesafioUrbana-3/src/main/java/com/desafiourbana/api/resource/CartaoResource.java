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
import com.desafiourbana.api.model.Cartao;
import com.desafiourbana.api.repository.filters.CartaoFilter;
import com.desafiourbana.api.repository.resumo.CartaoResumo;
import com.desafiourbana.api.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoResource {
	
	@Autowired
	private CartaoService cartaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CARTAO') and #oauth2.hasScope('read')")
	public ResponseEntity<?> listar() {
		List<Cartao> cartoes = cartaoService.listar();
		return !cartoes.isEmpty() ? ResponseEntity.ok(cartoes) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/all/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CARTAO') and #oauth2.hasScope('read')")
	public ResponseEntity<?> listar(@PathVariable Long id) {
		List<Cartao> cartoes = cartaoService.listarPorUsuario(id);
		return !cartoes.isEmpty() ? ResponseEntity.ok(cartoes) : ResponseEntity.noContent().build();
	}
	
	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CARTAO') and #oauth2.hasScope('read')")
	public Page<CartaoResumo> resumir(CartaoFilter cartaoFilter, Pageable pageable) {
		return cartaoService.resumir(cartaoFilter, pageable);
	}
	
	@PostMapping("/novo")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CARTAO') and #oauth2.hasScope('write')")
	public ResponseEntity<Cartao> criar(@RequestBody Cartao cartao, HttpServletResponse response) {
		Cartao cartaoSalvo = cartaoService.salvar(cartao);	
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cartaoSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cartaoSalvo);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CARTAO') and #oauth2.hasScope('read')")
	public ResponseEntity<Cartao> buscarPeloId(@PathVariable Long id) {
		Cartao cartao = cartaoService.buscarCartaoPeloId(id);
		return cartao != null ? ResponseEntity.ok(cartao) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/novo/{id}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CARTAO') and #oauth2.hasScope('write')")
	public ResponseEntity<Cartao> atualizar(@PathVariable Long id, @RequestBody Cartao cartao) {
		Cartao usuarioSalvo = cartaoService.atualizar(id, cartao);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CARTAO') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long id, @RequestBody Boolean status) {
		cartaoService.atualizarPropriedadeAtivo(id, status);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_CARTAO') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		cartaoService.remove(id);
	}

}
