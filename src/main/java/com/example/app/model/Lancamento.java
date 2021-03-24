package com.example.app.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.app.utils.DashboardInterface;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Lancamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DashboardInterface.class)
	private Integer id;
	
	@JsonView(DashboardInterface.class)
	private Date date;
	
	@ManyToOne
	@JsonView(DashboardInterface.class)
	private PlanoConta plano;
	
	@ManyToOne
	@JsonView(DashboardInterface.class)
	private Conta conta;
	
	@JsonView(DashboardInterface.class)
	private String descricao;
	
	private String login;
	
	@JsonView(DashboardInterface.class)
	private Double valor;
	
	@ManyToOne
	@JsonView(DashboardInterface.class)
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
