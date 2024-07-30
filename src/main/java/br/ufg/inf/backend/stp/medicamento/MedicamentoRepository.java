package br.ufg.inf.backend.stp.medicamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {
	
}
