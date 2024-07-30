package br.ufg.inf.backend.stp.documentoTransferencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<DocumentoTransferencia> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public DocumentoTransferencia obter(@PathParam(value = "id") Long documentoTransferenciaId) {
		return service.obter(documentoTransferenciaId);
	}

	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long documentoTransferenciaId) {
		service.remover(documentoTransferenciaId);
	}

	@PostMapping
	public DocumentoTransferencia adicionar(@RequestBody DocumentoTransferencia documentoTransferencia) {
		return service.salvar(documentoTransferencia);
	}

	@PutMapping("/{id}")
	public DocumentoTransferencia atualizar(@PathParam(value = "id") Long documentoTransferenciaId, @RequestBody DocumentoTransferencia documentoTransferencia) {
		return service.salvar(documentoTransferenciaId, documentoTransferencia);
	}
	
}
