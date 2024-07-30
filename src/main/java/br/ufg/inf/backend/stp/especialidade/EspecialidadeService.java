package br.ufg.inf.backend.stp.especialidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeService {

	@Autowired
	private EspecialidadeRepository repository;

	public List<Especialidade> listar() {
		return repository.findAll();
	}

	public Especialidade salvar(Especialidade especialidade) {
		return repository.save(especialidade);
	}

	public Especialidade salvar(Long id, Especialidade especialidade) {
		especialidade.setId(id);
		return repository.save(especialidade);
	}

	public Especialidade obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
