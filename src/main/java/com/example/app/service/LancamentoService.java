package com.example.app.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.app.dto.ContaDTO;
import com.example.app.dto.DashboardDTO;
import com.example.app.dto.LancamentoDTO;
import com.example.app.model.Conta;
import com.example.app.model.Conta.TipoConta;
import com.example.app.model.Lancamento;
import com.example.app.model.PlanoConta;
import com.example.app.model.PlanoConta.TipoMovimento;
import com.example.app.model.Usuario;
import com.example.app.repository.ContaRepository;
import com.example.app.repository.LancamentoRepository;
import com.example.app.repository.PlanoContaRepository;
import com.example.app.repository.UsuarioRepository;
import com.example.app.utils.Formatador;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private PlanoContaRepository planoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	private ContaService contaService;

	@Autowired
	private PlanoContaService planoContaService;

	@Autowired
	UsuarioService usuarioService;

	@Transactional
	public void cadastrarLancamento(Lancamento lancamento) throws Exception {

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

		if (planoConta.getTipo().equals(TipoMovimento.R)) {
			contaService.creditar(conta, valor);
		} else if (planoConta.getTipo().equals(TipoMovimento.D)) {
			contaService.debitar(conta, valor);
		} else if (planoConta.getTipo().equals(TipoMovimento.TC)) {
			transferirEntreContas(usuario, l, conta, valor);
		} else if (planoConta.getTipo().equals(TipoMovimento.TU)) {
			transferirEntreUsuarios(lancamento, l, conta, valor);
		}

		lancamentoRepository.save(l);
	}

	public void transferirEntreContas(Usuario usuario, Lancamento l, Conta conta, Double valor) throws CloneNotSupportedException {
		//buscando a conta crédito do usuário para ser utilizada como conta destino 
		List<Conta> contas = contaRepository.findByUsuarioId(usuario.getId());
		l.setContaDestino(contas.get(1));
		Conta contaDestino = contas.get(1);
		//realinhando o pointer do plano da conta origem
		PlanoConta novoplanoOrigem = new PlanoConta();
		List<PlanoConta> planosOrigem = planoRepository.findByUsuarioId(contaDestino.getUsuario().getId());
		novoplanoOrigem = planosOrigem.get(1); //Pegando plano de Receita do usuario destino			    
		l.setPlano(novoplanoOrigem);
		//criando o lancamento para a conta destino
		Lancamento lancContaDestino = (Lancamento) l.clone();
		lancContaDestino.setConta(contaDestino);
		lancContaDestino.setDescricao("Recebido da conta corrente");
		lancContaDestino.setPlano(planosOrigem.get(0));

		lancamentoRepository.save(lancContaDestino);

		contaService.transferir(conta, valor, contaDestino);
	}

	public void transferirEntreUsuarios(Lancamento lancamento, Lancamento l, Conta conta, Double valor) throws CloneNotSupportedException {
		Optional <Conta> contaOpp = contaRepository.findById(lancamento.getContaDestino().getId());
		Conta contaDest = contaOpp.get();

		//clonando o lançamento para constar também na lista de lancamentos da outra conta
		Lancamento lancContaDestino = (Lancamento) l.clone();
		lancContaDestino.setConta(contaDest);
		PlanoConta novoplanoDestino = new PlanoConta();
		List<PlanoConta> planosDestino = planoRepository.findByUsuarioId(contaDest.getUsuario().getId());
		novoplanoDestino = planosDestino.get(0); //Pegando plano de Receita do usuario destino			    
		lancContaDestino.setPlano(novoplanoDestino);
		lancContaDestino.setDescricao("Recebido de: " + l.getConta().getUsuario().getNome());

		lancamentoRepository.save(lancContaDestino);

		//Lançar origem:
		PlanoConta novoplanoOrigin = new PlanoConta();
		List<PlanoConta> planosOrigin = planoRepository.findByUsuarioId(conta.getUsuario().getId());
		novoplanoOrigin = planosOrigin.get(1); //Pegando plano de Despesa do usuario
		l.setPlano(novoplanoOrigin);

		contaService.transferir(conta, valor, contaDest);
	}


	public DashboardDTO listarLancamentosPorData(String dataI, String dataF, String login) throws ParseException {
			
		Optional<Usuario> opp = usuarioRepository.findByLogin(login);
		Usuario usuario = opp.get();
		List<Conta> contas = contaRepository.findByUsuarioId(usuario.getId());
		Conta contaBanco = contas.get(0);
		Conta contaCredito = contas.get(1);

		Date dataInicial = Formatador.stringParaDate(dataI);
		Date dataFinal = Formatador.stringParaDate(dataF);

		List<Lancamento> lancamentos = lancamentoRepository.findAll();	

		Double totCredBanco = 0.0;
		Double totDebBanco = 0.0;
		Double totCredCredito = 0.0;
		Double totDebCredito = 0.0;

		for (Lancamento lancamento : lancamentos) {
			if (usuario.equals(lancamento.getPlano().getUsuario())) {
				if(lancamento.getConta().getTipo().equals(TipoConta.BANCO) && lancamento.getPlano().getTipo().equals(TipoMovimento.R)) {
					totCredBanco += lancamento.getValor();
				}
				if(lancamento.getConta().getTipo().equals(TipoConta.BANCO) && lancamento.getPlano().getTipo().equals(TipoMovimento.D)) {
					totDebBanco += lancamento.getValor();
				}
				if(lancamento.getConta().getTipo().equals(TipoConta.CREDITO) && lancamento.getPlano().getTipo().equals(TipoMovimento.R)) {
					totCredCredito += lancamento.getValor();
				}
				if(lancamento.getConta().getTipo().equals(TipoConta.CREDITO) && lancamento.getPlano().getTipo().equals(TipoMovimento.D)) {
					totDebCredito += lancamento.getValor();
				}
			}
		}

		List<LancamentoDTO> lancamentosContaBanco = listarLancamentos(lancamentos, contaBanco, dataInicial, dataFinal);
		List<LancamentoDTO> lancamentosContaCredito = listarLancamentos(lancamentos, contaCredito, dataInicial, dataFinal);
		
		ContaDTO contaCredDTO = new ContaDTO();
		contaCredDTO.setId(contaCredito.getId());
		contaCredDTO.setNumero(contaCredito.getNumero());
		contaCredDTO.setSaldo(contaCredito.getSaldo());
		contaCredDTO.setTipo(contaCredito.getTipo());
		contaCredDTO.setTotalCreditos(totCredCredito);
		contaCredDTO.setTotalDebitos(totDebCredito);
		contaCredDTO.setLancamentos(lancamentosContaCredito);
		
		ContaDTO contaDebDTO = new ContaDTO();
		contaDebDTO.setId(contaBanco.getId());
		contaDebDTO.setNumero(contaBanco.getNumero());
		contaDebDTO.setSaldo(contaBanco.getSaldo());
		contaDebDTO.setTipo(contaBanco.getTipo());
		contaDebDTO.setTotalCreditos(totCredBanco);
		contaDebDTO.setTotalDebitos(totDebBanco);
		contaDebDTO.setLancamentos(lancamentosContaBanco);
		
		DashboardDTO dashboard = new DashboardDTO();
		dashboard.setContaDebito(contaDebDTO);
		dashboard.setContaCredito(contaCredDTO);
		
		
		System.out.println(dashboard);
		
		return dashboard;
		
	}

	public List<LancamentoDTO> listarLancamentos(List<Lancamento> lancamentos, Conta conta, Date dataInicial, Date dataFinal){ 

		List<LancamentoDTO> lancamentosDTO= new ArrayList<LancamentoDTO>();

		for (Lancamento lancamento : lancamentos) {
			Date dataLancamento = lancamento.getDate();
			if (lancamento.getConta().getId() == conta.getId()
					&& (dataLancamento.after(dataInicial) && dataLancamento.before(dataFinal))) {

				LancamentoDTO lancamentoDTO = new LancamentoDTO(); 		
				lancamentoDTO.setId(lancamento.getId());
				lancamentoDTO.setData(Formatador.formatarData(lancamento.getDate()));
				lancamentoDTO.setValor(lancamento.getValor());
				lancamentoDTO.setConta(lancamento.getConta().getId());
				lancamentoDTO.setDescrição(lancamento.getDescricao());
				lancamentoDTO.setPlano(lancamento.getPlano().getId());
				lancamentoDTO.setTipo(lancamento.getPlano().getTipo());

				lancamentosDTO.add(lancamentoDTO);

			}
		}
		return lancamentosDTO;
	}

}
