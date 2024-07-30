package br.ufg.inf.backend.stp.endereco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public List<Endereco> listar() {
		return repository.findAll();
	}

	public Endereco salvar(Endereco endereco) {
		return repository.save(endereco);
	}

	public Endereco salvar(Long id, Endereco endereco) {
		endereco.setId(id);
		return repository.save(endereco);
	}

	public Endereco obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
