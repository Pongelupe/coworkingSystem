package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {

}
