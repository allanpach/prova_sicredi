package br.com.sicredi.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.com.sicredi.core.BaseTest;
import br.com.sicredi.pojo.SimulacaoRequestBody;
import br.com.sincredi.factory.SimulacaoDataFactory;

public class CriarSimulacao extends BaseTest {

	@Test
	public void criarNovaSimulacao() throws Exception {
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacao();
		
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(201)
			.body(containsString("id"))
			.body("nome", is(criarSimulacao.getNome()))
			.body("cpf", is(criarSimulacao.getCpf()))
			.body("email", is(criarSimulacao.getEmail()))
			.body("valor", is(criarSimulacao.getValor()))
			.body("parcelas", is(criarSimulacao.getParcelas()))
			.body("seguro", is(criarSimulacao.getSeguro()));
			
	}
	
	
	@Test
	public void criarNovaSimulacaoCpfExistente() throws Exception {
		//Uma simulação para um mesmo CPF retorna um HTTP Status 409 com a mensagem "CPF já existente"
		// este retornando 400
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoCpfComMascara();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(409)
			.body("mensagem", is("CPF duplicado"));
	}
	
	@Test
	public void criarNovaSimulacaoCpfComMascara() throws Exception{
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoCpfComMascara();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400);
		
	}
	
	@Test
	public void criarNovaSimulacaoCpfVazio() throws Exception{
		//Permite inserir cpf vazio. retorna o status code 400 e 200 mas grava no aqui pode dar um falso psitivo.
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoCpfVazioa();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400);
		
	}
	

	@Test
	public void criarNovaSimulacaoNomeVazio() throws Exception{
		//Permite inserir nome vazio.
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoNomeVazio();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400);
		
	}
	
	
	
	@Test
	public void criarNovaSimulacaoValorMenorQue1000() throws Exception{
//Permite inserir valor menor que 1000
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoValorMenorQue1000();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400)
			.body("erros.valor",is("Valor deve ser maior ou igual a R$ 1.000"));
		
	}
	
	@Test
	public void criarNovaSimulacaoValorMaiorQue40000() throws Exception{
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoValorMaiorQue40000();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400)
			.body("erros.valor",is("Valor deve ser menor ou igual a R$ 40.000"));
		
	}
	
	
	@Test
	public void criarNovaSimulacaoParcelarMenorQue2() throws Exception{

		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoParcelaMenorQue2();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400)
			.body("erros.parcelas",is("Parcelas deve ser igual ou maior que 2"));
		
	}
	
	@Test
	public void criarNovaSimulacaoParcelarMaiorQue48() throws Exception{
//Não está validando parcelo maior que 48

		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoParcelaMaiorQue48();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400)
			.body("erros.parcelas",is("Parcelas deve ser igual ou menor que 48"));
		
	}
		
	@Test
	public void criarNovaSimulacaoValidarFormatoEmail() throws Exception{
//Quando envia um email no campo email nao mostra msg de erro
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoEmailErrado();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
			.statusCode(400);
	}
	
	
	@Test
	public void criarNovaSimulacaoValidarPathSimulacao() throws Exception {
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacao();
		given()
			.body(criarSimulacao)
		.when()
			.post("/Simulacoes")
		.then()
			.statusCode(404)
			.body("error", is("Not Found"))
			.body("message", is("No message available"))
			.body("path", is("/api/v1/Simulacoes"));
}
	
	@Test
	public void validarAtributosObrigatorios() throws Exception {
			//deveria retornar 400 e  as msg
		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacaoValidarCampo();
		given()
			.body(criarSimulacao)
		.when()
			.post("/simulacoes")
		.then()
				.statusCode(400)
				.body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"))
				.body("erros.cpf", is("CPF não pode ser vazio"))
				.body("erros.nome", is("Nome não pode ser vazio"))
				.body("erros.email", is("E-mail não deve ser vazio"))
				.body("erros.seguro",is("Uma das opções de Seguro devem ser selecionadas"))
				.body("erros.valor", is("Valor não pode ser vazio"));
				
}
	
}
