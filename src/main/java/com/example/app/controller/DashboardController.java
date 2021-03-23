package com.example.app.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.service.LancamentoService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	LancamentoService lancamentoService;
	

	@GetMapping
	public List<Object> listarLancamentos(@RequestParam(value="listarLancamentos", defaultValue="dataInicial") String dataInicial, 
											@RequestParam(value="listarLancamentos", defaultValue="dataFinal") String dataFinal, 
											@RequestParam(value="listarLancamentos", defaultValue="conta") String login ) throws ParseException{
		return lancamentoService.listarLancamentosPorData(dataInicial, dataFinal, login);
	}
	
	
		
	
	

}
