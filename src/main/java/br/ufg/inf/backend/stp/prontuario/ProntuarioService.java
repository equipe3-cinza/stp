package br.ufg.inf.backend.stp.prontuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
public class ProntuarioService {

	@Autowired
	private ProntuarioRepository repository;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	public List<Prontuario> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	public Prontuario salvar(Prontuario prontuario) {
		return repository.save(prontuario);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	public Prontuario salvar(Long id, Prontuario prontuario) {
		prontuario.setId(id);
		return repository.save(prontuario);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	public Prontuario obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
