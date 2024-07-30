package br.ufg.inf.backend.stp.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	public List<Role> listar() {
		return repository.findAll();
	}

	public Role salvar(Role role) {
		return repository.save(role);
	}

	public Role salvar(Long id, Role role) {
		role.setId(id);
		return repository.save(role);
	}

	public Role obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
