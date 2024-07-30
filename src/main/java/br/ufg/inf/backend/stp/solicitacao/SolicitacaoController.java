package br.ufg.inf.backend.stp.solicitacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoService service;

	@GetMapping
	public List<Solicitacao> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Solicitacao obter(@PathParam(value = "id") Long solicitacaoId) {
		return service.obter(solicitacaoId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long solicitacaoId) {
		service.remover(solicitacaoId);
	}

	@PostMapping
	public Solicitacao adicionar(@RequestBody Solicitacao solicitacao) {
		return service.salvar(solicitacao);
	}

	@PutMapping("/{id}")
	public Solicitacao atualizar(@PathParam(value = "id") Long solicitacaoId, @RequestBody Solicitacao solicitacao) {
		return service.salvar(solicitacaoId, solicitacao);
	}
	
}
