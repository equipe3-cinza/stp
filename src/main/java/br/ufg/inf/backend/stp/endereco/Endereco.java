package br.ufg.inf.backend.stp.endereco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false)
	private String rua;
	@Column(length = 100)
	private String complemento;
	@Column(length = 50, nullable = false)
	private String bairro;
	@Pattern(regexp = "^[0-9]{8}$", message = "CEP inválido")
	@Size(min = 8, max = 8, message = "CEP deve ter 8 dígitos")
	@Column(length = 8, columnDefinition = "char(8)", nullable = false)
	private String cep;
	@Column(length = 50, nullable = false)
	private String cidade;
	@Column(length = 2, columnDefinition = "char(8)", nullable = false)
	private String estado;
}
