package com.example.app.dto;

public class DashboardDTO {
	
	private ContaDTO ContaDebito;

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
