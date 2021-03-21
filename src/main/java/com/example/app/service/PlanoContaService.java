package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.PlanoConta;
import com.example.app.model.Usuario;
import com.example.app.repository.PlanoContaRepository;
import com.example.app.utils.exception.BadRequestException;
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
		// TODO mensagem de erro se o usuário tentar cadastrar um tipo transferência
		
		if (!planoConta.getTipo().equals(TipoMovimento.D)) {
			// TODO mensagem de erro (Tipo de Movimento inválido)
		} else {
		
			PlanoConta plano = new PlanoConta();
			plano.setNome(planoConta.getNome());
			plano.setUsuario(planoConta.getUsuario());
			plano.setTipo(planoConta.getTipo());
			
			planoContaRepository.save(plano);
		}
	}


	public PlanoConta findById(Integer id) {
		return planoContaRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Plano de Conta não encontrado."));
	}
}