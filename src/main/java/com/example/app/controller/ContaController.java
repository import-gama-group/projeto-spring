package com.example.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Conta;
import com.example.app.repository.ContaRepository;
import com.example.app.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	ContaService service;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public List<Conta> listar(){
		return contaRepository.findAll();
	}
}
