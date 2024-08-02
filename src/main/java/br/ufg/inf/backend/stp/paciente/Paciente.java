package br.ufg.inf.backend.stp.paciente;

import org.hibernate.validator.constraints.br.CPF;

import br.ufg.inf.backend.stp.endereco.Endereco;
import br.ufg.inf.backend.stp.prontuario.Prontuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Entity
@Data
public class Paciente {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(nullable = false)
    private String name;
    
    private String telefone;
    
    @Email
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSanguineo tipoSanguineo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prontuario_id", nullable = false)
    private Prontuario prontuario;


}
