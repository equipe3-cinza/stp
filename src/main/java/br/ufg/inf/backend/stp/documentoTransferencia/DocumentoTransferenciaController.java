package br.ufg.inf.backend.stp.documentoTransferencia;

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

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/documentoTransferencia")
public class DocumentoTransferenciaController {

	@Autowired
	private DocumentoTransferenciaService service;

	@GetMapping
	public ResponseEntity<List<DocumentoTransferencia>> listar() {
		try {
			List<DocumentoTransferencia> documentos = service.listar();
			return ResponseEntity.ok(documentos);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<DocumentoTransferencia> obter(@PathParam(value = "id") Long documentoTransferenciaId) {
		try {
			DocumentoTransferencia documento = service.obter(documentoTransferenciaId);
			if (documento != null) {
				return ResponseEntity.ok(documento);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathParam(value = "id") Long documentoTransferenciaId) {
		try {
			service.remover(documentoTransferenciaId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<DocumentoTransferencia> adicionar(@RequestBody DocumentoTransferencia documentoTransferencia) {
		try {
			DocumentoTransferencia novoDocumento = service.salvar(documentoTransferencia);
			return ResponseEntity.status(HttpStatus.CREATED).body(novoDocumento);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<DocumentoTransferencia> atualizar(@PathParam(value = "id") Long documentoTransferenciaId, @RequestBody DocumentoTransferencia documentoTransferencia) {
		try {
			DocumentoTransferencia documentoAtualizado = service.salvar(documentoTransferenciaId, documentoTransferencia);
			if (documentoAtualizado != null) {
				return ResponseEntity.ok(documentoAtualizado);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}