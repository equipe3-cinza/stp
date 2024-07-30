package br.ufg.inf.backend.stp.unidadeHospitalar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class UnidadeHospitalarService {

	@Autowired
	private UnidadeHospitalarRepository repository;

	public List<UnidadeHospitalar> listar() {
		return repository.findAll();
	}

	public UnidadeHospitalar salvar(UnidadeHospitalar unidadeHospitalar) {
		return repository.save(unidadeHospitalar);
	}
	
	@Transactional
	public UnidadeHospitalar salvar(Long id, UnidadeHospitalar unidadeHospitalar) {
		unidadeHospitalar.setId(id);
		return repository.save(unidadeHospitalar);
	}

	public UnidadeHospitalar obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
