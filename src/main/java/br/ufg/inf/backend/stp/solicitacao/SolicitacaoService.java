package br.ufg.inf.backend.stp.solicitacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository repository;

	public List<Solicitacao> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO')")
	public Solicitacao salvar(Solicitacao solicitacao) {
		return repository.save(solicitacao);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO')")
	public Solicitacao salvar(Long id, Solicitacao solicitacao) {
		solicitacao.setId(id);
		return repository.save(solicitacao);
	}

	public Solicitacao obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
