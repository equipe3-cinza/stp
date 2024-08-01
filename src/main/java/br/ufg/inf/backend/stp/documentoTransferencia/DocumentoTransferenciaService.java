package br.ufg.inf.backend.stp.documentoTransferencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DocumentoTransferenciaService {

	@Autowired
	private DocumentoTransferenciaRepository repository;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public List<DocumentoTransferencia> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public DocumentoTransferencia salvar(DocumentoTransferencia documentoTransferencia) {
		return repository.save(documentoTransferencia);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public DocumentoTransferencia salvar(Long id, DocumentoTransferencia documentoTransferencia) {
		documentoTransferencia.setId(id);
		return repository.save(documentoTransferencia);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public DocumentoTransferencia obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
