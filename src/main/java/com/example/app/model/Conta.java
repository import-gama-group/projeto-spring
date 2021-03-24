package com.example.app.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.app.utils.DashboardInterface;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView(DashboardInterface.class)
	private Integer id;
	@ManyToOne

	private Usuario usuario;
	
	@JsonView(DashboardInterface.class)
	private Integer numero;
	
	@JsonView(DashboardInterface.class)
	private Double saldo;
	
	@Enumerated(EnumType.STRING)
	@JsonView(DashboardInterface.class)
	private TipoConta tipo;	
	
	public Conta() {
		this(null, null, 0, 0.0, null );
	}
	// Constructor
	public Conta(Usuario user, Integer id, Integer numero, Double saldo, TipoConta tipo) {
		this.id = id;
		this.usuario = user;
		this.numero = numero;
		this.saldo = saldo;
		this.tipo = tipo;
	}
	
	// Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	};	
	public TipoConta getTipo() {
		return tipo;
	}
	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	// ENUM
	public enum TipoConta {
		BANCO, CREDITO
	}
}
