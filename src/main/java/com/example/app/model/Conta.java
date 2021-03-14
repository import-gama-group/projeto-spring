package com.example.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Conta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Usuario usuario;
	private Integer numero;
	private Double saldo;
	@Enumerated(EnumType.STRING)
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
