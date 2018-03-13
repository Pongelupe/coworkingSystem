package com.br.coworkingSystem.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Consumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicial;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;

	private int quantidade;
	private boolean faturado;

	@ManyToOne
	private Cliente solicitante;

	@OneToOne
	private Sala sala;

	@OneToOne
	private ProdutoServico servico;

	private String tipoConsumo;
	private String statusConsumo;

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isFaturado() {
		return faturado;
	}

	public void setFaturado(boolean faturado) {
		this.faturado = faturado;
	}

	public Cliente getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Cliente solicitante) {
		this.solicitante = solicitante;
	}

	public String getTipoConsumo() {
		return tipoConsumo;
	}

	public void setTipoConsumo(String tipoConsumo) {
		this.tipoConsumo = tipoConsumo;
	}

	public String getStatusConsumo() {
		return statusConsumo;
	}

	public void setStatusConsumo(String statusConsumo) {
		this.statusConsumo = statusConsumo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProdutoServico getServico() {
		return servico;
	}

	public void setServico(ProdutoServico servico) {
		this.servico = servico;
	}

	@Override
	public String toString() {
		return "Consumo [id=" + id + ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + ", quantidade="
				+ quantidade + ", faturado=" + faturado + ", solicitante=" + solicitante + ", sala=" + sala
				+ ", tipoConsumo=" + tipoConsumo + ", statusConsumo=" + statusConsumo + "]";
	}

}
