package com.example.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "contaDebito", "contaCredito"})
public class DashboardDTO {
	
	@JsonProperty("contaDebito")
	private ContaDTO ContaDebito;
	
	@JsonProperty("contaCredito")
	private ContaDTO ContaCredito;
	
	public ContaDTO getContaDebito() {
		return ContaDebito;
	}
	public void setContaDebito(ContaDTO contaDebito) {
		ContaDebito = contaDebito;
	}
	public ContaDTO getContaCredito() {
		return ContaCredito;
	}
	public void setContaCredito(ContaDTO contaCredito) {
		ContaCredito = contaCredito;
	}
	
	
}
