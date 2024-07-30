package br.ufg.inf.backend.stp.prontuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProntuarioService {

	@Autowired
	private ProntuarioRepository repository;

	public List<Prontuario> listar() {
		return repository.findAll();
	}

	public Prontuario salvar(Prontuario prontuario) {
		return repository.save(prontuario);
	}

	public Prontuario salvar(Long id, Prontuario prontuario) {
		prontuario.setId(id);
		return repository.save(prontuario);
	}

	public Prontuario obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	public void remover(Long id) {
		repository.deleteById(id);
	}
}
