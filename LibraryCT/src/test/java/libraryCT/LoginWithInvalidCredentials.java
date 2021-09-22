package libraryCT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginWithInvalidCredentials {

        public static void main(String[] args) throws InterruptedException {

            // Given user is on the loginPage
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://library2.cybertekschool.com/login.html");

//        When user enters invalid email address or password

            WebElement email = driver.findElement(By.id("inputEmail"));
            email.sendKeys("std58@library");
            WebElement password = driver.findElement(By.id("inputPassword"));
            password.sendKeys("Sdet*");

            Thread.sleep(3000);

            // student clicks sign in button
            WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
            signIn.click();
            Thread.sleep(3000);


//        Then verify the error message “Sorry, Wrong Email or Password”
            WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"login-form\"]/div[2]/div"));
            System.out.println("errorMessage.getText() = " + errorMessage.getText());

            Thread.sleep(3000);
            driver.quit();






    }

}
