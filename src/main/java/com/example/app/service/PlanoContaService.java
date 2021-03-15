package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Conta;
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
		
		if(planoConta.getTipo().equals(TipoMovimento.R)|| planoConta.getTipo().equals(TipoMovimento.D)) {			
				
			PlanoConta plano = new PlanoConta();
			plano.setUsuario(planoConta.getUsuario());
			plano.setNome(planoConta.getNome());
			plano.setTipo(planoConta.getTipo());
			
			planoContaRepository.save(plano);
		}
	}


	public PlanoConta findById(Integer id) {
		// TODO Auto-generated method stub
		return planoContaRepository.findById(id).get();
	}
}