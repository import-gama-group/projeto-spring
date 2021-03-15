package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Conta;
import com.example.app.model.Usuario;
import com.example.app.repository.ContaRepository;
import com.example.app.repository.UsuarioRepository;
import com.example.app.model.Conta.TipoConta;

@Service
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public void criarConta(Integer numero, TipoConta tipo, Usuario usuario) {
		
		Conta conta = new Conta();
		conta.setNumero(numero); 
		conta.setTipo(tipo);
		conta.setUsuario(usuario);
		
		contaRepository.save(conta);
		
		Usuario user = usuarioService.findById(usuario.getId());
		user.getContas().add(conta);
		usuarioRepository.save(user);
		System.out.println(user);
		
	}

	public void debitar(Conta conta, Double valor) {
		conta.setSaldo(conta.getSaldo() - valor);
	}

	public void creditar(Conta conta, Double valor) {
		conta.setSaldo(conta.getSaldo() + valor);
	}
	
	public void transferir(Conta conta, Double valor, Conta contaDestino) {
		conta.setSaldo(conta.getSaldo() - valor);
		contaDestino.setSaldo(contaDestino.getSaldo() + valor);
	}
	
	public Conta findById(Integer id) {
	    return contaRepository.findById(id).get();
	}


	//TODO criar metodo para gerar numero de conta randomico e não repetido
}
