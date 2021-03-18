package com.example.app.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Conta;
import com.example.app.model.Lancamento;
import com.example.app.model.PlanoConta;
import com.example.app.model.PlanoConta.TipoMovimento;
import com.example.app.model.Usuario;
import com.example.app.repository.ContaRepository;
import com.example.app.repository.LancamentoRepository;

@Service
public class LancamentoService {
	
	@Autowired
	LancamentoRepository lancamentoRepository;
	
	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	ContaService contaService;
	
	@Autowired 
	PlanoContaService planoContaService;
	
	@Autowired
	UsuarioService usuarioService;

	public void cadastrarLancamento(Lancamento lancamento) {

		//  verificar se o usuario está logado
		//  buscar id do usuario logado
		try {
			
			Conta conta = contaService.findById(lancamento.getConta().getId());
			Usuario usuario = usuarioService.findById(conta.getUsuario().getId());
			PlanoConta planoConta = planoContaService.findById(lancamento.getPlano().getId());
			
			Date data = new Date(System.currentTimeMillis()); // TODO tratamento da data
			
			Lancamento l = new Lancamento();
			l.setDate(data);
			l.setPlano(planoConta);
			l.setConta(conta);
			l.setValor(lancamento.getValor());
			l.setDescricao(lancamento.getDescricao());
			
			Double valor = lancamento.getValor();
			
			if(planoConta.getTipo().equals(TipoMovimento.R) && planoConta.getNome().isEmpty()) {
				planoConta.setNome("RECEITA");	
				contaService.creditar(conta, valor);
			}	else if (planoConta.getTipo().equals(TipoMovimento.R)) {
				contaService.creditar(conta, valor);
			} else if (planoConta.getTipo().equals(TipoMovimento.D) && planoConta.getNome().isEmpty()) {
				planoConta.setNome("DESPESA");
				contaService.debitar(conta, valor);	
			} else if (planoConta.getTipo().equals(TipoMovimento.D)) {				
				contaService.debitar(conta, valor);				
			} else if (planoConta.getTipo().equals(TipoMovimento.TC)){			
				List<Conta> contas = contaRepository.findByUsuarioId(usuario.getId());
				l.setContaDestino(contas.get(1));
				Conta contaDestino = contas.get(1);
				contaService.transferir(conta, valor, contaDestino);
			} else  if (planoConta.getTipo().equals(TipoMovimento.TU)){
				Conta contaDest = lancamento.getContaDestino();;
				contaService.transferir(conta, valor, contaDest );
			}
			
			lancamentoRepository.save(l);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}