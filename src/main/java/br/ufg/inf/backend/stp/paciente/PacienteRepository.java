package br.ufg.inf.backend.stp.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	
}
