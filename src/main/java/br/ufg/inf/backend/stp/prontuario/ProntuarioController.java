package br.ufg.inf.backend.stp.prontuario;

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
@RequestMapping("/prontuario")
public class ProntuarioController {
	@Autowired
	private ProntuarioService service;
	@Autowired
	private ApiResponse<Prontuario> response;
	@Autowired
	private ApiResponse<List<Prontuario>> responseList;
	@Autowired
	private ApiResponse<Void> responseVoid;
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Prontuario>> obter(@PathVariable("id") Long prontuarioId) {
		if (prontuarioId == null) {
			response.setMessage("ID do prontuário não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			Prontuario prontuario = service.obter(prontuarioId);
			if (prontuario != null) {
				response.setData(prontuario);
				response.setMessage("Prontuário obtido com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Prontuário não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter prontuário: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long prontuarioId) {
		if (prontuarioId == null) {
			responseVoid.setMessage("ID do prontuário não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(prontuarioId);
			responseVoid.setData(null);
			responseVoid.setMessage("Prontuário removido com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover prontuário: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<List<Prontuario>>> listar() {
		try {
			List<Prontuario> prontuarios = service.listar();
			responseList.setData(prontuarios);
			responseList.setMessage("Prontuários listados com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar prontuários: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<Prontuario>> adicionar(@RequestBody Prontuario prontuario) {
		try {
			Prontuario novoProntuario = service.salvar(prontuario);
			response.setData(novoProntuario);
			response.setMessage("Prontuário adicionado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar prontuário: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Prontuario>> atualizar(@PathVariable("id") Long prontuarioId, @RequestBody Prontuario prontuario) {
		try {
			Prontuario prontuarioAtualizado = service.salvar(prontuarioId, prontuario);
			if (prontuarioAtualizado != null) {
				response.setData(prontuarioAtualizado);
				response.setMessage("Prontuário atualizado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Prontuário não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar prontuário: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
