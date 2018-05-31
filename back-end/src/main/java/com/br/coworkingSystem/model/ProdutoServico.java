package com.br.coworkingSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProdutoServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	private double valor;
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

	@Override
	public String toString() {
		return "ProdutoServico [id=" + id + ", nome=" + nome + ", valor=" + valor + ", ehProduto=" + ehProduto + "]";
	}

}
