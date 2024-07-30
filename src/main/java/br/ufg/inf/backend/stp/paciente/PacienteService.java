package br.ufg.inf.backend.stp.paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;

	public List<Paciente> listar() {
		return repository.findAll();
	}

	public Paciente salvar(Paciente paciente) {
		return repository.save(paciente);
	}

	public Paciente salvar(Long id, Paciente paciente) {
		paciente.setId(id);
		return repository.save(paciente);
	}

	public Paciente obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
