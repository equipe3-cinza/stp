package br.ufg.inf.backend.stp.paciente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

	@Autowired
	private PacienteRepository repository;


	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public List<Paciente> listar() {
		return repository.findAll();
	}
	
	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")
	public Paciente salvar(Paciente paciente) {
		return repository.save(paciente);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public Paciente salvar(Long id, Paciente paciente) {
		paciente.setId(id);
		return repository.save(paciente);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public Paciente obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
