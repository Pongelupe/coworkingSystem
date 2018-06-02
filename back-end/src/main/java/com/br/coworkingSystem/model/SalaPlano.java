package com.br.coworkingSystem.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@SuppressWarnings("serial")
@Entity
public class SalaPlano implements Serializable {

	@Column(nullable = false)
	private int quantidadeHoras;

	@Id
	@ManyToOne
	@JoinColumn(nullable = false)
	@JsonBackReference
	private Plano plano;

	@Id
	@ManyToOne
	@JoinColumn(nullable = false)
	private Sala sala;

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
