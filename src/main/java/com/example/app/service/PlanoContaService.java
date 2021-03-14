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
	
	// criar metodo cadastrarPlanoContaPersonalido() para criar plano conta onde o usuario pode 
	// com utilização de login
	public void cadastrarPlanoContaPersonalizado() {
		// verificar se o usuario está logado
		// buscar id do usuario logado
		// o usuario pode escolher apenas os ENUM receita ou despesa // e descrição do plano de conta
		
	}	
}
