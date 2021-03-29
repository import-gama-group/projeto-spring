package com.example.app.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.model.Conta;
import com.example.app.model.Usuario;
import com.example.app.repository.ContaRepository;
import com.example.app.utils.exception.DefaultErrorException;
import com.example.app.model.Conta.TipoConta;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	public void criarConta(TipoConta tipo, Usuario usuario, Double limite) {
		
		Conta conta = new Conta();
		conta.setNumero(criarNumeroConta());
		
		if(contaRepository.existsByNumero(conta.getNumero()))
				throw new DefaultErrorException(HttpStatus.BAD_REQUEST, "Número de conta já existente!");		
		
		conta.setTipo(tipo);
		conta.setUsuario(usuario);
		conta.setLimite(limite);
		
		contaRepository.save(conta);
		
	}
	
	public void debitar(Conta conta, Double valor) {

		if ((conta.getLimite() + conta.getSaldo()) < valor) {
			throw new DefaultErrorException(HttpStatus.NOT_ACCEPTABLE, "Saldo insuficiente");
		} else {
			conta.setSaldo(conta.getSaldo() - valor);
		}
	}

	public void creditar(Conta conta, Double valor) {
		conta.setSaldo(conta.getSaldo() + valor);
	}
	
	public void transferir(Conta conta, Double valor, Conta contaDestino) {
		
		if ((conta.getLimite() + conta.getSaldo()) < valor) {
			throw new DefaultErrorException(HttpStatus.NOT_ACCEPTABLE, "Saldo insuficiente");
		} else {
			conta.setSaldo(conta.getSaldo() - valor);
		}
		
		Conta contaReceber = this.findById(contaDestino.getId());
		
		contaReceber.setSaldo(contaReceber.getSaldo() + valor);
	}
	
	public Conta findById(Integer id) {
	    return contaRepository.findById(id)
	    		.orElseThrow(() -> new DefaultErrorException(HttpStatus.BAD_REQUEST, "Conta não encontrado."))
	    		;
	}
	
	private String criarNumeroConta() {
		Random rand = new Random();
		Integer rand_int = rand.nextInt(100000);
		String numero = String.format("%06d", rand_int);
		return numero;
	} 
}
