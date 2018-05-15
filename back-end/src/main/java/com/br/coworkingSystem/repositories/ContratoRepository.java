package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

}
