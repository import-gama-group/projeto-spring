package com.example.app.dto;

import java.util.List;

import com.example.app.model.Conta.TipoConta;

public class ContaDTO {
	private Integer id;
	private String numero;
	private Double saldo;
	private TipoConta tipo;
	
	private Double totalCreditos;
	private Double totalDebitos;
	
	private List<LancamentoDTO> lancamentos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public TipoConta getTipo() {
		return tipo;
	}

	public void setTipo(TipoConta tipo) {
		this.tipo = tipo;
	}

	public Double getTotalCreditos() {
		return totalCreditos;
	}

	public void setTotalCreditos(Double totalCreditos) {
		this.totalCreditos = totalCreditos;
	}

	public Double getTotalDebitos() {
		return totalDebitos;
	}

	public void setTotalDebitos(Double totalDebitos) {
		this.totalDebitos = totalDebitos;
	}

	public List<LancamentoDTO> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoDTO> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	
	
}
