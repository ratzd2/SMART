package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.ExemploPage;

public class PageObjectManager {

	private WebDriver driver;
	private ExemploPage exemplo;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}
	
	public ExemploPage getExemploPage() {
		return (exemplo == null) ? new ExemploPage(driver) : exemplo;
	}
}
