package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;

@Service
public class LoginService {

	@Autowired
	private UsuarioRepository repository;

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
			usuario.setSenha(novaSenha);
			repository.save(usuario);

		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login e/ou senha temporária inválidos.");
		}		
	}
}