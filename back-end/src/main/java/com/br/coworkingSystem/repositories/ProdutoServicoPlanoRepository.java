package com.br.coworkingSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.ProdutoServicoPlano;

@Repository
public interface ProdutoServicoPlanoRepository extends JpaRepository<ProdutoServicoPlano, Long> {

}
