package core;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	protected BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(28));
	}
	
	public void goToUrl(String URL) {
		driver.get(URL);
	}
	
	protected WebElement find(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	protected WebElement visible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected WebElement clickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	protected void type(By locator, String texto) {
		WebElement element = visible(locator);
		element.clear();
		element.sendKeys(texto);
	}
	
	protected void click(By locator) {
		WebElement element = clickable(locator);
		element.click();
	}
	
	protected String getText(By locator) {
		WebElement element = visible(locator);
		return element.getText();
	}
	
	
}
