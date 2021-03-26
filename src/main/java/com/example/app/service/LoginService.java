package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;


@Service
public class LoginService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	PasswordEncoder encoder;

	public String solicitarNovaSenha(Usuario usuario) {
		Optional<Usuario> opp = repository.findByEmail(usuario.getEmail());
		Usuario usuario1 = opp.get();

		System.out.println(usuario1);
		usuario1.setSenha(usuario.getLogin() + "123");
		repository.save(usuario1);

		String resultado = "{ \"senhaTemporaria\": \""+usuario1.getSenha()+"\" }";

		return resultado;

	}

	public void alterarSenha(String login, String senhaTemporaria, String novaSenha) {

		Optional<Usuario> opp = repository.findByLogin(login);
		Usuario usuario = opp.get();


		if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senhaTemporaria) ) {
			//String senhaCrypt = encoder.encode(novaSenha); NÃO SERÁ NA UTILIZADO NA ALTERAÇÃO DE SENHA PARA FINS DEMONSTRATIVOS DA APRESENTAÇÃO
			//usuario.setSenha(senhaCrypt);
			
			usuario.setSenha(novaSenha);
			repository.save(usuario);

		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login e/ou senha temporária inválidos.");
		}		
	}
}