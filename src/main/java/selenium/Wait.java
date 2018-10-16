package selenium;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import managers.FileReaderManager;

public class Wait {
	private static Long timeout = FileReaderManager.getInstance().getConfigReader().getWait();

	public static void untilJqueryIsDone(WebDriver driver) {
		untilJqueryIsDone(driver, timeout);
	}

	public static void untilJqueryIsDone(WebDriver driver, Long timeoutInSeconds) {
		until(driver, new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver t) {
				Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) t).executeScript("return jQuery.active==0");
				if (!isJqueryCallDone)
					;
				return isJqueryCallDone;
			}
		}, timeoutInSeconds);
	}

	public static void untilPageLoadComplete(WebDriver driver) {
		untilPageLoadComplete(driver, timeout);
	}

	public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds) {
		until(driver, new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver t) {
				Boolean isPageLoaded = (Boolean) ((JavascriptExecutor) t).executeScript("return document.readyState")
						.equals("complete");
				if (!isPageLoaded)
					;
				return isPageLoaded;
			}
		}, timeoutInSeconds);
	}

	public static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition) {
		until(driver, waitCondition, timeout);
	}

	private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds) {

		WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
		webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);

		try {
			webDriverWait.until(waitCondition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
