package com.example.app.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Lancamento;
import com.example.app.service.LancamentoService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	LancamentoService lancamentoService;
	
	
	@GetMapping
	public List<Lancamento> listarLancamentos(@RequestParam String dataInicial, @RequestParam String dataFinal, @RequestParam String login ) throws ParseException{
		return lancamentoService.listarLancamentosPorData(dataInicial, dataFinal, login);
	}
	
	
		
	
	

}
