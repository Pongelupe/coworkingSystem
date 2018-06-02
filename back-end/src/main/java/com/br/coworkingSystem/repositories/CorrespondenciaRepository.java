package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Correspondencia;

@Repository
public interface CorrespondenciaRepository extends JpaRepository<Correspondencia, Long> {

}
