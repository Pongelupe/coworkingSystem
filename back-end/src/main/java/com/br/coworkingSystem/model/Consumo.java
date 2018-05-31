package com.br.coworkingSystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
public class Consumo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataInicial;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataFinal;

	@Column(nullable = false)
	private int quantidade;
	
	@Column(nullable = false, columnDefinition="boolean default false")
	private boolean faturado;

	@ManyToOne
	@NotNull
	private Colaborador solicitante;

	@ManyToOne
	@NotNull
	private Cliente cliente;
	
	@OneToOne
	@Null
	private Sala sala;

	@OneToOne
	@Null
	private ProdutoServico produtoServico;

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

	public Colaborador getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Colaborador solicitante) {
		this.solicitante = solicitante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public ProdutoServico getProdutoServico() {
		return produtoServico;
	}

	public void setProdutoServico(ProdutoServico produtoServico) {
		this.produtoServico = produtoServico;
	}

	public long getId() {
		return id;
	}



}
