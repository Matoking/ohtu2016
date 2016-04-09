package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        System.out.println("==");
        
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        System.out.println("==");
        System.out.println( driver.getPageSource() );
        
        element = driver.findElement(By.linkText("logout"));
        element.click();
        
        // Failed login
        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekaaa");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        // Nonexistent account
        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.linkText("login"));       
        element.click(); 
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("nobody");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekaaa");
        element = driver.findElement(By.name("login"));
        element.submit();
        
        // Creating new account
        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.linkText("register new user"));       
        element.click();
        
        element = driver.findElement(By.name("username"));
        element.sendKeys("kalle");
        element = driver.findElement(By.name("password"));
        element.sendKeys("aG00dpassword");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("aG00dpassword");
        element = driver.findElement(By.name("add"));
        element.submit();
        
        element = driver.findElement(By.linkText("continue to application mainpage"));       
        element.click(); 
        element = driver.findElement(By.linkText("logout"));       
        element.click(); 
        
        
        
    }
}
