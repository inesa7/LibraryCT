package libraryCT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * User Story 1: As a user, I should be able to login to LibraryCT.
 * AC#2:
 * Given librarian is on the loginPage
 * Then verify that the title is “Login - Library”
 * When librarian enters valid email address and password
 * And librarian click sign in button
 * Then verify that there are 3 modules on the page
 */

public class LoginLibrarian1 {

    WebDriver driver;

    // ArrayList<String> librarians = new ArrayList<>
            //(Arrays.asList("librarian50@library", "librarian19@library"));


    @BeforeEach
    public void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterEach
    public void closeBrowser() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.quit();
    }

    @Test
    public void loginLibrarian() {
        //for (String eachLibrarian : librarians) {

            driver.get("https://library2.cybertekschool.com/login.html");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            // verify that the title is “Login - Library”
            //assertTrue(driver.getTitle().equals("Login - Library"));
            System.out.println("driver.getTitle() = " + driver.getTitle());

            // librarian enters valid email address
            driver.findElement(By.id("inputEmail")).sendKeys("librarian19@library");
           // System.out.println("User: " + eachLibrarian);

            // librarian enters valid password
            String password = "Sdet2022*";
            driver.findElement(By.id("inputPassword")).sendKeys(password);

            // librarian clicks sign in button
            driver.findElement(By.cssSelector("#login-form > button")).click();


            // verify that there are 3 modules on the page

            List<WebElement> modules = driver.findElements(By.className("title"));
            assertTrue(modules.size() == 3);
            System.out.println("modules.size() = " + modules.size());


    }

    }



//"student58@library", "student59@library", "student60@library"
