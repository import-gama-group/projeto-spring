package com.example.app;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.app.model.Conta;
import com.example.app.model.Conta.TipoConta;
import com.example.app.model.Usuario;
import com.example.app.repository.ContaRepository;
import com.example.app.service.ContaService;

@RunWith(SpringRunner.class)
public class ContaServiceTest {

	@TestConfiguration
	static class contaServiceTestConfiguration {
		
		@Bean 
		public ContaService contaService() {
			return new ContaService();
		}
		
	}

	@Autowired
	ContaService contaService;
	
	@MockBean
	ContaRepository contaRespository;
	
	Usuario usuario1 = new Usuario(1,"fulaninho123","584698", "Fulano","758423485683");
	Usuario usuario2 = new Usuario(2,"beltraninho123","584698", "Beltrano","758423485683");	
	Conta conta1 = new Conta(usuario1, 1, 123456, 1000.0, TipoConta.BANCO);
	Conta conta2 = new Conta(usuario2, 2, 123456, 0.0, TipoConta.CREDITO);

	
	@Test
	public void LancamentoServiceCreditar() {		
		
		contaService.creditar(conta1, 500.00);
		
		Assertions.assertEquals(1500.00, conta1.getSaldo());
		
		Mockito.when(contaRespository.findById(conta1.getId()))
		.thenReturn(java.util.Optional.of(conta1));		
	}
	
	@Test
	public void LancamentoServiceDebitar() {		
		
		contaService.debitar(conta1, 500.00);
		
		Assertions.assertEquals(500.00, conta1.getSaldo());
		
		Mockito.when(contaRespository.findById(conta1.getId()))
		.thenReturn(java.util.Optional.of(conta1));		
	} 
	/* TODO verificar como incluir a segunda conta no MOCKBEAN
	 * 
	@Test
	public void LancamentoServiceTransferir() {		
		
		contaService.transferir(conta1, 500.00, conta2);
		
		Assertions.assertEquals(500.00, conta1.getSaldo());
		
		Mockito.when(contaRespository.findById(conta1.getId()))
		.thenReturn(java.util.Optional.of(conta1));
		
		Mockito.when(contaRespository.findById(conta2.getId()))
		.thenReturn(java.util.Optional.of(conta2));
	} */ 
}