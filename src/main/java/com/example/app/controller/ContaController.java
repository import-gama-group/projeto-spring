package com.example.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Conta;
import com.example.app.repository.ContaRepository;
import com.example.app.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	ContaService Contaservice;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public List<Conta> listar(){
		return contaRepository.findAll();
	}
	
	@GetMapping("/usuario_id/{id}")
		List<Conta> one(@PathVariable Integer id) {
	    
		return contaRepository.findByUsuarioId(id);
	    // TODO .orElseThrow(() -> new EmployeeNotFoundException(id));
	}
}
