package com.example.app.dto;

import com.example.app.model.PlanoConta.TipoMovimento;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "data", "valor", "descricao", "conta", "plano", "tipo"})
public class LancamentoDTO {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("data")
	private String data;
	
	@JsonProperty("valor")
	private Double valor;
	
	@JsonProperty("conta")
	private Integer conta;
	
	@JsonProperty("descricao")
	private String descrição;
	
	@JsonProperty("plano")
	private Integer plano;
	
	@JsonProperty("tipo")
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
