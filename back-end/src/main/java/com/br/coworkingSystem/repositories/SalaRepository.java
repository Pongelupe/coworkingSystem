package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

}
