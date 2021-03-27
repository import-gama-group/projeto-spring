package com.example.app.dto;

import com.example.app.model.PlanoConta.TipoMovimento;

public class LancamentoDTO {
	private Integer id;
	private String data;
	private Double valor;
	private Integer conta;
	private String descrição;
	private Integer plano;
	private TipoMovimento tipo;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Integer getConta() {
		return conta;
	}
	public void setConta(Integer conta) {
		this.conta = conta;
	}
	public String getDescrição() {
		return descrição;
	}
	public void setDescrição(String descrição) {
		this.descrição = descrição;
	}
	public Integer getPlano() {
		return plano;
	}
	public void setPlano(Integer plano) {
		this.plano = plano;
	}
	public TipoMovimento getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimento tipo) {
		this.tipo = tipo;
	}
	
	
}
