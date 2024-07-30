package br.ufg.inf.backend.stp.transferencia;

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
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaService service;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping
	public List<Transferencia> listar() {
		return service.listar();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping("/{id}")
	public Transferencia obter(@PathParam(value = "id") Long transferenciaId) {
		return service.obter(transferenciaId);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long transferenciaId) {
		service.remover(transferenciaId);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PostMapping
	public Transferencia adicionar(@RequestBody Transferencia transferencia) {
		return service.salvar(transferencia);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PutMapping("/{id}")
	public Transferencia atualizar(@PathParam(value = "id") Long transferenciaId, @RequestBody Transferencia transferencia) {
		return service.salvar(transferenciaId, transferencia);
	}
	
}
