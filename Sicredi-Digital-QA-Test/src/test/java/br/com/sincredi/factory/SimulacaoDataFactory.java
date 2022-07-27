package br.com.sincredi.factory;

import java.io.FileInputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sicredi.pojo.SimulacaoRequestBody;
import br.com.sicredi.utils.DataUtils;

public class SimulacaoDataFactory {

	public static SimulacaoRequestBody criarNovaSimulacao() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SimulacaoRequestBody simulacao = objectMapper.readValue(
				new FileInputStream("src\\test\\resources\\requestBody\\postCriarSimulacao.json"),
				SimulacaoRequestBody.class);
		simulacao.setCpf(DataUtils.geradorCPF());
		return simulacao;

	}

	public static SimulacaoRequestBody criarSimulacaoCpfExistente() throws Exception {
		SimulacaoRequestBody simulacaoCpfExistente = criarNovaSimulacao();
		simulacaoCpfExistente.setCpf(DataUtils.geradorCPF());
		return simulacaoCpfExistente;
	}

	public static SimulacaoRequestBody criarNovaSimulacaoCpfComMascara() throws Exception {
		SimulacaoRequestBody simulacaoCpfComMascara = criarNovaSimulacao();
		simulacaoCpfComMascara.setCpf(DataUtils.geradorCPFMascara());
		return simulacaoCpfComMascara;

	}

	public static SimulacaoRequestBody criarNovaSimulacaoCpfVazioa() throws Exception {
		SimulacaoRequestBody simulacaoCpfVazio = criarNovaSimulacao();
		simulacaoCpfVazio.setCpf("");
		return simulacaoCpfVazio;
		
	}

	public static SimulacaoRequestBody criarNovaSimulacaoNomeVazio() throws Exception {
		SimulacaoRequestBody simulacaoNomeVazio = criarNovaSimulacao();
		simulacaoNomeVazio.setNome("");
		return simulacaoNomeVazio;
	}

	public static SimulacaoRequestBody criarNovaSimulacaoValorMenorQue1000() throws Exception {
		SimulacaoRequestBody simulacaoValorMenorQue1000 = criarNovaSimulacao();
		simulacaoValorMenorQue1000.setValor(999);
		return simulacaoValorMenorQue1000;
		
	}

	public static SimulacaoRequestBody criarNovaSimulacaoValorMaiorQue40000() throws Exception {
		SimulacaoRequestBody simulacaoValorMaiorQue4000 = criarNovaSimulacao();
		simulacaoValorMaiorQue4000.setValor(40001);;
		return simulacaoValorMaiorQue4000;
	}

	public static SimulacaoRequestBody criarNovaSimulacaoParcelaMenorQue2() throws Exception {
		SimulacaoRequestBody simulacaoValorParcelaMenorQue1 = criarNovaSimulacao();
		simulacaoValorParcelaMenorQue1.setParcelas(1);
		return simulacaoValorParcelaMenorQue1;
	}

	public static SimulacaoRequestBody criarNovaSimulacaoParcelaMaiorQue48() throws Exception {
		SimulacaoRequestBody simulacaoValorParcelaMaiorQue48 = criarNovaSimulacao();
		simulacaoValorParcelaMaiorQue48.setParcelas(48);
		return simulacaoValorParcelaMaiorQue48;
	}

	public static SimulacaoRequestBody criarNovaSimulacaoEmailErrado() throws Exception {
		SimulacaoRequestBody simulacaoEmailErrado = criarNovaSimulacao();
		simulacaoEmailErrado.setEmail("@fulano.com");
		return simulacaoEmailErrado;

	}

	public static SimulacaoRequestBody criarNovaSimulacaoValidarCampo() throws Exception {
		SimulacaoRequestBody criarSimulacao = new SimulacaoRequestBody();
		return criarSimulacao;
	}

	public static SimulacaoRequestBody removerSimulacaoIdInvalido() throws Exception {
		SimulacaoRequestBody criarSimulacao = new SimulacaoRequestBody();
		criarSimulacao.setId(System.nanoTime());
		return criarSimulacao;
	}
	
	public static SimulacaoRequestBody alterarSimulacao() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		SimulacaoRequestBody simulacaoAlt = objectMapper.readValue(
				new FileInputStream("src\\test\\resources\\requestBody\\postAlterarSimulacao.json"),
		SimulacaoRequestBody.class);
		return simulacaoAlt;

	}
	
	public static SimulacaoRequestBody alterarSimulacaoCpfVazio() throws Exception {
		SimulacaoRequestBody simulacaoCpfVazio = alterarSimulacao();
		simulacaoCpfVazio.setCpf("");
		return simulacaoCpfVazio;
		
	}
	public static SimulacaoRequestBody alterarSimulacaoNomeVazio() throws Exception {
		SimulacaoRequestBody simulacaoNomeVazio = alterarSimulacao();
		simulacaoNomeVazio.setNome("");
		return simulacaoNomeVazio;
	
	
	}
	
	public static SimulacaoRequestBody alterarSimulacaoValorMenorQue1000() throws Exception {
		SimulacaoRequestBody valorMenorQue1000 = alterarSimulacao();
		valorMenorQue1000.setValor(999);;
		return valorMenorQue1000;
	
	
	}
	public static SimulacaoRequestBody alterarSimulacaoValorMaiorQue40000() throws Exception {
		SimulacaoRequestBody valorMaiorQue40000 = alterarSimulacao();
		valorMaiorQue40000.setValor(400001);
		return valorMaiorQue40000;
	
	
	}
	public static SimulacaoRequestBody alterarSimulacaoParcelaMenorQue2() throws Exception {
		SimulacaoRequestBody simulacaoValorParcelaMenorQue1 = alterarSimulacao();
		simulacaoValorParcelaMenorQue1.setParcelas(1);
		return simulacaoValorParcelaMenorQue1;
	}

	public static SimulacaoRequestBody alterarSimulacaoParcelaMaiorQue48() throws Exception {
		SimulacaoRequestBody simulacaoValorParcelaMaiorQue48 = alterarSimulacao();
		simulacaoValorParcelaMaiorQue48.setParcelas(48);
		return simulacaoValorParcelaMaiorQue48;
	}
	
}
