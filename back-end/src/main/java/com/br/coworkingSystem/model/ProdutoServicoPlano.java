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
public class ProdutoServicoPlano implements Serializable {

	@Column(nullable = false)
	private int quantidade;

	@Id
	@ManyToOne
	@JsonBackReference
	@JoinColumn(nullable = false)
	private Plano plano;

	@Id
	@ManyToOne
	@JoinColumn(nullable = false)
	private ProdutoServico produtoServico;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public ProdutoServico getProdutoServico() {
		return produtoServico;
	}

	public void setProdutoServico(ProdutoServico produtoServico) {
		this.produtoServico = produtoServico;
	}

}
