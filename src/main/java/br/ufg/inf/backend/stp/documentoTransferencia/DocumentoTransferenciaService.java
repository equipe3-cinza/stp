package br.ufg.inf.backend.stp.documentoTransferencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentoTransferenciaService {

	@Autowired
	private DocumentoTransferenciaRepository repository;

	public List<DocumentoTransferencia> listar() {
		return repository.findAll();
	}

	public DocumentoTransferencia salvar(DocumentoTransferencia documentoTransferencia) {
		return repository.save(documentoTransferencia);
	}

	public DocumentoTransferencia salvar(Long id, DocumentoTransferencia documentoTransferencia) {
		documentoTransferencia.setId(id);
		return repository.save(documentoTransferencia);
	}

	public DocumentoTransferencia obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
