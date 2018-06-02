package com.br.coworkingSystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contrato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private int diaVencimento;

	@Column(nullable = false)
	private int mesesDuracaoContrato;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dataEncerrado;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Plano plano;

	public int getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(int diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public int getMesesDuracaoContrato() {
		return mesesDuracaoContrato;
	}

	public void setMesesDuracaoContrato(int mesesDuracaoContrato) {
		this.mesesDuracaoContrato = mesesDuracaoContrato;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataEncerrado() {
		return dataEncerrado;
	}

	public void setDataEncerrado(Date dataEncerrado) {
		this.dataEncerrado = dataEncerrado;
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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

}
