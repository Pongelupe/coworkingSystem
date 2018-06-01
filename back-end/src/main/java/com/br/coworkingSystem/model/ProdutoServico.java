package com.br.coworkingSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProdutoServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, length = 50, nullable = false)
	private String nome;

	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false, columnDefinition = "boolean default false")
	private boolean ehProduto;

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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isEhProduto() {
		return ehProduto;
	}

	public void setEhProduto(boolean ehProduto) {
		this.ehProduto = ehProduto;
	}

	
}
