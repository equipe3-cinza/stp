package br.ufg.inf.backend.stp.paciente;

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
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private PacienteService service;

	@Autowired
	private ApiResponse<Paciente> response;

	@Autowired
	private ApiResponse<List<Paciente>> responseList;

	@Autowired
	private ApiResponse<Void> responseVoid;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Paciente>>> listar() {
		try {
			List<Paciente> pacientes = service.listar();
			responseList.setData(pacientes);
			responseList.setMessage("Pacientes listados com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar pacientes: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Paciente>> obter(@PathVariable("id") Long pacienteId) {
		if (pacienteId == null) {
			response.setMessage("ID do paciente não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			Paciente paciente = service.obter(pacienteId);
			if (paciente != null) {
				response.setData(paciente);
				response.setMessage("Paciente obtido com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Paciente não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter paciente: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long pacienteId) {
		if (pacienteId == null) {
			responseVoid.setMessage("ID do paciente não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(pacienteId);
			responseVoid.setData(null);
			responseVoid.setMessage("Paciente removido com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover paciente: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Paciente>> adicionar(@RequestBody Paciente paciente) {
		try {
			Paciente pacienteSalvo = service.salvar(paciente);
			response.setData(pacienteSalvo);
			response.setMessage("Paciente adicionado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar paciente: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Paciente>> atualizar(@PathVariable("id") Long pacienteId, @RequestBody Paciente paciente) {
		try {
			Paciente pacienteAtualizado = service.salvar(pacienteId, paciente);
			if (pacienteAtualizado != null) {
				response.setData(pacienteAtualizado);
				response.setMessage("Paciente atualizado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Paciente não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar paciente: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
