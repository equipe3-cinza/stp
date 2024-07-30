package br.ufg.inf.backend.stp.transferencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;

	public List<Transferencia> listar() {
		return repository.findAll();
	}

	public Transferencia salvar(Transferencia transferencia) {
		return repository.save(transferencia);
	}

	public Transferencia salvar(Long id, Transferencia transferencia) {
		transferencia.setId(id);
		return repository.save(transferencia);
	}

	public Transferencia obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
