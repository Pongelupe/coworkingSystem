package com.br.coworkingSystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Plano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, length = 50, nullable = false)
	private String nome;

	@Column(length = 400, nullable = true)
	private String descricao;

	@Column(length = 400, nullable = false)
	private double valor;

	@OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ProdutoServicoPlano> produtosServicos;

	@OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<SalaPlano> salas;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ProdutoServicoPlano> getProdutosServicos() {
		return produtosServicos;
	}

	public List<SalaPlano> getSalas() {
		return salas;
	}

}
