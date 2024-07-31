package br.ufg.inf.backend.stp.solicitacao;

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
@RequestMapping("/solicitacao")
public class SolicitacaoController {

	@Autowired
	private SolicitacaoService service;

	@Autowired
	private ApiResponse<Solicitacao> response;

	@Autowired
	private ApiResponse<List<Solicitacao>> responseList;

	@Autowired
	ApiResponse<Void> responseVoid;

	@GetMapping
	public ResponseEntity<ApiResponse<List<Solicitacao>>> listar() {
		try {
			List<Solicitacao> solicitacoes = service.listar();
			responseList.setData(solicitacoes);
			responseList.setMessage("Solicitações listadas com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar solicitações: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Solicitacao>> obter(@PathVariable("id") Long solicitacaoId) {
		if (solicitacaoId == null) {
			response.setMessage("ID da solicitação não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			Solicitacao solicitacao = service.obter(solicitacaoId);
			if (solicitacao != null) {
				response.setData(solicitacao);
				response.setMessage("Solicitação obtida com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Solicitação não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter solicitação: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long solicitacaoId) {
		if (solicitacaoId == null) {
			responseVoid.setMessage("ID da solicitação não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(solicitacaoId);
			responseVoid.setData(null);
			responseVoid.setMessage("Solicitação removida com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover solicitação: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Solicitacao>> adicionar(@RequestBody Solicitacao solicitacao) {
		try {
			Solicitacao novaSolicitacao = service.salvar(solicitacao);
			response.setData(novaSolicitacao);
			response.setMessage("Solicitação adicionada com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar solicitação: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Solicitacao>> atualizar(@PathVariable("id") Long solicitacaoId, @RequestBody Solicitacao solicitacao) {
		try {
			Solicitacao solicitacaoAtualizada = service.salvar(solicitacaoId, solicitacao);
			if (solicitacaoAtualizada != null) {
				response.setData(solicitacaoAtualizada);
				response.setMessage("Solicitação atualizada com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Solicitação não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar solicitação: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
