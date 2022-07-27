package br.com.sicredi.suit;



import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import br.com.sicredi.test.*;


@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	CriarSimulacao.class,
	Restricoes.class,
	AlterarSimulacao.class,
	ConsultarTodasSimulacoesCadastradas.class,
	ConsultarSimulacaoPeloCpf.class,
	RemoverUmaSimulacao.class,
	
})
public class SuitTest {


}
