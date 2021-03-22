package com.example.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.PlanoConta;
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
	public List<PlanoConta> listar() {
		return planoContaRepository.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	List<PlanoConta> one(@PathVariable Integer id) {	    
		return planoContaRepository.findByUsuarioId(id);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PlanoConta> findById(@PathVariable Integer id) {
		PlanoConta obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void cadastrar(@RequestBody PlanoConta planoConta) {

		service.cadastrarPlanoContaPersonalizado(planoConta);

	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT) 
	public @ResponseBody PlanoConta alterarNomePlanoConta(@PathVariable("id") Integer id, String novoNome) {
		
		return service.alterarNomePlanoConta(id, novoNome);
		
	} 
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deletarPlanoConta(@PathVariable("id") Integer id) {
	    
		service.deletarPlanoConta(id);
		
	}
}
