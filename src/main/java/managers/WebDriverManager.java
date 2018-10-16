package managers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import enums.DriverType;

public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static final String FIREFOX_DRIVER_PROPERTY = "webdriver.gecko.driver";
	private static final String PHANTOM_DRIVER_PROPERTY = "phantomjs.binary.path";

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
	}
 
	public WebDriver getDriver() {
		if(driver == null) driver = criarDriver();
		return driver;
	}
 
	public WebDriver criarDriver() {
        switch (driverType) {	    
        case FIREFOX :
        	System.setProperty(FIREFOX_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getCaminhoDriverFF());
        	driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getCaminhoDriverChrome());
        	driver = new ChromeDriver();
    		break;
        case PHANTOM : 
        	System.setProperty(PHANTOM_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getCaminhoDriverPhantom());
//        	driver = new PhantomJSDriver();
    		break;
        case INTERNETEXPLORER : driver = new InternetExplorerDriver();
    		break;
        }
 
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getWait(), TimeUnit.SECONDS);
		return driver;
	}	
 
	public void closeDriver() {
		driver.quit();
	}
}
