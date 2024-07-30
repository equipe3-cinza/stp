package br.ufg.inf.backend.stp.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private CustomUserDetailsService service;
	
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping
	public ResponseEntity<List<User>> listar() {
		try {
			List<User> users = service.listar();
			return ResponseEntity.ok(users);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

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

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable("id") Long userId) {
		try {
			service.remover(userId);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

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
