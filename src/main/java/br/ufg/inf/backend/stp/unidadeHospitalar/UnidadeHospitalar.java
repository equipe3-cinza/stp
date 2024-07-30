package br.ufg.inf.backend.stp.unidadeHospitalar;

import java.util.List;

import br.ufg.inf.backend.stp.endereco.Endereco;
import br.ufg.inf.backend.stp.especialidade.Especialidade;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class UnidadeHospitalar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String telefone;

	@Email
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private Boolean temUTI;

	@Column(nullable = false)
	private String dadosPessoal;

	@Column(nullable = false)
	private Double latitude;

	@Column(nullable = false)
	private Double longitude;

	@Column(nullable = false)
	private Integer disponibilidadeLeitos;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", nullable = false, unique = true)
	private Endereco endereco;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "unidade_hospitalar_especialidades",
			joinColumns = @JoinColumn(name = "unidade_hospitalar_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "especialidade_id", nullable = false) 
	)
	private List<Especialidade> especialidades;

}
