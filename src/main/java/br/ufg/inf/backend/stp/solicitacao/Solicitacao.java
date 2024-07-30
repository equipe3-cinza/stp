package br.ufg.inf.backend.stp.solicitacao;

import java.util.Date;
import java.util.List;

import br.ufg.inf.backend.stp.documentoTransferencia.DocumentoTransferencia;
import br.ufg.inf.backend.stp.especialidade.Especialidade;
import br.ufg.inf.backend.stp.medico.Medico;
import br.ufg.inf.backend.stp.paciente.Paciente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Solicitacao {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;

    @Column(nullable = false)
    private Date horarioSolicitacao;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "documento_id", nullable = false, unique = true)
    private DocumentoTransferencia documento;
    
    @ManyToMany
    @JoinTable(
            name = "solicitacao_especialidade",
            joinColumns = @JoinColumn(name = "solicitacao_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id", nullable = false)
    )
    private List<Especialidade> especialidadesRequisitada;

    
}
