package com.br.coworkingSystem.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Plano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;
	private String descricao;
	private double valorPlano;

	@OneToOne
	private Pacote pacote;

	@OneToMany
	private List<ProdutoServico> produtosServicos;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValorPlano() {
		return valorPlano;
	}

	public void setValorPlano(double valorPlano) {
		this.valorPlano = valorPlano;
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public List<ProdutoServico> getProdutosServicos() {
		return produtosServicos;
	}

	@Override
	public String toString() {
		return "Plano [nome=" + nome + ", descricao=" + descricao + ", valorPlano=" + valorPlano + ", pacote=" + pacote
				+ ", produtosServicos=" + produtosServicos + "]";
	}

}
