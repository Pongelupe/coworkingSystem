package com.br.coworkingSystem.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.coworkingSystem.model.Cliente;
import com.br.coworkingSystem.model.Contrato;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
	
	List<Contrato> findAllByCliente(Optional<Cliente> clienteOptional);
}
