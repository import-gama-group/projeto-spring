package com.example.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "conta_debito", "conta_credito"})
public class DashboardDTO {
	
    @JsonProperty("conta_debito")
	private ContaDTO ContaDebito;
    @JsonProperty("conta_credito")
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
