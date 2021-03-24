package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.model.PlanoConta;
import com.example.app.model.Usuario;
import com.example.app.repository.PlanoContaRepository;
import com.example.app.utils.exception.DefaultErrorException;
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
		// TODO mensagem de erro se o usuário tentar cadastrar um tipo transferência
		
		
			if (!planoConta.getTipo().equals(TipoMovimento.D)){
				} else {
				PlanoConta plano = new PlanoConta();
				plano.setNome(planoConta.getNome());
				plano.setUsuario(planoConta.getUsuario());
				plano.setTipo(planoConta.getTipo());
				
				planoContaRepository.save(plano);
			}
			throw new DefaultErrorException("Erro ao cadastrar plano de contas");
			
	}


	public PlanoConta findById(Integer id) {
		return planoContaRepository.findById(id)
				.orElseThrow(() -> new DefaultErrorException("Plano de Conta não encontrado."));
	}


	public PlanoConta alterarNomePlanoConta(Integer id, String novoNome) {

		Optional<PlanoConta> opp = planoContaRepository.findById(id);
		PlanoConta plano = opp.get();

		
			if (plano.getPadrao() == false) {
				plano.setNome(novoNome);
				planoContaRepository.save(plano);
				throw new DefaultErrorException("Não foi possível alterar o plano de conta");
			}
			
		
		return plano;
	}


	public void deletarPlanoConta(Integer id) {
		
		Optional<PlanoConta> opp = planoContaRepository.findById(id);
	    PlanoConta plano = opp.get();
	    
	    
			if (plano.getPadrao() == false) {
				planoContaRepository.delete(plano);	
				throw new DefaultErrorException("Não foi possível deletar plano de contas.");
			}
			
			
	}
	
}