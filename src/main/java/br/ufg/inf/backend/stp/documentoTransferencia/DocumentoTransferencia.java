package br.ufg.inf.backend.stp.documentoTransferencia;

import java.util.List;

import br.ufg.inf.backend.stp.medicamento.Medicamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class DocumentoTransferencia {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private List<String> procedimentosAcondicionamento;
    private List<String> procedimentosRecebimento;

    @ManyToMany
    @JoinTable(name = "documento_transferencia_medicamento"
        , joinColumns = @JoinColumn(name = "documento_transferencia_id", nullable = false)
        , inverseJoinColumns = @JoinColumn(name = "medicamento_id", nullable = false)
    )
    private List<Medicamento> drogasAdministradas;

}
