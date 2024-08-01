package br.ufg.inf.backend.stp.especialidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.backend.stp.ApiResponse;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {

	@Autowired
	private EspecialidadeService service;

	@Autowired
	private ApiResponse<Especialidade> response;

	@Autowired
	private ApiResponse<List<Especialidade>> responseList;

	@Autowired
	private ApiResponse<Void> responseVoid ;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Especialidade>>> listar() {
		try {
			List<Especialidade> especialidades = service.listar();
			responseList.setData(especialidades);
			responseList.setMessage("Especialidades listadas com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar especialidades: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Especialidade>> obter(@PathVariable("id") Long especialidadeId) {
		if (especialidadeId == null) {
			response.setMessage("ID da especialidade não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			Especialidade especialidade = service.obter(especialidadeId);
			if (especialidade != null) {
				response.setData(especialidade);
				response.setMessage("Especialidade obtida com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Especialidade não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter especialidade: " + e.getMessage());
			e.printStackTrace();
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long especialidadeId) {

		if (especialidadeId == null) {
			responseVoid.setMessage("ID da especialidade não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(especialidadeId);
			responseVoid.setData(null);
			responseVoid.setMessage("Especialidade removida com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover especialidade: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
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
	public ResponseEntity<ApiResponse<Especialidade>> atualizar(@PathVariable("id") Long especialidadeId, @RequestBody Especialidade especialidade) {
		try {
			Especialidade especialidadeAtualizada = service.salvar(especialidadeId, especialidade);
			if (especialidadeAtualizada != null) {
				response.setData(especialidadeAtualizada);
				response.setMessage("Especialidade atualizada com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Especialidade não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar especialidade: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}