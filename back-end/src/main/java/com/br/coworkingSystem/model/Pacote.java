package com.br.coworkingSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pacote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private int quantidadeHoras;
	private double valorPacote;
	@OneToOne
	private Sala sala;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidadeHoras() {
		return quantidadeHoras;
	}

	public void setQuantidadeHoras(int quantidadeHoras) {
		this.quantidadeHoras = quantidadeHoras;
	}

	public double getValorPacote() {
		return valorPacote;
	}

	public void setValorPacote(double valorPacote) {
		this.valorPacote = valorPacote;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return "Pacote [id=" + id + ", nome=" + nome + ", quantidadeHoras=" + quantidadeHoras + ", valorPacote="
				+ valorPacote + ", sala=" + sala + "]";
	}

}
