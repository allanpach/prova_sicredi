package br.com.sicredi.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import br.com.sicredi.core.BaseTest;

public class Restricoes extends BaseTest {
//Ao colocar um CPF inválido retorna Status code 204 exemplo: "abc.abc.abc-aa"
// Ao colocar um CPF com retrição e com máscara ele não é valido e retorna o Status code 204
	
	String cpfComResticao = "60094146012";
	String cpfComResticaoMascara = "600.941.460-12";
	String cpfInvalido = "abc.abc.abc.aa";
	String cpfSemResticao = "43655481071";
	String cpfVazio = "";

	@Test
	public void consultarCPFSemRestricoes(){
		
		given()
		.when()
			.get("/restricoes/"+cpfSemResticao)
		.then()
			.statusCode(204);
		}
	
	
	@Test
	public void consultarCPFComRestricoes() {

		given()
		.when()
			.get("restricoes/" + cpfComResticao)
		.then()
			.statusCode(200)
			.body("mensagem", is("O CPF " + cpfComResticao + " tem problema"));
		}
	
	@Test
	public void consultarCPFVazio() {

		given()
		.when()
			.get("restricoes/"+cpfVazio)
		.then()
			.statusCode(404)
			.body("error", is("Not Found"))
			.body("message", is("No message available"))
			.body("path", is("/api/v1/restricoes/"));
			}
	
	@Test
	public void consultarCpfInvalido() {

		given()
		.when()
			.get("restricoes/"+cpfInvalido)
		.then()
			.statusCode(204);
					
	}
	
	@Test
	public void consultarCpfComResticaoMascaraTes() {

		given()
		.when()
			.get("restricoes/"+cpfComResticaoMascara)
		.then()
			.statusCode(200)
			.body("mensagem", is("O CPF " +cpfComResticaoMascara + " tem problema"));
		
	}
	
	
	@Test
	public void validarPathConsultarRestricoes() {
			
			given()
		    .when()
		    	.get("Restricoes/"+cpfSemResticao+"")
			.then()
				.statusCode(404)
				.body("error", is("Not Found"))
				.body("message", is("No message available"))
				.body("path", is("/api/v1/Restricoes/"+cpfSemResticao+""));
}
	
	}
	

