package br.ufg.inf.backend.stp.solicitacao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {

	@Autowired
	private SolicitacaoRepository repository;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")
	public List<Solicitacao> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO')")
	public Solicitacao salvar(Solicitacao solicitacao) {
		validarCamposObrigatorios(solicitacao);
		solicitacao.setHorarioSolicitacao(new Date());
		return repository.save(solicitacao);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO')")
	public Solicitacao salvar(Long id, Solicitacao solicitacao) {
		validarCamposObrigatorios(solicitacao);
		solicitacao.setId(id);
		return repository.save(solicitacao);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public Solicitacao obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO')")
	public void remover(Long id) {
		repository.deleteById(id);
	}

	private void validarCamposObrigatorios(Solicitacao solicitacao) {
		if (solicitacao.getPaciente() == null) {
			throw new IllegalArgumentException("O campo paciente é obrigatório");
		}
		if (solicitacao.getMedico() == null) {
			throw new IllegalArgumentException("O campo médico é obrigatório");
		}
		if (solicitacao.getDocumento() == null) {
			throw new IllegalArgumentException("O Documento é obrigatório");
		}
		if (solicitacao.getEspecialidadesRequisitada() == null) {
			throw new IllegalArgumentException("O campo Especialidades é obrigatório");
		}
	}
}