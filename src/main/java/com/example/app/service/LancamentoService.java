package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.model.Lancamento;

@Service
public class LancamentoService {

	public void cadastrarLancamento(Lancamento lancamento) {
		// TODO Auto-generated method stub
		
		// TODO Criar método para criar Lancamento
		//  verificar se o usuario está logado
		//  buscar id do usuario logado
		//  escolher a conta que será trabalhada
		// 
		// IF BANCO
			//tem acesso todos os TipoMovimento do Plano de Conta	
		// IF CREDITO
		   // acesso apenas a TipoMovimento D
			
		//comportamentos // METODOS CHAMADOS NO CONTASERVICE
		//if TipoPlanoConta for D
			//descrescer o valor do debito no saldo da conta 
		//if TipoPlanoConta for R
			//acrescer o valor do debito no saldo da conta
		//if TipoPlanoConta for TC
		//acrescer o valor no saldo da contaCredito e decresce no saldo da contaBanco
		//if TipoPlanoConta for TU
		//acrescer o valor no saldo da contaBanco do outro usuario e decresce no saldo da contaBanco
	}

}

//*EXTRA criar atributo limite nas contas