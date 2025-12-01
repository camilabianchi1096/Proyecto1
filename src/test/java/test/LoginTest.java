package test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.ProductPage;
import utils.data.LoginDataReader;
import utils.model.LoginData;
import utils.reporte.ExtentListener;
import utils.reporte.Report;

@Listeners(ExtentListener.class)
public class LoginTest {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    ProductPage productPage;
 
    
    @Parameters("navegador")
    
    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider(Method method) throws IOException {
    	String testName = method.getName();
    	LoginData[] allData = LoginDataReader.readLoginData();
    	
    	List<Object[]> result = new ArrayList<>();
    	for (LoginData data : allData) {
    		if (data.getIdCaso() == null) 
    			continue;
    		
    		if(data.getIdCaso().equalsIgnoreCase(testName)) {
    			int rep = data.getRepeticiones() > 0 ? data.getRepeticiones() : 1;
    			for (int i = 0; i < rep; i++) {
    				result.add(new Object[] {data});
    			}
    		}
    	}
    	
    	return result.toArray(new Object[0][0]);
    }

    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("edge") String navegador) {
    	switch (navegador.toLowerCase()) {
    		case "chrome":
    			ChromeOptions options = new ChromeOptions();
    			options.addArguments("start-maximized");
    			options.addArguments("--headless");
    			options.addArguments("--disable-extensions");
    			options.addArguments("--disable-notifications");
    			options.setAcceptInsecureCerts(true);
    			driver = new ChromeDriver(options);
    			break;
    		case "edge":
			default:
    			EdgeOptions optionsE = new EdgeOptions();
    			optionsE.addArguments("start-maximized");
    			optionsE.addArguments("--headless");
    			optionsE.addArguments("--disable-extensions");
    			optionsE.addArguments("--disable-notifications");
    			optionsE.setAcceptInsecureCerts(true);
    			driver = new EdgeDriver(optionsE);
    	}
        wait = new WebDriverWait(driver, Duration.ofSeconds(28)); // este es el tiempo máximo que va a esperar 
        System.out.println("Se ingresó a la página");
        
    	loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);   
        
        loginPage.goToUrl("https://www.saucedemo.com/");
   
    }
    
    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.quit();
    }
    
    @Test(dataProvider = "loginData", groups = {"LOGIN", "REGRESION", "EXITO", "SMOKE"})
    public void login_Exitoso(LoginData data) {
    	Report.info("COMIENZA TEST LOGIN EXITOSO: " + data.getDescripcion() + "| USUARIO: "+ data.getUsername());
    	loginPage.escribirUsuario(data.getUsername());
        loginPage.escribirContrasena(data.getPassword());
        loginPage.hacerClickLogin();
    	Report.info("SE COMPLETAN DATOS DE LOGIN Y HACE CLICK EN BOTÓN LOGIN");
        
        Assert.assertEquals(productPage.leerTitulo(), "Products");
        System.out.println("Se válida que se ingresa correctamtente a la página de: " + productPage.leerTitulo());
        
        Report.pass("INGRESÓ CORRECTAMENTE A LA PANTALLA DE INVENTARIO");
        Report.screenshot(driver, "login-exitoso");

    }
    
    @Test(dataProvider = "loginData",groups = {"LOGIN", "REGRESION",  "FALLA"})
    public void login_Fallido_Usuario(LoginData data) {
    	
    	Report.info("COMIENZA TEST LOGIN FALLIDO POR CONTRASEÑA"+ data.getDescripcion() + "| USUARIO: "+ data.getUsername());
    	loginPage.escribirUsuario(data.getUsername());
        loginPage.hacerClickLogin();
    	Report.info("SE COMPLETAN DATOS DE LOGIN Y HACE CLICK EN BOTÓN LOGIN");
               
        System.out.println("Se valida que la contraseña es obligatoria. Aparece el siguiente mensaje " + loginPage.leerErrorContrasena());
                Assert.assertEquals(loginPage.leerErrorContrasena(), "Epic sadface: Password is required", "El mensaje es diferente al esperado");
                
                Report.pass("SE VALIDA MENSAJE DE CONTRASEÑA OBLIGATORIA");
                Report.screenshot(driver, "login-contraseña-obligatoria");

    }
    
    @Test(dataProvider = "loginData",groups = {"LOGIN", "REGRESION",  "FALLA"})
    public void login_Fallido_Contrasena(LoginData data) {
    	
    	Report.info("COMIENZA TEST LOGIN FALLIDO POR USUARIO"+ data.getDescripcion() + "| USUARIO: "+ data.getUsername());  	
    	loginPage.escribirContrasena(data.getPassword());
        loginPage.hacerClickLogin();
    	Report.info("SE COMPLETAN DATOS DE LOGIN Y HACE CLICK EN BOTÓN LOGIN");
               
        System.out.println("Se valida que la contraseña es obligatoria. Aparece el siguiente mensaje " + loginPage.leerErrorUsuario());
                Assert.assertEquals(loginPage.leerErrorUsuario(), "Epic sadface: Username is required", "El mensaje es diferente al esperado");

                Report.pass("SE VALIDA MENSAJE DE USUARIO OBLIGATORIO");
                Report.screenshot(driver, "login-usuario-obligatorio");

    }

    @Test(dataProvider = "loginData", groups = {"LOGIN", "REGRESION",  "FALLA"})
    public void login_Fallido_Invalido(LoginData data) {
    	Report.info("COMIENZA TEST LOGIN FALLIDO POR USUARIO INVALIDO"+ data.getDescripcion() + "| USUARIO: "+ data.getUsername());
    	loginPage.escribirUsuario(data.getUsername());
    	loginPage.escribirContrasena(data.getPassword());
        loginPage.hacerClickLogin();
    	Report.info("SE COMPLETAN DATOS DE LOGIN Y HACE CLICK EN BOTÓN LOGIN");
               
        System.out.println("Se valida que la contraseña es obligatoria. Aparece el siguiente mensaje " + loginPage.leerErrorInvalido());
                Assert.assertEquals(loginPage.leerErrorInvalido(), "Epic sadface: Username and password do not match any user in this service", "El mensaje es diferente al esperado");

                Report.pass("SE VALIDA MENSAJE DE USUARIO INCORRECTO");
                Report.screenshot(driver, "login-usuario-incorrecto");
    }



}
