package com.example.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.model.Conta.TipoConta;
import com.example.app.model.PlanoConta.TipoMovimento;
import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;
import com.example.app.utils.exception.BadRequestException;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	PlanoContaService planoContaService;

	@Autowired
	ContaService contaService;

	@Transactional
	public void cadastrarUsuario(Usuario usuario) throws BadRequestException{

		if(usuarioRepository.existsByCpf(usuario.getCpf()))
			throw new BadRequestException("CPF já utilizado!");
		
		String senhaCriptografada = encoder.encode(usuario.getPassword());

		String login = usuario.getLogin();

		usuario.setPassword(senhaCriptografada);

		usuarioRepository.save(usuario);

		contaService.criarConta(123456, TipoConta.BANCO, usuario);
		contaService.criarConta(123456, TipoConta.CREDITO, usuario);

		planoContaService.criarPlanoContaPadrao(usuario, "RECEITAS", TipoMovimento.R, true);
		planoContaService.criarPlanoContaPadrao(usuario, "DESPESAS", TipoMovimento.D, true);
		planoContaService.criarPlanoContaPadrao(usuario, "TRANSFERÊNCIA ENTRE USUÁRIOS", TipoMovimento.TU, true);
		planoContaService.criarPlanoContaPadrao(usuario, "TRANSFERÊNCIA ENTRE CONTAS", TipoMovimento.TC, true);
	
		
	}

	public Usuario findById(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new BadRequestException("Id não encontrado."));
	}


	// public Usuario getByEmail(String email) {

	// return usuarioRepository.findByEmail(email);
	// }



}
