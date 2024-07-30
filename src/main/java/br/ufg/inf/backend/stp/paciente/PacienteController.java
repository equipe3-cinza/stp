package br.ufg.inf.backend.stp.paciente;

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
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService service;

	@GetMapping
	public List<Paciente> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Paciente obter(@PathParam(value = "id") Long pacienteId) {
		return service.obter(pacienteId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long pacienteId) {
		service.remover(pacienteId);
	}

	@PostMapping
	public Paciente adicionar(@RequestBody Paciente paciente) {
		return service.salvar(paciente);
	}

	@PutMapping("/{id}")
	public Paciente atualizar(@PathParam(value = "id") Long pacienteId, @RequestBody Paciente paciente) {
		return service.salvar(pacienteId, paciente);
	}
	
}
