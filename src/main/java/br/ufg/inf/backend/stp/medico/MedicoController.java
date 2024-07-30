package br.ufg.inf.backend.stp.medico;

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
@RequestMapping("/medico")
public class MedicoController {

	@Autowired
	private MedicoService service;

	@GetMapping
	public List<Medico> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Medico obter(@PathParam(value = "id") Long medicoId) {
		return service.obter(medicoId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long medicoId) {
		service.remover(medicoId);
	}

	@PostMapping
	public Medico adicionar(@RequestBody Medico medico) {
		return service.salvar(medico);
	}

	@PutMapping("/{id}")
	public Medico atualizar(@PathParam(value = "id") Long medicoId, @RequestBody Medico medico) {
		return service.salvar(medicoId, medico);
	}
	
}
