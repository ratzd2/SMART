package stepDefinitions;

import static org.junit.Assert.*;
import cucumber.TestContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.FileReaderManager;
import pageObjects.ExemploPage;
import testDataTypes.ExemploTestDataJson;

public class GoogleSteps {
	private TestContext testContext;
	private ExemploPage exemplo;
	private ExemploTestDataJson dadosJson;

	public GoogleSteps(TestContext context) {
		testContext = context;
		exemplo = testContext.getPageObjectManager().getExemploPage();
	}

	@Given("^eu acesse a pagina do google$")
	public void eu_acesse_a_pagina_do_google() throws Exception {
		exemplo.acessarPagina();
	}

	@When("^eu preencher a \"([^\"]*)\"$")
	public void eu_preencher_a(String pesquisa) throws Exception {
		dadosJson = FileReaderManager.getInstance().getJsonReader().getPesquisa(pesquisa);
		// exemplo.preencherDadosVindosJson(dadosJson);
		exemplo.preencher_Pesquisa(dadosJson.pesquisa);
	}

	@When("^clicar no botao pesquisar$")
	public void clicar_no_botao_pesquisar() throws Exception {
		exemplo.clicar_Pesquisar();
	}

	@When("^quando as consultas forem apresentadas$")
	public void quando_as_consultas_serem_apresentadas() throws Exception {
		exemplo.aguardaCarregamento();
	}

	@When("^eu selecionar o \"([^\"]*)\"$")
	public void eu_selecionar_o(String tipoPesquisa) throws Exception {
		// exemplo.select_TipoPesquisa(tipoPesquisa);
		exemplo.select_TipoPesquisa(dadosJson.tipoPesquisa.tipo);
	}

	@Then("^valido que apenas foram apresentadas as noticias$")
	public void valido_que_apenas_foram_apresentadas_as_noticias() throws Exception {
		exemplo.aguardaCarregamento();
		assertTrue(exemplo.abaSelecionada().equals(dadosJson.tipoPesquisa.tipo));
	}
}
