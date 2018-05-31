package com.br.coworkingSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Colaborador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	
	@Column(nullable = false, columnDefinition="boolean default false")
	private boolean responsavelLegal;

	@MapsId
	@NotNull
	@OneToOne
	private Pessoa pessoa;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;

	public boolean isResponsavelLegal() {
		return responsavelLegal;
	}

	public void setResponsavelLegal(boolean responsavelLegal) {
		this.responsavelLegal = responsavelLegal;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}


	

}
