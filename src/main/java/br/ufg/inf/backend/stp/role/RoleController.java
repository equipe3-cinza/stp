package br.ufg.inf.backend.stp.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService service;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping
	public List<Role> listar() {
		return service.listar();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@GetMapping("/{id}")
	public Role obter(@PathParam(value = "id") Long roleId) {
		return service.obter(roleId);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@DeleteMapping("/{id}")
	public void remover(@PathParam(value = "id") Long roleId) {
		service.remover(roleId);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PostMapping
	public Role adicionar(@RequestBody Role role) {
		return service.salvar(role);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	@PutMapping("/{id}")
	public Role atualizar(@PathParam(value = "id") Long roleId, @RequestBody Role role) {
		return service.salvar(roleId, role);
	}
	
}
