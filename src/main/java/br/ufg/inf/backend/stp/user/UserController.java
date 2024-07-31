package br.ufg.inf.backend.stp.user;

import br.ufg.inf.backend.stp.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private CustomUserDetailsService service;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private ApiResponse<User> response;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<User>> listar() {
		try {
			List<User> users = service.listar();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<User> obter(@PathVariable("id") Long userId) {
		try {
			User user = service.obter(userId);
			if (user != null) {
				return ResponseEntity.ok(user);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/{username}")
	public ResponseEntity<ApiResponse<User>> obter(@PathVariable("username") String username) {

		try {
			User user = service.obter(username);

			response.setData(user);
			response.setMessage("Usuário encontrado");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro, usuário não encontrado: " + e.getMessage().split("ERROR:")[1].split("Detail:")[0].trim());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long userId) {
		try {
			service.remover(userId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<User> adicionar(@RequestBody User user) {
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			User savedUser = service.salvar(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<User> atualizar(@PathVariable("id") Long userId, @RequestBody User user) {
		try {
			User updatedUser = service.salvar(userId, user);
			if (updatedUser != null) {
				return ResponseEntity.ok(updatedUser);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
