package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.PlanoConta;
import com.example.app.model.Usuario;
import com.example.app.repository.PlanoContaRepository;

import com.example.app.model.PlanoConta.TipoMovimento;

@Service
public class PlanoContaService {

	@Autowired
	PlanoContaRepository planoContaRepository;
	
	public void criarPlanoContaPadrao(Usuario usuario, String nome, TipoMovimento tipo, Boolean padrao) {
		
		PlanoConta plano = new PlanoConta();
		plano.setUsuario(usuario);
		plano.setNome(nome);
		plano.setTipo(tipo);
		plano.setPadrao(padrao);
		
		planoContaRepository.save(plano);
	}
	
	
	public void cadastrarPlanoContaPersonalizado(PlanoConta planoConta) {
		// TODO verificar se o usuario está logado
		
			PlanoConta plano = new PlanoConta();
					
			if(planoConta.getTipo().equals(TipoMovimento.R) && planoConta.getNome().isEmpty()) {			
				plano.setNome("RECEITA");
			}	else if (planoConta.getTipo().equals(TipoMovimento.D) && planoConta.getNome().isEmpty()) {
				plano.setNome("DESPESA");
			} else if (planoConta.getTipo().equals(TipoMovimento.TC) && planoConta.getNome().isEmpty()){
				plano.setNome("TRANSFERÊNCIA ENTRE CONTAS");
			} else if (planoConta.getTipo().equals(TipoMovimento.TU) && planoConta.getNome().isEmpty()){
				plano.setNome("TRANSFERÊNCIA ENTRE USUÁRIOS");
			} else {
				plano.setNome(planoConta.getNome());
			}
			
			plano.setUsuario(planoConta.getUsuario());
			plano.setTipo(planoConta.getTipo());
			
			planoContaRepository.save(plano);
		
	}


	public PlanoConta findById(Integer id) {
		return planoContaRepository.findById(id).get();
	}
}