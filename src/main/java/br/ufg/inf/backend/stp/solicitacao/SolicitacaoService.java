package br.ufg.inf.backend.stp.solicitacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository repository;

	public List<Solicitacao> listar() {
		return repository.findAll();
	}

	public Solicitacao salvar(Solicitacao solicitacao) {
		return repository.save(solicitacao);
	}

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
