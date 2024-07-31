package br.ufg.inf.backend.stp.transferencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaService service;

	@Autowired
	private ApiResponse<Transferencia> response;

	@Autowired
	private ApiResponse<List<Transferencia>> responseList;

	@Autowired
	ApiResponse<Void> responseVoid;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping
	public ResponseEntity<ApiResponse<List<Transferencia>>> listar() {
		try {
			List<Transferencia> transferencias = service.listar();
			responseList.setData(transferencias);
			responseList.setMessage("Transferências listadas com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar transferências: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Transferencia>> obter(@PathVariable("id") Long transferenciaId) {
		if (transferenciaId == null) {
			response.setMessage("ID da transferência não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			Transferencia transferencia = service.obter(transferenciaId);
			if (transferencia != null) {
				response.setData(transferencia);
				response.setMessage("Transferência obtida com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Transferência não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter transferência: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long transferenciaId) {
		if (transferenciaId == null) {
			responseVoid.setMessage("ID da transferência não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(transferenciaId);
			responseVoid.setData(null);
			responseVoid.setMessage("Transferência removida com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover transferência: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PostMapping
	public ResponseEntity<ApiResponse<Transferencia>> adicionar(@RequestBody Transferencia transferencia) {
		try {
			Transferencia novaTransferencia = service.salvar(transferencia);
			response.setData(novaTransferencia);
			response.setMessage("Transferência adicionada com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar transferência: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Transferencia>> atualizar(@PathVariable("id") Long transferenciaId, @RequestBody Transferencia transferencia) {
		try {
			Transferencia transferenciaAtualizada = service.salvar(transferenciaId, transferencia);
			if (transferenciaAtualizada != null) {
				response.setData(transferenciaAtualizada);
				response.setMessage("Transferência atualizada com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Transferência não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar transferência: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
