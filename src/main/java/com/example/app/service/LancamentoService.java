package com.example.app.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.model.Conta;
import com.example.app.model.Lancamento;
import com.example.app.model.PlanoConta;
import com.example.app.model.PlanoConta.TipoMovimento;
import com.example.app.model.Usuario;
import com.example.app.repository.ContaRepository;
import com.example.app.repository.LancamentoRepository;
import com.example.app.repository.UsuarioRepository;
import com.example.app.utils.exception.BadRequestException;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private ContaService contaService;

	@Autowired
	private PlanoContaService planoContaService;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Transactional

	public void cadastrarLancamento(Lancamento lancamento) {

		try {

			Conta conta = contaService.findById(lancamento.getConta().getId());
			Usuario usuario = usuarioService.findById(conta.getUsuario().getId());
			PlanoConta planoConta = planoContaService.findById(lancamento.getPlano().getId());

			Date data = Calendar.getInstance().getTime();

			Lancamento l = new Lancamento();
			l.setDate(data);
			l.setPlano(planoConta);
			l.setConta(conta);
			l.setValor(lancamento.getValor());
			l.setDescricao(lancamento.getDescricao());

			Double valor = lancamento.getValor();

			if (planoConta.getTipo().equals(TipoMovimento.R) && planoConta.getNome().isEmpty()) {
				planoConta.setNome("RECEITA");
				contaService.creditar(conta, valor);
			} else if (planoConta.getTipo().equals(TipoMovimento.R)) {
				contaService.creditar(conta, valor);
			} else if (planoConta.getTipo().equals(TipoMovimento.D) && planoConta.getNome().isEmpty()) {
				planoConta.setNome("DESPESA");
				contaService.debitar(conta, valor);
			} else if (planoConta.getTipo().equals(TipoMovimento.D)) {
				contaService.debitar(conta, valor);
			} else if (planoConta.getTipo().equals(TipoMovimento.TC)) {
				List<Conta> contas = contaRepository.findByUsuarioId(usuario.getId());
				l.setContaDestino(contas.get(1));
				Conta contaDestino = contas.get(1);
				contaService.transferir(conta, valor, contaDestino);
			} else if (planoConta.getTipo().equals(TipoMovimento.TU)) {
				Conta contaDest = lancamento.getContaDestino();
				contaService.transferir(conta, valor, contaDest);
			}

			lancamentoRepository.save(l);

		} catch (BadRequestException e) {
			 
		}
	}

	public Map<String, Object> listarLancamentosPorData(String dataI, String dataF, String login) throws ParseException {

		Optional<Usuario> opp = usuarioRepository.findByLogin(login);
		Usuario usuario = opp.get();
		List<Conta> contas = contaRepository.findByUsuarioId(usuario.getId());
		Conta contaBanco = contas.get(0);
		Conta contaCredito = contas.get(1);

		DateFormat formatter1 = new SimpleDateFormat("MM-dd-yy");
		Date dataInicial = formatter1.parse(dataI);
		DateFormat formatter2 = new SimpleDateFormat("MM-dd-yy");
		Date dataFinal = formatter2.parse(dataF);

		List<Lancamento> lancamentos = lancamentoRepository.findAll();

		List<Lancamento> lancamentosContaBanco = listarLancamentos(lancamentos, contaBanco, dataInicial, dataFinal);
		List<Lancamento> lancamentosContaCredito = listarLancamentos(lancamentos, contaCredito, dataInicial, dataFinal);

		Map<String, Object> listafinal = new HashMap<>();
		listafinal.put("contaDebito", contaBanco);
		listafinal.put("contaCredito", contaCredito);
		listafinal.put("lancamentosContaBanco", lancamentosContaBanco);
		listafinal.put("lancamentosContaCredito", lancamentosContaCredito);

		return listafinal;

	}

	public List<Lancamento> listarLancamentos(List<Lancamento> lancamentos, Conta conta, Date dataInicial,
			Date dataFinal) {

		List<Lancamento> lancamentosConsolidados = new ArrayList<>();

		for (Lancamento lancamento : lancamentos) {
			Date dataLancamento = lancamento.getDate();
			if (lancamento.getConta().getId() == conta.getId()
					&& (dataLancamento.after(dataInicial) && dataLancamento.before(dataFinal))) {

				lancamentosConsolidados.add(lancamento);

			}

		}
		return lancamentosConsolidados;

	}

}
