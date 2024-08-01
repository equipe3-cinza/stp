package br.ufg.inf.backend.stp.transferencia;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import br.ufg.inf.backend.stp.unidadeHospitalar.UnidadeHospitalar;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository repository;

	private static final double EARTH_RADIUS = 6371;

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public List<Transferencia> listar() {
		return repository.findAll();
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")	
	public Transferencia salvar(Transferencia transferencia) {
		validarTransferencia(transferencia);
		calcularDistancia(transferencia);
		calcularHorarioPrevistoChegada(transferencia);
		return repository.save(transferencia);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")
	public Transferencia salvar(Long id, Transferencia transferencia) {
		validarTransferencia(transferencia);
		calcularDistancia(transferencia);
		calcularHorarioPrevistoChegada(transferencia);
		transferencia.setId(id);
		return repository.save(transferencia);
	}
	

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR') or hasRole('ROLE_MEDICO')")	
	public Transferencia obter(Long id) {
		return repository.findById(id).orElse(null);
	}

	@PreAuthorize("hasRole('ROLE_MEDICO_REGULADOR')")	
	public void remover(Long id) {
		repository.deleteById(id);
	}

	private void validarTransferencia(Transferencia transferencia) {
		if (transferencia == null) {
			throw new IllegalArgumentException("O Campo transferência é obrigatorio!");
		}
		if (transferencia.getPaciente() == null) {
			throw new IllegalArgumentException("O Campo paciente é obrigatorio!");
		}
		if (transferencia.getOrigem() == null) {
			throw new IllegalArgumentException("O Campo hospital de origem é obrigatorio!");
		}
		if (transferencia.getDestino() == null) {
			throw new IllegalArgumentException("O Campo hospital de destino é obrigatorio!");
		}
		if (transferencia.getHorarioSaida() == null) {
			throw new IllegalArgumentException("O Campo horário de saída é obrigatorio!");
		}
		if (transferencia.getMeioTransporte() == null) {
			throw new IllegalArgumentException("O Campo meio de transporte é obrigatorio!");
		}
		if (transferencia.getMedicoOrigem() == null) {
			throw new IllegalArgumentException("O Campo médico de origem é obrigatorio!");
		}
		if (transferencia.getDocumento() == null) {
			throw new IllegalArgumentException("O Campo documento de transferência é obrigatorio!");
		}
	}

	private void calcularHorarioPrevistoChegada(Transferencia transferencia) {
		double distancia = transferencia.getDistancia();
		double velocidadeMedia = transferencia.getMeioTransporte().getVelocidadeMedia();
		double tempoEmHoras = distancia / velocidadeMedia;
		
		long tempoEmMillis = (long) (tempoEmHoras * 60 * 60 * 1000);
		Date horarioPrevistoChegada = new Date(transferencia.getHorarioSaida().getTime() + tempoEmMillis);
		
		transferencia.setHorarioPrevistoChegada(horarioPrevistoChegada);
	}

	private void calcularDistancia(Transferencia transferencia) {
		UnidadeHospitalar origem = transferencia.getOrigem();
		UnidadeHospitalar destino = transferencia.getDestino();
		double distancia = calcularDistancia(origem, destino);
		transferencia.setDistancia(distancia);
	}

	private double calcularDistancia(UnidadeHospitalar origem, UnidadeHospitalar destino) {
		
		double lat1 = Math.toRadians(origem.getLatitude());
		double lon1 = Math.toRadians(origem.getLongitude());
		double lat2 = Math.toRadians(destino.getLatitude());
		double lon2 = Math.toRadians(destino.getLongitude());

		double dLat = lat2 - lat1;
		double dLon = lon2 - lon1;

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
				Math.cos(lat1) * Math.cos(lat2) *
				Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c;
	}

	
}