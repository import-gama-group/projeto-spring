package com.example.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;

@Service
public class LoginService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public void solicitarNovaSenha(Usuario usuario) {
		Optional<Usuario> opp = repository.findByEmail(usuario.getEmail());
		Usuario usuario1 = opp.get();
		usuario1.setPassword(usuario.getLogin() + "123");
		repository.save(usuario1);
	}
	
	public void alterarSenha(String login, String novaSenha) {
		Optional<Usuario> opp = repository.findByLogin(login);
		Usuario usuario = opp.get();
		usuario.setPassword(novaSenha);
		repository.save(usuario);
	}
}
