package br.ufg.inf.backend.stp.medicamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {

	@Autowired
	private MedicamentoRepository repository;

	public List<Medicamento> listar() {
		return repository.findAll();
	}

	public Medicamento salvar(Medicamento medicamento) {
		return repository.save(medicamento);
	}

	public Medicamento salvar(Long id, Medicamento medicamento) {
		medicamento.setId(id);
		return repository.save(medicamento);
	}

	public Medicamento obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
