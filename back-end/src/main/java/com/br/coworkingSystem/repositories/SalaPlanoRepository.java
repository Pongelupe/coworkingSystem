package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.SalaPlano;

@Repository
public interface SalaPlanoRepository extends JpaRepository<SalaPlano, Long> {

}
