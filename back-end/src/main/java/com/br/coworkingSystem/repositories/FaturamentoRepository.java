package com.br.coworkingSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Faturamento;
import com.br.coworkingSystem.model.enuns.StatusFaturamento;

@Repository
public interface FaturamentoRepository extends JpaRepository<Faturamento, Long> {

	List<Faturamento> findAllByStatusFaturamento(StatusFaturamento status);

}
