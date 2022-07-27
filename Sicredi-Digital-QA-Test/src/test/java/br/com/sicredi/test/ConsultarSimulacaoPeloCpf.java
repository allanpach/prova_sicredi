package br.com.sicredi.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.com.sicredi.core.BaseTest;
import br.com.sicredi.pojo.SimulacaoRequestBody;
import br.com.sincredi.factory.SimulacaoDataFactory;

public class ConsultarSimulacaoPeloCpf extends BaseTest {

	

	@Test
	public void consultarSimulacaoCpfCadastrado() throws Exception {
		
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacao();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then();
		given()
		.when()
			.get("/simulacoes/"+criarSimulacao.getCpf()+"")
		.then()
			.statusCode(200)
			.body(containsString("id"))
			.body(containsString("nome"))
			.body(containsString("cpf"))
			.body(containsString("email"))
			.body(containsString("valor"))
			.body(containsString("parcelas"))
			.body(containsString("seguro"));
		
	}
	
	@Test
	public void consultarSimulacaoCpfnaoCadastrado() throws Exception {
		
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacao();
		given()
		.when()
			.get("/simulacoes/"+criarSimulacao.getCpf()+"")
		.then()
			.statusCode(404)
			.body("mensagem",is("CPF "+criarSimulacao.getCpf()+" não encontrado"));

	}
	
	@Test
	public void consultarSimulacaoValidarPath() throws Exception {
		
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacao();
		given()
		.when()
			.get("/Simulacoes/"+criarSimulacao.getCpf()+"")
		.then()
			.statusCode(404)
			.body("error", is("Not Found"))
			.body("message", is("No message available"))
			.body("path", is("/api/v1/Simulacoes/"+criarSimulacao.getCpf()+""));
	}

}
