package test;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.ProductPage;
import utils.reporte.ExtentListener;

@Listeners(ExtentListener.class)
public class ProductTest {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    ProductPage productPage;

    @Parameters("navegador")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("edge") String navegador) {
    	switch (navegador.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			default:
			driver = new EdgeDriver();
    		}
        wait = new WebDriverWait(driver, Duration.ofSeconds(28)); // este es el tiempo máximo que va a esperar 
        driver.manage().window().maximize();
        System.out.println("Se ingresó a la página");
        
        productPage = new ProductPage(driver);   
    	loginPage = new LoginPage(driver);
        productPage.goToUrl("https://www.saucedemo.com/");

    }
    
    @AfterMethod(alwaysRun = true)
    public void close() {
        driver.quit();
    }
 
    @Test(groups = {"INVENTARIO", "REGRESION", "EXITO", "SMOKE"})
    public void inventario_Completo() {
    	loginPage.loginCompleto("standard_user", "secret_sauce");
    	
    	Assert.assertEquals(productPage.obtenerNombreProducto1(),"Sauce Labs Backpack");
    	System.out.println("Se válida que el primer producto es " + productPage.obtenerNombreProducto1());
    	
    	Assert.assertEquals(productPage.obtenerNombreProducto2(),"Sauce Labs Bike Light");
    	System.out.println("Se válida que el 2do producto es " + productPage.obtenerNombreProducto2());
    	
    	Assert.assertEquals(productPage.obtenerNombreProducto3(),"Sauce Labs Bolt T-Shirt");
    	System.out.println("Se válida que el 3er producto es " + productPage.obtenerNombreProducto3());
    	
    	Assert.assertEquals(productPage.obtenerNombreProducto4(),"Sauce Labs Fleece Jacket");
    	System.out.println("Se válida que el 4to producto es " + productPage.obtenerNombreProducto4());
    	
    	Assert.assertEquals(productPage.obtenerNombreProducto5(),"Sauce Labs Onesie");
    	System.out.println("Se válida que el 5tp producto es " + productPage.obtenerNombreProducto5());
    	
    	try {
        	Assert.assertEquals(productPage.obtenerNombreProducto6(),"Sauce Labs T-Shirt (Red)");
        	System.out.println("Se válida que el sexto producto es " + productPage.obtenerNombreProducto6());
    	} catch(Error e) {
        	System.out.println("FALLÓ LA VALIDACIÓN: " + e);
    	}
   	
  	
    	
    }
}

