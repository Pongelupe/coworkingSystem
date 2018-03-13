package com.br.coworkingSystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int diaVencimento;
	private int duracaoContrato;
	private Date dataInicio;
	private Date dataFim;
	private int multa;
	private int juros;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public int getDuracaoContrato() {
		return duracaoContrato;
	}

	public void setDuracaoContrato(int duracaoContrato) {
		this.duracaoContrato = duracaoContrato;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public int getMulta() {
		return multa;
	}

	public void setMulta(int multa) {
		this.multa = multa;
	}

	public int getJuros() {
		return juros;
	}

	public void setJuros(int juros) {
		this.juros = juros;
	}

	@Override
	public String toString() {
		return "Contrato [id=" + id + ", diaVencimento=" + diaVencimento + ", duracaoContrato=" + duracaoContrato
				+ ", dataInicio=" + dataInicio + ", dataFim=" + dataFim + ", multa=" + multa + ", juros=" + juros + "]";
	}

}
