package com.br.coworkingSystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.br.coworkingSystem.model.enuns.MeioPagamento;
import com.br.coworkingSystem.model.enuns.TipoFaturamento;

@Entity
public class Faturamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private double valor;

	@Column(nullable = true)
	private double desconto;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataEmissao;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataVencimento;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dataPagamento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private TipoFaturamento tipoFaturamento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MeioPagamento meioPagamento;

	@OneToOne
	@JoinColumn(nullable = true)
	private Consumo consumo;
	
	@OneToOne
	@JoinColumn(nullable = true)
	private Contrato contrato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public TipoFaturamento getTipoFaturamento() {
		return tipoFaturamento;
	}

	public void setTipoFaturamento(TipoFaturamento tipoFaturamento) {
		this.tipoFaturamento = tipoFaturamento;
	}

	public MeioPagamento getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(MeioPagamento meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public Consumo getConsumo() {
		return consumo;
	}

	public void setConsumo(Consumo consumo) {
		this.consumo = consumo;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}
