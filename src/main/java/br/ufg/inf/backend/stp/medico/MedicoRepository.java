package br.ufg.inf.backend.stp.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	
}
