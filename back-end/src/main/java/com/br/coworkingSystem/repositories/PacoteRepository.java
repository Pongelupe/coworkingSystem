package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Pacote;

@Repository
public interface PacoteRepository extends JpaRepository<Pacote, Long> {

}
