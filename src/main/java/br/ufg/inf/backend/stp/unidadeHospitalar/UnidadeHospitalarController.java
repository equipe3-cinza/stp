package br.ufg.inf.backend.stp.unidadeHospitalar;

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

import br.ufg.inf.backend.stp.ApiResponse;

@RestController
@RequestMapping("/unidadeHospitalar")
public class UnidadeHospitalarController {

	@Autowired
	private UnidadeHospitalarService service;

	@Autowired
	private ApiResponse<UnidadeHospitalar> response;

	@Autowired
	private ApiResponse<List<UnidadeHospitalar>> responseList;

	@Autowired
	private ApiResponse<Void> responseVoid;

	@GetMapping
	public ResponseEntity<ApiResponse<List<UnidadeHospitalar>>> listar() {
		try {
			List<UnidadeHospitalar> unidadesHospitalares = service.listar();
			responseList.setData(unidadesHospitalares);
			responseList.setMessage("Unidades Hospitalares listadas com sucesso");
			responseList.setSuccess(true);
			return ResponseEntity.ok(responseList);
		} catch (Exception e) {
			responseList.setData(null);
			responseList.setMessage("Erro ao listar unidades hospitalares: " + e.getMessage());
			responseList.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseList);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UnidadeHospitalar>> obter(@PathVariable("id") Long unidadeHospitalarId) {
		if (unidadeHospitalarId == null) {
			response.setMessage("ID da unidade hospitalar não pode ser nulo");
			response.setSuccess(false);
			return ResponseEntity.badRequest().body(response);
		}
		try {
			UnidadeHospitalar unidadeHospitalar = service.obter(unidadeHospitalarId);
			if (unidadeHospitalar != null) {
				response.setData(unidadeHospitalar);
				response.setMessage("Unidade Hospitalar obtida com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Unidade Hospitalar não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao obter unidade hospitalar: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> remover(@PathVariable("id") Long unidadeHospitalarId) {
		if (unidadeHospitalarId == null) {
			responseVoid.setMessage("ID da unidade hospitalar não pode ser nulo");
			responseVoid.setSuccess(false);
			return ResponseEntity.badRequest().body(responseVoid);
		}
		try {
			service.remover(unidadeHospitalarId);
			responseVoid.setData(null);
			responseVoid.setMessage("Unidade Hospitalar removida com sucesso");
			responseVoid.setSuccess(true);
			return ResponseEntity.ok(responseVoid);
		} catch (Exception e) {
			responseVoid.setData(null);
			responseVoid.setMessage("Erro ao remover unidade hospitalar: " + e.getMessage());
			responseVoid.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVoid);
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UnidadeHospitalar>> adicionar(@RequestBody UnidadeHospitalar unidadeHospitalar) {
		try {
			UnidadeHospitalar novaUnidadeHospitalar = service.salvar(unidadeHospitalar);
			response.setData(novaUnidadeHospitalar);
			response.setMessage("Unidade Hospitalar adicionada com sucesso");
			response.setSuccess(true);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao adicionar unidade hospitalar: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UnidadeHospitalar>> atualizar(@PathVariable("id") Long unidadeHospitalarId, @RequestBody UnidadeHospitalar unidadeHospitalar) {
		try {
			UnidadeHospitalar unidadeHospitalarAtualizada = service.salvar(unidadeHospitalarId, unidadeHospitalar);
			if (unidadeHospitalarAtualizada != null) {
				response.setData(unidadeHospitalarAtualizada);
				response.setMessage("Unidade Hospitalar atualizada com sucesso");
				response.setSuccess(true);
				return ResponseEntity.ok(response);
			} else {
				response.setData(null);
				response.setMessage("Unidade Hospitalar não encontrada");
				response.setSuccess(false);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
		} catch (Exception e) {
			response.setData(null);
			response.setMessage("Erro ao atualizar unidade hospitalar: " + e.getMessage());
			response.setSuccess(false);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
