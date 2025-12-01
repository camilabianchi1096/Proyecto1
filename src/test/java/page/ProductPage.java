package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.BasePage;

public class ProductPage extends BasePage {
	private final By titleProductXpath = By.xpath("(//span[@class='title'])[1]");
	private final By product1Xpath = By.xpath("//div[normalize-space()='Sauce Labs Backpack']");
	private final By product2Xpath = By.xpath("//div[normalize-space()='Sauce Labs Bike Light']");
	private final By product3Xpath = By.xpath("//div[normalize-space()='Sauce Labs Bolt T-Shirt']");
	private final By product4Xpath = By.xpath("//div[normalize-space()='Sauce Labs Fleece Jacket']");
	private final By product5Xpath = By.xpath("//div[normalize-space()='Sauce Labs Onesie']");
	private final By product6Xpath = By.xpath("//div[normalize-space()='Test.allTheThings() T-Shirt (Red)']");
	
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}
	
	public String leerTitulo() {
		return find(titleProductXpath).getText();
	}
	
	public String obtenerNombreProducto1() {
		return find(product1Xpath).getText();
	}
	
	public String obtenerNombreProducto2() {
		return find(product2Xpath).getText();
	}
	
	public String obtenerNombreProducto3() {
		return find(product3Xpath).getText();
	}
	
	public String obtenerNombreProducto4() {
		return find(product4Xpath).getText();
	}
	
	public String obtenerNombreProducto5() {
		return find(product5Xpath).getText();
	}
	
	public String obtenerNombreProducto6() {
		return find(product6Xpath).getText();
	}
	
}