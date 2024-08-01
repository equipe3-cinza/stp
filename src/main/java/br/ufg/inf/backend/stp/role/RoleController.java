package br.ufg.inf.backend.stp.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService service;

	@GetMapping
	public List<Role> listar() {
		return service.listar();
	}
	
	@GetMapping("/{id}")
	public Role obter(@PathVariable("id") Long roleId) {
		return service.obter(roleId);
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable("id") Long roleId) {
		service.remover(roleId);
	}

	@PostMapping
	public Role adicionar(@RequestBody Role role) {
		return service.salvar(role);
	}
	
	@PutMapping("/{id}")
	public Role atualizar(@PathVariable("id") Long roleId, @RequestBody Role role) {
		return service.salvar(roleId, role);
	}
	
}