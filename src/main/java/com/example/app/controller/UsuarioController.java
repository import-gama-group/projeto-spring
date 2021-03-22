package com.example.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Integer id){
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
		public void cadastrar(@Valid @RequestBody Usuario usuario){
		service.cadastrarUsuario(usuario);
		
	}
}
