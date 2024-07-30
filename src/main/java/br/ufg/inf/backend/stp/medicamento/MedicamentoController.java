package br.ufg.inf.backend.stp.medicamento;

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
@RequestMapping("/medicamento")
public class MedicamentoController {

	@Autowired
	private MedicamentoService service;

	@GetMapping
	public List<Medicamento> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public Medicamento obter(@PathParam(value = "id") Long medicamentoId) {
		return service.obter(medicamentoId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long medicamentoId) {
		service.remover(medicamentoId);
	}

	@PostMapping
	public Medicamento adicionar(@RequestBody Medicamento medicamento) {
		return service.salvar(medicamento);
	}

	@PutMapping("/{id}")
	public Medicamento atualizar(@PathParam(value = "id") Long medicamentoId, @RequestBody Medicamento medicamento) {
		return service.salvar(medicamentoId, medicamento);
	}
	
}
