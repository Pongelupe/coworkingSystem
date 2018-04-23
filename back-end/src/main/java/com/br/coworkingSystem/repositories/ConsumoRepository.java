package com.br.coworkingSystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Cliente;
import com.br.coworkingSystem.model.Consumo;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Integer> {

	List<Consumo> findAllBySolicitante(Cliente cliente);

	List<Consumo> findAllByFaturado(boolean faturado);
}