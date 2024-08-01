package br.ufg.inf.backend.stp.user;

import br.ufg.inf.backend.stp.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private CustomUserDetailsService service;

	@Autowired
	private ApiResponse<User> response;

	@Autowired
	private ApiResponse<Void> responseVoid;

	@Autowired
	private ApiResponse<List<User>> responseList;

	@GetMapping
	public ResponseEntity<ApiResponse<List<User>>> listar() {
		try {
			List<User> users = service.listar();
			responseList.setData(users);
			responseList.setMessage("Usuários listados com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar usuários: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<User>> obter(@PathVariable("id") Long userId) {
		try {
			User user = service.obter(userId);
			if (user != null) {
				response.setData(user);
				response.setMessage("Usuário encontrado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Usuário não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter usuário: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@GetMapping("/{username}")
	public ResponseEntity<ApiResponse<User>> obter(@PathVariable("username") String username) {
		try {
			User user = service.obter(username);
			response.setData(user);
			response.setMessage("Usuário encontrado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro, usuário não encontrado: " + e.getMessage().split("ERROR:")[1].split("Detail:")[0].trim());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long userId) {
		try {
			service.remover(userId);
			responseVoid.setData(null);
			responseVoid.setMessage("Usuário removido com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover usuário: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<User>> adicionar(@RequestBody User user) {
		try {
			User savedUser = service.salvar(user);
			response.setData(savedUser);
			response.setMessage("Usuário adicionado com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar usuário: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<User>> atualizar(@PathVariable("id") Long userId, @RequestBody User user) {
		try {
			User updatedUser = service.salvar(userId, user);
			if (updatedUser != null) {
				response.setData(updatedUser);
				response.setMessage("Usuário atualizado com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Usuário não encontrado");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar usuário: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
}