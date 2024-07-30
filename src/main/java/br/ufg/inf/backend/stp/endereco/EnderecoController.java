package br.ufg.inf.backend.stp.endereco;

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
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@GetMapping
	public List<Endereco> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Endereco obter(@PathParam(value = "id") Long enderecoId) {
		return service.obter(enderecoId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long enderecoId) {
		service.remover(enderecoId);
	}

	@PostMapping
	public Endereco adicionar(@RequestBody Endereco endereco) {
		return service.salvar(endereco);
	}

	@PutMapping("/{id}")
	public Endereco atualizar(@PathParam(value = "id") Long enderecoId, @RequestBody Endereco endereco) {
		return service.salvar(enderecoId, endereco);
	}
	
}
