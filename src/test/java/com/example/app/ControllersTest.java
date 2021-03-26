package com.example.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.app.model.Conta;
import com.example.app.model.Conta.TipoConta;
import com.example.app.model.Lancamento;
import com.example.app.model.PlanoConta;
import com.example.app.model.PlanoConta.TipoMovimento;
import com.example.app.model.Usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void LancamentosTestGetAll() throws Exception {
		mockMvc.perform(get("/lancamentos"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void ContasTestGetAll() throws Exception {
		mockMvc.perform(get("/contas"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void PlanosContaTestGetAll() throws Exception {
		mockMvc.perform(get("/planos"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void UsuariosTestGetAll() throws Exception {
		mockMvc.perform(get("/clientes"))
			.andExpect(status().isOk());
	}
	
	@Test
	public void UsuarioTestSave() throws JsonProcessingException, Exception {
		Usuario usuario1 = new Usuario(1,"fulaninho123","12345", "Fulano","758423485683", "fulano@email.com");	
		
		mockMvc.perform(post("/clientes")
			.content(asJsonString(usuario1))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}
	
	@Test
	public void PlanoContaTestSave() throws JsonProcessingException, Exception {
		Usuario usuario1 = new Usuario(1,"fulaninho123","584698", "Fulano","758423485683", "fulano@email.com");	
		PlanoConta plano1 = new PlanoConta(5, usuario1,"LUZ", TipoMovimento.D, false);
		mockMvc.perform(post("/planos")
			.content(asJsonString(plano1))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    } 
	}
	
	@Test
	public void LancamentoTestSave() throws JsonProcessingException, Exception {
		Usuario usuario1 = new Usuario(1,"fulaninho123","584698", "Fulano","758423485683", "fulano@email.com");	
		Conta conta1 = new Conta(usuario1, 1, "123456", 0.0, TipoConta.BANCO);
		PlanoConta plano1 = new PlanoConta(1, usuario1,"RECEITAS", TipoMovimento.R, true);
		Lancamento lancamento = new Lancamento(1, null, plano1, conta1, "recebendo uma grana", "12345", 500000.00, null);
		
		mockMvc.perform(post("/lancamentos")
			.content(asJsonString(lancamento))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}
}