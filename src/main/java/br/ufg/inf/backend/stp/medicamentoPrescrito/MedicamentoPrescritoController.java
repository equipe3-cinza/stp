package br.ufg.inf.backend.stp.medicamentoPrescrito;

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
@RequestMapping("/medicamentoPrescrito")
public class MedicamentoPrescritoController {

	@Autowired
	private MedicamentoPrescritoService service;

	@Autowired
	private ApiResponse<MedicamentoPrescrito> response;

	@Autowired
	private ApiResponse<List<MedicamentoPrescrito>> responseList;

	@Autowired
	ApiResponse<Void> responseVoid;

	@GetMapping
	public ResponseEntity<ApiResponse<List<MedicamentoPrescrito>>> listar() {
		try {
			List<MedicamentoPrescrito> medicamentosPrescritos = service.listar();
			responseList.setData(medicamentosPrescritos);
			responseList.setMessage("Medicamentos prescritos listados com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar medicamentos prescritos: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<MedicamentoPrescrito>> obter(@PathVariable("id") Long medicamentoPrescritoId) {
		if (medicamentoPrescritoId == null) {
			response.setMessage("ID do medicamento prescrito não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			MedicamentoPrescrito medicamentoPrescrito = service.obter(medicamentoPrescritoId);
			if (medicamentoPrescrito != null) {
				response.setData(medicamentoPrescrito);
				response.setMessage("Medicamento prescrito obtido com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Medicamento prescrito não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter medicamento prescrito: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long medicamentoPrescritoId) {
		if (medicamentoPrescritoId == null) {
			responseVoid.setMessage("ID do medicamento prescrito não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(medicamentoPrescritoId);
			responseVoid.setData(null);
			responseVoid.setMessage("Medicamento prescrito removido com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover medicamento prescrito: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<MedicamentoPrescrito>> adicionar(@RequestBody MedicamentoPrescrito medicamentoPrescrito) {
		try {
			MedicamentoPrescrito novoMedicamentoPrescrito = service.salvar(medicamentoPrescrito);
			response.setData(novoMedicamentoPrescrito);
			response.setMessage("Medicamento prescrito adicionado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar medicamento prescrito: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<MedicamentoPrescrito>> atualizar(@PathVariable("id") Long medicamentoPrescritoId, @RequestBody MedicamentoPrescrito medicamentoPrescrito) {
		try {
			MedicamentoPrescrito medicamentoPrescritoAtualizado = service.salvar(medicamentoPrescritoId, medicamentoPrescrito);
			if (medicamentoPrescritoAtualizado != null) {
				response.setData(medicamentoPrescritoAtualizado);
				response.setMessage("Medicamento prescrito atualizado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Medicamento prescrito não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar medicamento prescrito: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
