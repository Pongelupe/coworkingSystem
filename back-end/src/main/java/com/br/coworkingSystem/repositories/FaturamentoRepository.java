package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.coworkingSystem.model.Faturamento;

public interface FaturamentoRepository extends JpaRepository<Faturamento, Long> {

}
