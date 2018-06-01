package com.br.coworkingSystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.br.coworkingSystem.model.enuns.TipoSala;

@Entity
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 50)
	private String nome;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horarioInicial;

	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date horarioFinal;

	@Column(nullable = true)
	private int ramal;
	
	@Column(nullable = false)
	private double valorHora;
	
	@Column(nullable = false)
	private int quantidadeEstacoes;
	
	@Column(nullable = false, columnDefinition="boolean default false")
	private boolean estaLivre;

	@Enumerated(EnumType.STRING)
	private TipoSala tipo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getHorarioInicial() {
		return horarioInicial;
	}

	public void setHorarioInicial(Date horarioInicial) {
		this.horarioInicial = horarioInicial;
	}

	public Date getHorarioFinal() {
		return horarioFinal;
	}

	public void setHorarioFinal(Date horarioFinal) {
		this.horarioFinal = horarioFinal;
	}

	public int getRamal() {
		return ramal;
	}

	public void setRamal(int ramal) {
		this.ramal = ramal;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}

	public int getQuantidadeEstacoes() {
		return quantidadeEstacoes;
	}

	public void setQuantidadeEstacoes(int quantidadeEstacoes) {
		this.quantidadeEstacoes = quantidadeEstacoes;
	}

	public boolean isEstaLivre() {
		return estaLivre;
	}

	public void setEstaLivre(boolean estaLivre) {
		this.estaLivre = estaLivre;
	}

	public TipoSala getTipo() {
		return tipo;
	}

	public void setTipo(TipoSala tipo) {
		this.tipo = tipo;
	}

	public long getId() {
		return id;
	}

	

}