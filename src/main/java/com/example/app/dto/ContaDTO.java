package com.example.app.dto;

import java.util.List;

import com.example.app.model.Conta.TipoConta;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "numero", "saldo", "tipo", "totalReceitas", "totalDespesas", "balanco", "lancamentos"})
public class ContaDTO {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("numero")
	private String numero;
	
	@JsonProperty("saldo")
	private Double saldo;
	
	@JsonProperty("tipo")
	private TipoConta tipo;
	
	@JsonProperty("totalReceitas")
	private Double totalReceitas;
	
	@JsonProperty("totalDespesas")
	private Double totalDespesas;
	
	@JsonProperty("balanco")
	private Double balanco;
	
	@JsonProperty("lancamentos")
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

	public Double getTotalReceitas() {
		return totalReceitas;
	}

	public void setTotalReceitas(Double totalReceitas) {
		this.totalReceitas = totalReceitas;
	}

	public Double getTotalDespesas() {
		return totalDespesas;
	}

	public void setTotalDespesas(Double totalDespesas) {
		this.totalDespesas = totalDespesas;
	}

	public Double getBalanco() {
		return balanco;
	}

	public void setBalanco(Double balanco) {
		this.balanco = balanco;
	}

	public List<LancamentoDTO> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoDTO> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
}
