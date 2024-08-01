package br.ufg.inf.backend.stp.medico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	public List<Medico> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Medico salvar(Medico medico) {
		if (medico.getCrm() == null || medico.getCrm().isEmpty()) {
			throw new IllegalArgumentException("CRM é obrigatório");
		}
		if (medico.getNome() == null || medico.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome é obrigatório");
		}
		if (medico.getPapel() == null) {
			throw new IllegalArgumentException("Papel é obrigatório");
		}
		if (medico.getEspecialidade() == null) {
			throw new IllegalArgumentException("Especialidade é obrigatória");
		}
		return repository.save(medico);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Medico salvar(Long id, Medico medico) {
		if (medico.getCrm() == null || medico.getCrm().isEmpty()) {
			throw new IllegalArgumentException("CRM é obrigatório");
		}
		if (medico.getNome() == null || medico.getNome().isEmpty()) {
			throw new IllegalArgumentException("Nome é obrigatório");
		}
		if (medico.getPapel() == null) {
			throw new IllegalArgumentException("Papel é obrigatório");
		}
		if (medico.getEspecialidade() == null) {
			throw new IllegalArgumentException("Especialidade é obrigatória");
		}
		medico.setId(id);
		return repository.save(medico);
	}

	public Medico obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void remover(Long id) {
		repository.deleteById(id);
	}
}