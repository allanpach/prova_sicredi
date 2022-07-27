package br.com.sicredi.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.com.sicredi.core.BaseTest;
import br.com.sicredi.pojo.SimulacaoRequestBody;
import br.com.sincredi.factory.SimulacaoDataFactory;

public class RemoverUmaSimulacao extends BaseTest {

	//Ao informar um ID que não existe está retronando status code 200  no lugar do status code 404.

	@Test
	public void removerSimulacaoPorIdValido() throws Exception {
		
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacao();
		Integer idResp=	
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
				.extract().path("id");
		 given()
		.when()
			.delete("/simulacoes/"+idResp+"")
		.then()
			.statusCode(200)
			.body(containsString("OK"));

	}
	
	@Test
	public void removerSimulacaoPorIdInvalido() throws Exception {
// está retornando 200 e deveria se 404
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.removerSimulacaoIdInvalido();
		given()
		.when()
			.delete("/simulacoes/"+criarSimulacao.getId()+"")
		.then()
			.statusCode(404);
	}
	
	@Test
	public void validarPathRemoverSimulacaoPorId() throws Exception {
		
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.removerSimulacaoIdInvalido();
		given()
		.when()
			.delete("/Simulacoes/"+criarSimulacao.getId()+"")
		.then()
			.statusCode(404)
			.body("error", is("Not Found"))
			.body("message", is("No message available"))
			.body("path", is("/api/v1/Simulacoes/"+criarSimulacao.getId()+""));
	}

}
