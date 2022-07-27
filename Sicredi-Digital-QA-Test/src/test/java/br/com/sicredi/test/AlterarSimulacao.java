 package br.com.sicredi.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import br.com.sicredi.core.BaseTest;
import br.com.sicredi.pojo.SimulacaoRequestBody;
import br.com.sincredi.factory.SimulacaoDataFactory;

public class AlterarSimulacao extends BaseTest {

	
	@Test
	public void alterarSimulacaoExistentePeloCpf() throws Exception {
	//Não está atualizando o valor e ao alterar se nao passar o id apresenta erro 500
	
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacao();
		
		given()
			.body(alterarSimulacao)
		.when()
			.put("/simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(200)
			.body(containsString("id"))
			.body("nome", is(alterarSimulacao.getNome()))
			.body("cpf", is(alterarSimulacao.getCpf()))
			.body("email", is(alterarSimulacao.getEmail()))
			.body("valor", is(alterarSimulacao.getValor()))
			.body("parcelas", is(alterarSimulacao.getParcelas()))
			.body("seguro", is(alterarSimulacao.getSeguro()));
			
		
	;}
	
	@Test
	public void alterarSimulacaoInexistentePeloCpf() throws Exception {

		SimulacaoRequestBody criarSimulacao = SimulacaoDataFactory.criarNovaSimulacao();
		given()
			.body(criarSimulacao)
		.when()
			.put("/simulacoes/"+criarSimulacao.getCpf()+"")
		.then()
			.statusCode(40)
		.body("mensagem", is("CPF "+criarSimulacao.getCpf()+" não encontrado"));
		
		}
	
	
	@Test
	public void alterarSimulacaoCampoCpfVazio() throws Exception{
//Permite inserir cpf vazio. retorna o status code 405 mas grava no aqui pode dar um falso psitivo.
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoCpfVazio();
		given()
			.body(alterarSimulacao)
		.when()
			.put("/simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(400);
	}
	
	@Test
	public void alterarSimulacaoCampoNomefVazio() throws Exception{
//Permite inserir cpf vazio. retorna o status code 405 mas grava no aqui pode dar um falso psitivo.
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoCpfVazio();
		given()
			.body(alterarSimulacao)
		.when()
			.put("/simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(400);
	}
		
	@Test
	public void alterarSimulacaoValorMenorQue1000() throws Exception{
//Permite inserir valor menor que 1000
		
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoValorMenorQue1000();
		given()
			.body(alterarSimulacao)
		.when()
			.put("/simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(400)
			.body("erros.valor",is("Valor deve ser maior ou igual a R$ 1.000"));
		
	}
	
	@Test
	public void alterarSimulacaoValorMaiorQue40000() throws Exception{
		
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoValorMaiorQue40000();
		given()
			.body(alterarSimulacao)
		.when()
			.put("/simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(400)
			.body("erros.valor",is("Valor deve ser menor ou igual a R$ 40.000"));
		
	}
	
	@Test
	public void alterarSimulacaoParcelarMenorQue2() throws Exception{

			
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoParcelaMenorQue2();
		given()
			.body(alterarSimulacao)
		.when()
			.put("/simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(400)
			.body("erros.parcelas",is("Parcelas deve ser igual ou maior que 2"));
		
	}
	
	@Test
	public void alterarSimulacaoParcelarMaiorQue48() throws Exception{
//Não está validando parcelo maior que 48
		
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoParcelaMaiorQue48();
		given()
			.body(alterarSimulacao)
		.when()
			.put("/simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(400)
			.body("erros.parcelas",is("Parcelas deve ser igual ou menor que 48"));
		
	}
	
	@Test
	public void alterarSimulacaoValidarPath() throws Exception {
			
		//Não está atualizando o valor
		SimulacaoRequestBody alterarSimulacao = SimulacaoDataFactory.alterarSimulacaoParcelaMaiorQue48();
		given()
			.body(alterarSimulacao)
		.when()
			.put("/Simulacoes/"+alterarSimulacao.getCpf()+"")
		.then()
			.statusCode(404)
			.body("error", is("Not Found"))
			.body("message", is("No message available"))
			.body("path", is("/api/v1/Simulacoes/"+alterarSimulacao.getCpf()+""));
		
	}
	

}
