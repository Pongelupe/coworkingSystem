package com.br.coworkingSystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nome;

	@Temporal(TemporalType.TIME)
	private Date horarioInicial;

	@Temporal(TemporalType.TIME)
	private Date horarioFinal;

	private int ramal;
	private double valorHora;
	private int quantidadeEstacoes;
	private boolean estaLivre;

	@Enumerated(EnumType.STRING)
	private TipoSala tipoSala;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public TipoSala getTipoSala() {
		return tipoSala;
	}

	public void setTipoSala(TipoSala tipoSala) {
		this.tipoSala = tipoSala;
	}

	public boolean isEstaLivre() {
		return estaLivre;
	}

	public void setEstaLivre(boolean estaLivre) {
		this.estaLivre = estaLivre;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", nome=" + nome + ", horarioInicial=" + horarioInicial + ", horarioFinal="
				+ horarioFinal + ", ramal=" + ramal + ", valorHora=" + valorHora + ", quantidadeEstacoes="
				+ quantidadeEstacoes + ", tipoSala=" + tipoSala + "]";
	}

}