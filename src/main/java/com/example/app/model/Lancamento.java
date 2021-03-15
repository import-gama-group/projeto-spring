package com.example.app.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date date;
	@ManyToOne
	private PlanoConta plano;
	@ManyToOne
	private Conta conta;
	private String descricao;
	private String login;
	private Double valor;
	@ManyToOne
	private Conta contaDestino;
	
	public Lancamento() {
		this(null, null, null, null, null, null, null, null);
	}
	
	// Constructor
	public Lancamento(Integer id, Date date, PlanoConta plano, Conta conta, String descricao,
			String login, Double valor, Conta contaDestino) {
		super();
		this.id = id;
		this.date = date;
		this.plano = plano;
		this.conta = conta;
		this.descricao = descricao;
		this.login = login;
		this.valor = valor;
		this.contaDestino = contaDestino;
	}
	
	// Getter and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public PlanoConta getPlano() {
		return plano;
	}
	public void setPlano(PlanoConta d) {
		this.plano = d;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Conta getContaDestino() {
		return contaDestino;
	}
	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}
}
