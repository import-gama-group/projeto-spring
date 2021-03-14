package com.example.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.app.model.Conta.TipoConta;
import com.example.app.model.PlanoConta.TipoMovimento;
import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	PlanoContaService planoContaService;
	
	@Autowired
	ContaService contaService;
		
	@Transactional
	public void cadastrarUsuario(Usuario usuario){
				
		usuarioRepository.save(usuario);
		
		contaService.criarConta(123456, TipoConta.BANCO, usuario);
		contaService.criarConta(123456, TipoConta.CREDITO, usuario);
		
		
		planoContaService.criarContaPadrao(usuario, "RECEITAS", TipoMovimento.R, true);
		planoContaService.criarContaPadrao(usuario, "DESPESAS", TipoMovimento.D, true);
		planoContaService.criarContaPadrao(usuario, "TRANSFERÊNCIA ENTRE USUÁRIOS", TipoMovimento.TU, true);
		planoContaService.criarContaPadrao(usuario, "TRANSFERÊNCIA ENTRE CONTAS", TipoMovimento.TC, true);
	}
}
