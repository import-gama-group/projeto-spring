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

import com.example.app.model.PlanoConta;
import com.example.app.model.Usuario;
import com.example.app.repository.PlanoContaRepository;
import com.example.app.service.PlanoContaService;

@RestController
@RequestMapping("/planos")
public class PlanoContaController {
	
	@Autowired
	PlanoContaService service;
	
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
	@GetMapping
	public List<PlanoConta> listar(){
		return planoContaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrar(@RequestBody PlanoConta planoConta){
	
	service.cadastrarPlanoContaPersonalizado(planoConta);
	
}

}
