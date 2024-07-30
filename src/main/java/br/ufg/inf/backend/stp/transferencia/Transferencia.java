package br.ufg.inf.backend.stp.transferencia;

import java.util.Date;

import br.ufg.inf.backend.stp.documentoTransferencia.DocumentoTransferencia;
import br.ufg.inf.backend.stp.medico.Medico;
import br.ufg.inf.backend.stp.paciente.Paciente;
import br.ufg.inf.backend.stp.solicitacao.Solicitacao;
import br.ufg.inf.backend.stp.unidadeHospitalar.UnidadeHospitalar;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transferencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date horarioSaida;
	private Date horarioPrevistoChegada;
	private Double distancia;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "meio_transporte", length = 20)
	private MeioTransporte meioTransporte;
	
	@ManyToOne
	@JoinColumn(name = "destino_id", nullable = false)
	private UnidadeHospitalar destino;
	
	@ManyToOne
	@JoinColumn(name = "origem_id", nullable = false)
	private UnidadeHospitalar origem;
	
	@ManyToOne
	@JoinColumn(name = "medico_destino_id")
	private Medico medicoDestino;
	
	@ManyToOne
	@JoinColumn(name = "medico_origem_id", nullable = false)
	private Medico medicoOrigem;
	
	@ManyToOne
	@JoinColumn(name = "medico_regulador_id", nullable = false)
	private Medico medicoRegulador;

	@ManyToOne
	@JoinColumn(name = "documento_transferencia_id", nullable = false, unique = true)
	private DocumentoTransferencia documento;

	@ManyToOne
	@JoinColumn(name = "paciente_id", nullable = false)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "solicitacao_id", nullable = false, unique = true)
	private Solicitacao solicitacao;
}
