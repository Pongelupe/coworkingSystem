package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Faturamento;

@Repository
public interface FaturamentoRepository extends JpaRepository<Faturamento, Long> {
	
}
