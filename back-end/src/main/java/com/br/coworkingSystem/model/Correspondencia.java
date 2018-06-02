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
public class Correspondencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 50, nullable = false)
	private String remetente;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataRecebimento;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true)
	private Date dataEntrega;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = true, length=400)
	private Date observacao;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Cliente cliente;

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Date getObservacao() {
		return observacao;
	}

	public void setObservacao(Date observacao) {
		this.observacao = observacao;
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
	
	
}
