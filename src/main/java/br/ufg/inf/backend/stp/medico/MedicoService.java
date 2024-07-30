package br.ufg.inf.backend.stp.medico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	public List<Medico> listar() {
		return repository.findAll();
	}

	public Medico salvar(Medico medico) {
		return repository.save(medico);
	}

	public Medico salvar(Long id, Medico medico) {
		medico.setId(id);
		return repository.save(medico);
	}

	public Medico obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
