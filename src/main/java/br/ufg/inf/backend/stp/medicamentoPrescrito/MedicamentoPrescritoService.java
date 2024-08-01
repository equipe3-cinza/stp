package br.ufg.inf.backend.stp.medicamentoPrescrito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoPrescritoService {

	@Autowired
	private MedicamentoPrescritoRepository repository;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public List<MedicamentoPrescrito> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public MedicamentoPrescrito salvar(MedicamentoPrescrito mecamentoPrecrito) {
		return repository.save(mecamentoPrecrito);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public MedicamentoPrescrito salvar(Long id, MedicamentoPrescrito mecamentoPrecrito) {
		mecamentoPrecrito.setId(id);
		return repository.save(mecamentoPrecrito);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public MedicamentoPrescrito obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public void remover(Long id) {
		repository.deleteById(id);
	}
}
