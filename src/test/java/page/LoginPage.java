package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.BasePage;

public class LoginPage extends BasePage {
	private final By usuarioID = By.id("user-name");
	private final By contrasenaID = By.id("password");
	private final By btnLoginID = By.id("login-button");
	private final By errorPassXpath = By.xpath("(//h3[normalize-space()='Epic sadface: Password is required'])[1]");
	private final By errorUserXpath = By.xpath("//h3[normalize-space()='Epic sadface: Username is required']");
	private final By errorInvalidoXpath = By.xpath("//h3[contains(text(),'Epic sadface: Username and password do not match a')]");
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void escribirUsuario(String texto) {
		type(usuarioID, texto);	
	}
	
	public void escribirContrasena(String texto) {
		type(contrasenaID, texto);	
	}
	
	public void hacerClickLogin() {
		click(btnLoginID);
	}
	
	
	public String leerErrorContrasena() {
		return visible(errorPassXpath).getText();
	}
	
	
	public String leerErrorUsuario() {
		return visible(errorUserXpath).getText();
	}
	
	public String leerErrorInvalido() {
		return visible(errorInvalidoXpath).getText();
	}
	
	public void loginCompleto(String usuario, String contrasena) {
		type(usuarioID, usuario);	
		type(contrasenaID, contrasena);	
		hacerClickLogin();
		}
	
}