package br.ufg.inf.backend.stp.medico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.ufg.inf.backend.stp.user.CustomUserDetailsService;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	@Autowired
	private CustomUserDetailsService userService;

	public List<Medico> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Medico salvar(Medico medico) {
		validarMedico(medico);
		medico.setUser(userService.salvar(medico.getUser()));
		return repository.save(medico);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Medico salvar(Long id, Medico medico) {
		validarMedico(medico);
		medico.setUser(userService.salvar(medico.getUser()));
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

	private void validarMedico(Medico medico) {
		if (medico == null) {
			throw new IllegalArgumentException("O Medico é obrigatório");
		}
		if (medico.getCrm() == null || medico.getCrm().isEmpty()) {
			throw new IllegalArgumentException("O Campo CRM é obrigatório");
		}
		if (medico.getNome() == null || medico.getNome().isEmpty()) {
			throw new IllegalArgumentException("O Campo Nome é obrigatório");
		}
		if (medico.getPapel() == null) {
			throw new IllegalArgumentException("O Campo Papel é obrigatório");
		}
		if (medico.getEspecialidade() == null) {
			throw new IllegalArgumentException("O Campo Especialidade é obrigatória");
		}
	}
}