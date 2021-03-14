package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Usuario;
import com.example.app.repository.UsuarioRepository;
import com.example.app.service.UsuarioService;

@RestController
@RequestMapping("/clientes")
public class UsuarioController {
	
	@Autowired
	UsuarioService service;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<Usuario> listar(){
		return usuarioRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
		public void cadastrar(@RequestBody Usuario usuario){
		
		service.cadastrarUsuario(usuario);
		
	}
}
