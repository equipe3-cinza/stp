package br.ufg.inf.backend.stp.medicamentoPrescrito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoPrescritoService {

	@Autowired
	private MedicamentoPrescritoRepository repository;

	public List<MedicamentoPrescrito> listar() {
		return repository.findAll();
	}

	public MedicamentoPrescrito salvar(MedicamentoPrescrito mecamentoPrecrito) {
		return repository.save(mecamentoPrecrito);
	}

	public MedicamentoPrescrito salvar(Long id, MedicamentoPrescrito mecamentoPrecrito) {
		mecamentoPrecrito.setId(id);
		return repository.save(mecamentoPrecrito);
	}

	public MedicamentoPrescrito obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
