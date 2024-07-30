package br.ufg.inf.backend.stp.unidadeHospitalar;

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
@RequestMapping("/unidadeHospitalar")
public class UnidadeHospitalarController {

	@Autowired
	private UnidadeHospitalarService service;

	@GetMapping
	public List<UnidadeHospitalar> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public UnidadeHospitalar obter(@PathParam(value = "id") Long unidadeHospitalarId) {
		return service.obter(unidadeHospitalarId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long unidadeHospitalarId) {
		service.remover(unidadeHospitalarId);
	}

	@PostMapping
	public UnidadeHospitalar adicionar(@RequestBody UnidadeHospitalar unidadeHospitalar) {
		return service.salvar(unidadeHospitalar);
	}

	@PutMapping("/{id}")
	public UnidadeHospitalar atualizar(@PathParam(value = "id") Long unidadeHospitalarId, @RequestBody UnidadeHospitalar unidadeHospitalar) {
		return service.salvar(unidadeHospitalarId, unidadeHospitalar);
	}
	
}
