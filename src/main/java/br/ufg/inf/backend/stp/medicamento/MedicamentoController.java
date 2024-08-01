package br.ufg.inf.backend.stp.medicamento;

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
@RequestMapping("/medicamento")
public class MedicamentoController {

	@Autowired
	private MedicamentoService service;

	@Autowired
	private ApiResponse<Medicamento> response;

	@Autowired
	private ApiResponse<List<Medicamento>> responseList;

	@Autowired
	private ApiResponse<Void> responseVoid;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Medicamento>>> listar() {
		try {
			List<Medicamento> medicamentos = service.listar();
			responseList.setData(medicamentos);
			responseList.setMessage("Medicamentos listados com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar medicamentos: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Medicamento>> obter(@PathVariable("id") Long medicamentoId) {
		if (medicamentoId == null) {
			response.setMessage("ID do medicamento não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			Medicamento medicamento = service.obter(medicamentoId);
			if (medicamento != null) {
				response.setData(medicamento);
				response.setMessage("Medicamento obtido com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Medicamento não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter medicamento: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long medicamentoId) {
		if (medicamentoId == null) {
			responseVoid.setMessage("ID do medicamento não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(medicamentoId);
			responseVoid.setData(null);
			responseVoid.setMessage("Medicamento removido com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover medicamento: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Medicamento>> adicionar(@RequestBody Medicamento medicamento) {
		try {
			Medicamento medicamentoSalvo = service.salvar(medicamento);
			response.setData(medicamentoSalvo);
			response.setMessage("Medicamento adicionado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar medicamento: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Medicamento>> atualizar(@PathVariable("id") Long medicamentoId, @RequestBody Medicamento medicamento) {
		try {
			Medicamento medicamentoAtualizado = service.salvar(medicamentoId, medicamento);
			if (medicamentoAtualizado != null) {
				response.setData(medicamentoAtualizado);
				response.setMessage("Medicamento atualizado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Medicamento não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar medicamento: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
