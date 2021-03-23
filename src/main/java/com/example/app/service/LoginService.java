package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;
import com.example.app.utils.exception.BadRequestException;

@Service
public class LoginService {



	@Autowired
	private UsuarioRepository repository;

	public String solicitarNovaSenha(Usuario usuario) {
		Optional<Usuario> opp = repository.findByEmail(usuario.getEmail());
		Usuario usuario1 = opp.get();
		usuario1.setSenha(usuario.getLogin() + "123");
		repository.save(usuario1);

		String resultado = "{ \"novaSenha\": \"" + usuario1.getSenha() + "\" }";

		return resultado;

	}

	public void alterarSenha(String login, String senhaTemporaria, String novaSenha) {

		Optional<Usuario> opp = repository.findByLogin(login);
		Usuario usuario = opp.get();

		try {
			if (usuario.getLogin() == login && usuario.getSenha() == senhaTemporaria) {
				usuario.setSenha(novaSenha);
				repository.save(usuario);
			}
		} catch (BadRequestException e) {

		}
	}
}