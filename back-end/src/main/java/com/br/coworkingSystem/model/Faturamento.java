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

import com.br.coworkingSystem.model.enuns.StatusFaturamento;
import com.br.coworkingSystem.model.enuns.TipoFaturamento;

@Entity
public class Faturamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double valorFaturamento;
	private double descontoFaturamento;

	@Temporal(TemporalType.DATE)
	private Date dtEmissao;
	@Temporal(TemporalType.DATE)
	private Date dtVencimento;

	@Enumerated(EnumType.STRING)
	private TipoFaturamento tipoFaturamento;

	@Enumerated(EnumType.STRING)
	private StatusFaturamento statusFaturamento;

	public double getValorFaturamento() {
		return valorFaturamento;
	}

	public void setValorFaturamento(double valorFaturamento) {
		this.valorFaturamento = valorFaturamento;
	}

	public double getDescontoFaturamento() {
		return descontoFaturamento;
	}

	public void setDescontoFaturamento(double descontoFaturamento) {
		this.descontoFaturamento = descontoFaturamento;
	}

	public Date getDtEmissao() {
		return dtEmissao;
	}

	public void setDtEmissao(Date dtEmissao) {
		this.dtEmissao = dtEmissao;
	}

	public Date getDtVencimento() {
		return dtVencimento;
	}

	public void setDtVencimento(Date dtVencimento) {
		this.dtVencimento = dtVencimento;
	}

	public TipoFaturamento getTipoFaturamento() {
		return tipoFaturamento;
	}

	public void setTipoFaturamento(TipoFaturamento tipoFaturamento) {
		this.tipoFaturamento = tipoFaturamento;
	}

	public StatusFaturamento getStatusFaturamento() {
		return statusFaturamento;
	}

	public void setStatusFaturamento(StatusFaturamento statusFaturamento) {
		this.statusFaturamento = statusFaturamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Faturamento [id=" + id + ", valorFaturamento=" + valorFaturamento + ", descontoFaturamento="
				+ descontoFaturamento + ", dtEmissao=" + dtEmissao + ", dtVencimento=" + dtVencimento
				+ ", tipoFaturamento=" + tipoFaturamento + ", statusFaturamento=" + statusFaturamento + "]";
	}

}
