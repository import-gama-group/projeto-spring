package com.example.app.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.DashboardDTO;
import com.example.app.service.LancamentoService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	LancamentoService lancamentoService;
	

	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DashboardDTO listarLancamentos(@RequestParam String dataInicial, 
										  @RequestParam String dataFinal, 
										  @RequestParam String login ) throws ParseException {
		
		return lancamentoService.listarLancamentosPorData(dataInicial, dataFinal, login);
	}
	
}