package br.ufg.inf.backend.stp.documentoTransferencia;

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
@RequestMapping("/documentoTransferencia")
public class DocumentoTransferenciaController {

	@Autowired
	private DocumentoTransferenciaService service;

	@Autowired
	private ApiResponse<DocumentoTransferencia> response;

	@Autowired
	private ApiResponse<List<DocumentoTransferencia>> responseList;

	@Autowired
	private ApiResponse<Void> responseVoid;

	@GetMapping
	public ResponseEntity<ApiResponse<List<DocumentoTransferencia>>> listar() {
		try {
			List<DocumentoTransferencia> documentoTransferencias = service.listar();
			responseList.setData(documentoTransferencias);
			responseList.setMessage("Documentos listados com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar documentos: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<DocumentoTransferencia>> obter(@PathVariable("id") Long documentoTransferenciaId) {
		if (documentoTransferenciaId == null) {
			response.setMessage("ID do documento não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			DocumentoTransferencia documentoTransferencia = service.obter(documentoTransferenciaId);
			if (documentoTransferencia != null) {
				response.setData(documentoTransferencia);
				response.setMessage("Documento obtido com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Documento não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter documento: " + e.getMessage());
			e.printStackTrace();
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long documentoTransferenciaId) {
		if (documentoTransferenciaId == null) {
			responseVoid.setMessage("ID do documento não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(documentoTransferenciaId);
			responseVoid.setData(null);
			responseVoid.setMessage("Documento removido com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover documento: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<DocumentoTransferencia>> adicionar(@RequestBody DocumentoTransferencia documentoTransferencia) {
		try {
			service.salvar(documentoTransferencia);
			response.setData(documentoTransferencia);
			response.setMessage("Documento adicionado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar documento: " + e.getMessage().split("ERROR:")[1].split("Detail:")[0].trim());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		} 
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<DocumentoTransferencia>> atualizar(@PathVariable("id") Long documentoTransferenciaId, @RequestBody DocumentoTransferencia documentoTransferencia) {
		try {
			DocumentoTransferencia documentoTransferenciaAtualizado = service.salvar(documentoTransferenciaId, documentoTransferencia);
			if (documentoTransferenciaAtualizado != null) {
				response.setData(documentoTransferenciaAtualizado);
				response.setMessage("Documento atualizado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Documento não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar documento: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}