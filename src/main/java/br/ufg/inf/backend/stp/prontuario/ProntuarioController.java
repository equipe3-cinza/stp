package br.ufg.inf.backend.stp.prontuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

	@Autowired
	private ProntuarioService service;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping("/{id}")
	public Prontuario obter(@PathParam(value = "id") Long prontuarioId) {
		return service.obter(prontuarioId);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long prontuarioId) {
		service.remover(prontuarioId);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping
	public List<Prontuario> listar() {
		return service.listar();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PostMapping
	public Prontuario adicionar(@RequestBody Prontuario prontuario) {
		return service.salvar(prontuario);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PutMapping("/{id}")
	public Prontuario atualizar(@PathParam(value = "id") Long prontuarioId, @RequestBody Prontuario prontuario) {
		return service.salvar(prontuarioId, prontuario);
	}
	
}
