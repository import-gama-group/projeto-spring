package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Conta;
import com.example.app.model.Usuario;
import com.example.app.repository.ContaRepository;
import com.example.app.model.Conta.TipoConta;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;

	public void criarConta(Integer numero, TipoConta tipo, Usuario usuario) {
		
		
		Conta conta = new Conta();
		conta.setNumero(numero); 
		conta.setTipo(tipo);
		conta.setUsuario(usuario);
		
		contaRepository.save(conta);
	}
	
	//TODO criar metodo para gerar numero de conta randomico e não repetido
}
