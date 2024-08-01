package br.ufg.inf.backend.stp.endereco;

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
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@Autowired
	private ApiResponse<Endereco> response;

	@Autowired
	private ApiResponse<List<Endereco>> responseList;

	@Autowired
	private ApiResponse<Void> responseVoid;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Endereco>>> listar() {
		try {
			List<Endereco> enderecos = service.listar();
			responseList.setData(enderecos);
			responseList.setMessage("Endereços listados com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar endereços: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Endereco>> obter(@PathVariable("id") Long enderecoId) {
		if (enderecoId == null) {
			response.setMessage("ID do endereço não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			Endereco endereco = service.obter(enderecoId);
			if (endereco != null) {
				response.setData(endereco);
				response.setMessage("Endereço obtido com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Endereço não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter endereço: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long enderecoId) {
		if (enderecoId == null) {
			responseVoid.setMessage("ID do endereço não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(enderecoId);
			responseVoid.setData(null);
			responseVoid.setMessage("Endereço removido com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover endereço: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Endereco>> adicionar(@RequestBody Endereco endereco) {
		try {
			Endereco novoEndereco = service.salvar(endereco);
			response.setData(novoEndereco);
			response.setMessage("Endereço adicionado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar endereço: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Endereco>> atualizar(@PathVariable("id") Long enderecoId, @RequestBody Endereco endereco) {
		try {
			Endereco enderecoAtualizado = service.salvar(enderecoId, endereco);
			if (enderecoAtualizado != null) {
				response.setData(enderecoAtualizado);
				response.setMessage("Endereço atualizado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Endereço não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar endereço: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}