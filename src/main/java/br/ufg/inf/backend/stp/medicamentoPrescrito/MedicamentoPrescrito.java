package br.ufg.inf.backend.stp.medicamentoPrescrito;

import br.ufg.inf.backend.stp.medicamento.Medicamento;
import jakarta.persistence.CascadeType;
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
public class MedicamentoPrescrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String posologia;
    private Double dosagem;
    private String viaAdministracao;
    
    @Enumerated(EnumType.STRING)
    private UnidadeDosagem unidadeDosagem;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medicamento_id", nullable = false)
    private Medicamento medicamento;    
}



