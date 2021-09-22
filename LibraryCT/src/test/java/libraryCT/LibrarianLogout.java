package libraryCT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Given user is on the homePage
 * When user click username on the right top corner And user click Log Out
 * Then verify user navigate back to login page.
 */

public class LibrarianLogout {
        WebDriver driver;

        @BeforeAll
        public static void setUpDriver(){

            WebDriverManager.chromedriver().setup();
        }

        @BeforeEach
        public void setupWebDriver(){
            driver=new ChromeDriver();
            driver.manage().window().maximize();

        }
        @Test
        public void testLibrarianLogOut() {

            driver.get("https://library2.cybertekschool.com/login.html");

            WebElement emailBox = driver.findElement(By.id("inputEmail"));
            emailBox.sendKeys("librarian50@library");
            WebElement password = driver.findElement(By.id("inputPassword"));
            password.sendKeys("Sdet2022*");


            WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
            signIn.click();

            driver.findElement(By.xpath("//a[@role='button']")).click();


            driver.findElement(By.linkText("Log Out")).click();


            String expectedUrl="https://library2.cybertekschool.com/login.html";
            String actualUrl= driver.getCurrentUrl();
            assertEquals(expectedUrl, actualUrl);

        }


        @AfterEach
        public void closeBrowser(){
            driver.quit();
        }

    }

