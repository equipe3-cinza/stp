package br.ufg.inf.backend.stp.prontuario;

import java.util.List;

import br.ufg.inf.backend.stp.medicamentoPrescrito.MedicamentoPrescrito;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Entity
@Data
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "prontuario_medicamento_prescrito",
            joinColumns = @JoinColumn(name = "prontuario_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "medicamento_prescrito_id", nullable = false)
    )
    private List<MedicamentoPrescrito> medicamentosPrescritos;
}