package br.com.sicredi.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;
import org.junit.Test;

import br.com.sicredi.core.BaseTest;
import io.restassured.response.Response;

public class ConsultarTodasSimulacoesCadastradas extends BaseTest {
		
	
	@Test
	public void consultarTodasAsSimulacoes() {
//Caso não tenha simulações está retornando Status Code 200 no lugar do Status Code 204
		Response resposta = (Response) 
		given()
		.when()
		   	.get("/simulacoes");

		if (resposta.statusCode() == 204) {
			resposta.then().statusCode(204)
				.body(Matchers.hasSize(0));
												
		}

		if (resposta.statusCode() == 200) {
			resposta.then().statusCode(200)
				.body(containsString("id"))
				.body(containsString("nome"))
				.body(containsString("cpf"))
				.body(containsString("email"))
				.body(containsString("valor"))
				.body(containsString("parcelas"))
				.body(containsString("seguro"));
			
		}

}
	
	@Test
	public void validarPathConsultarTodasAsSimulacoes() {
			
			given()
		    .when()
		     	.get("/Simulacoes")
			.then()
				.statusCode(404)
				.body("error", is("Not Found"))
				.body("message", is("No message available"))
				.body("path", is("/api/v1/Simulacoes"));
}
	
	}
