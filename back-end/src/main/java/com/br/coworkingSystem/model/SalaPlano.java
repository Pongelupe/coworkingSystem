package com.br.coworkingSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class SalaPlano {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private int quantidadeHoras;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private Plano plano;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Sala sala;

	public long getId() {
		return id;
	}

	public int getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras(int quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	

}
