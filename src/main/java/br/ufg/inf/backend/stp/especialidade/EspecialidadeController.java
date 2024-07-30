package br.ufg.inf.backend.stp.especialidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.backend.stp.ApiResponse;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeService service;

	@Autowired
	private ApiResponse<Especialidade> response;

	@GetMapping
	public ResponseEntity<List<Especialidade>> listar() {
		try {
			List<Especialidade> especialidades = service.listar();
			return ResponseEntity.ok(especialidades);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Especialidade> obter(@PathParam(value = "id") Long especialidadeId) {
		try {
			Especialidade especialidade = service.obter(especialidadeId);
			if (especialidade != null) {
				return ResponseEntity.ok(especialidade);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathParam(value = "id") Long especialidadeId) {
		try {
			service.remover(especialidadeId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Especialidade>> adicionar(@RequestBody Especialidade especialidade) {
		try {
			service.salvar(especialidade);
			response.setData(especialidade);
			response.setMessage("Especialidade adicionada com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar especialidade: " + e.getMessage().split("ERROR:")[1].split("Detail:")[0].trim());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} 
	}

	@PutMapping("/{id}")
	public ResponseEntity<Especialidade> atualizar(@PathParam(value = "id") Long especialidadeId, @RequestBody Especialidade especialidade) {
		try {
			Especialidade especialidadeAtualizada = service.salvar(especialidadeId, especialidade);
			if (especialidadeAtualizada != null) {
				return ResponseEntity.ok(especialidadeAtualizada);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}