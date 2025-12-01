package test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterTest {

    public WebDriver driver;
    public WebDriverWait wait;
    
    @Test
    public void registro_valido() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(28)); // este es el tiempo máximo que va a esperar 
        driver.manage().window().maximize();
        driver.get("http://www.automationpractice.pl/index.php");
        System.out.println("Ingresar a la página de Your Logo");
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[normalize-space()='Sign in']")));
        WebElement singInBtn = driver.findElement(By.xpath("//a[normalize-space()='Sign in']"));
        singInBtn.click();
        System.out.println("Hacer click en el bóton Sing In");
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email_create")));
        WebElement createMailTxt = driver.findElement(By.id("email_create"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Create an account']")));
        WebElement createAccountBtn = driver.findElement(By.xpath("//span[normalize-space()='Create an account']"));
        createMailTxt.clear();
        createMailTxt.sendKeys("testit010@gmail.com");
        System.out.println("Ingresar mail válido en el campo de Email address");
        createAccountBtn.click();
        System.out.println("Hacer click en el Bóton Create an account");
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[normalize-space()='Mrs.']")));
        WebElement mrsBtn = driver.findElement(By.xpath("//label[normalize-space()='Mrs.']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_firstname")));
        WebElement firstNameTxt = driver.findElement(By.id("customer_firstname"));       
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("customer_lastname")));
        WebElement lastNameTxt = driver.findElement(By.id("customer_lastname"));         
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
        WebElement mailTxt = driver.findElement(By.id("email"));        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwd")));
        WebElement passwordTxt = driver.findElement(By.id("passwd"));  
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='8'])[1]")));
        WebElement daySelector = driver.findElement(By.xpath("(//option[@value='8'])[1]"));  
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='10'])[2]")));
        WebElement monthSelector = driver.findElement(By.xpath("(//option[@value='10'])[2]"));  
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//option[@value='1996'])[1]")));
        WebElement yearSelector = driver.findElement(By.xpath("(//option[@value='1996'])[1]"));  
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("newsletter")));
        WebElement newsletter = driver.findElement(By.id("newsletter"));  
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submitAccount")));
        WebElement registerBtn = driver.findElement(By.id("submitAccount"));  
   
        mrsBtn.click();
        firstNameTxt.sendKeys("Camila");
        lastNameTxt.sendKeys("Bianchi");
        System.out.println("Validar que el mail sea el que se ingresó en la página anterior: " + mailTxt.getAttribute("value"));
        passwordTxt.sendKeys("123456789");
        daySelector.click();
        monthSelector.click();
        yearSelector.click();
        newsletter.click();
        registerBtn.click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"center_column\"]/p[1]")));
        WebElement createdAccountLabel = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[1]"));   
        System.out.println("Validar que se creó la cuenta correctamente: " + createdAccountLabel.getAttribute("value"));
        System.out.println("Validar que se creó la cuenta correctamente: " + createdAccountLabel.getText());
        driver.close();
    }
} 