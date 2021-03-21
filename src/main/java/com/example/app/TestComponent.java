package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.app.model.Usuario;
import com.example.app.service.UsuarioService;


@Component
public class TestComponent {
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void test() {
		Usuario user = new Usuario();
		user.setLogin("gso");
		user.setCpf("1321654654");
		user.setEmail("asdad");
		user.setName("gso");
		user.setPassword(encoder.encode("gso123"));
		service.cadastrarUsuario(user);

		System.out.println("FUNCIONADO");
	}
}