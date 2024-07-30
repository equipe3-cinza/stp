package br.ufg.inf.backend.stp.medicamentoPrescrito;

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
@RequestMapping("/mecamentoPrecrito")
public class MedicamentoPrescritoController {

	@Autowired
	private MedicamentoPrescritoService service;

	@GetMapping
	public List<MedicamentoPrescrito> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public MedicamentoPrescrito obter(@PathParam(value = "id") Long mecamentoPrecritoId) {
		return service.obter(mecamentoPrecritoId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long mecamentoPrecritoId) {
		service.remover(mecamentoPrecritoId);
	}

	@PostMapping
	public MedicamentoPrescrito adicionar(@RequestBody MedicamentoPrescrito mecamentoPrecrito) {
		return service.salvar(mecamentoPrecrito);
	}

	@PutMapping("/{id}")
	public MedicamentoPrescrito atualizar(@PathParam(value = "id") Long mecamentoPrecritoId, @RequestBody MedicamentoPrescrito mecamentoPrecrito) {
		return service.salvar(mecamentoPrecritoId, mecamentoPrecrito);
	}
	
}
