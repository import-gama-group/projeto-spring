package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

		if (planoConta.getTipo().equals(TipoMovimento.D)){

			PlanoConta plano = new PlanoConta();
			plano.setNome(planoConta.getNome());
			plano.setUsuario(planoConta.getUsuario());
			plano.setTipo(planoConta.getTipo());

			planoContaRepository.save(plano);
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Tipo de Movimento não autorizado.");
		}

	}

	public PlanoConta findById(Integer id) {
		return planoContaRepository.findById(id)
				.orElseThrow(() -> new DefaultErrorException("Plano de Conta não encontrado."));
	}

	public PlanoConta alterarNomePlanoConta(Integer id, String novoNome) {

		Optional<PlanoConta> opp = planoContaRepository.findById(id);
		PlanoConta plano = opp.get();

		if (plano.getPadrao().equals(false)) {
			plano.setNome(novoNome);
			planoContaRepository.save(plano);	
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Não é permitido alterar um Plano de Conta Padrão");
		}

		return plano;
	}


	public void deletarPlanoConta(Integer id) {

		Optional<PlanoConta> opp = planoContaRepository.findById(id);
		PlanoConta plano = opp.get();

		if (plano.getPadrao().equals(false)) {
			planoContaRepository.delete(plano);	
		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Não é permitido apagar um Plano de Conta Padrão");
		}

	}

}