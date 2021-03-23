package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.model.PlanoConta;
import com.example.app.model.Usuario;
import com.example.app.repository.PlanoContaRepository;
import com.example.app.utils.exception.BadRequestException;
import com.example.app.model.PlanoConta.TipoMovimento;

@Service
public class PlanoContaService {

	@Autowired
	PlanoContaRepository planoContaRepository;
	
	@Transactional
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
		
		
			if (!planoConta.getTipo().equals(TipoMovimento.D)){
				} else {
				PlanoConta plano = new PlanoConta();
				plano.setNome(planoConta.getNome());
				plano.setUsuario(planoConta.getUsuario());
				plano.setTipo(planoConta.getTipo());
				
				planoContaRepository.save(plano);
			}
			throw new BadRequestException("Erro ao cadastrar plano de contas");
			
	}


	public PlanoConta findById(Integer id) {
		return planoContaRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Plano de Conta não encontrado."));
	}
}