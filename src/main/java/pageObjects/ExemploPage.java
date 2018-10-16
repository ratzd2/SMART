package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import managers.FileReaderManager;
import selenium.Wait;
import testDataTypes.ExemploTestDataJson;

public class ExemploPage{
	
	private WebDriver driver;
	private WebDriverWait espera;
	
	public ExemploPage(WebDriver driver) {
		this.driver = driver;
		espera = new WebDriverWait(this.driver, FileReaderManager.getInstance().getConfigReader().getWait());
	    PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.ID, using = "lst-ib") 
	private WebElement txt_Pesquisa;
	
	@FindBy(how = How.XPATH, using = "/html/body/div/div[3]/form/div[2]/div[3]/center/input[1]") 
	private WebElement btn_Pesquisar;

	@FindBy(how = How.XPATH, using = "/html/body/div[5]/div[3]/div[1]/div[5]/div/div/div[1]/div/div/div/div[1]/div/div[4]") 
	private WebElement abaSelecionada;
	
	public void acessarPagina() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getUrl());
	}
	
	public String abaSelecionada() {
		espera.until(ExpectedConditions.elementToBeClickable(abaSelecionada));
		espera.until(ExpectedConditions.visibilityOf(abaSelecionada));
		return abaSelecionada.getText();
	}
	
	public void preencher_Pesquisa(String pesquisa) {
		txt_Pesquisa.clear();
		txt_Pesquisa.sendKeys(pesquisa);
	}
	
	public void clicar_Pesquisar() {
		btn_Pesquisar.click();
	}
	
	public void aguardaCarregamento() {
		Wait.untilPageLoadComplete(driver);
	}

	public void select_TipoPesquisa(String tipoPesquisa) {
		Wait.untilPageLoadComplete(driver);
		driver.findElement(By.linkText(tipoPesquisa)).click();
	}

	public void preencherDadosVindosJson(ExemploTestDataJson dataType) {
		preencher_Pesquisa(dataType.pesquisa);
		select_TipoPesquisa(dataType.tipoPesquisa.tipo);
	}

}
