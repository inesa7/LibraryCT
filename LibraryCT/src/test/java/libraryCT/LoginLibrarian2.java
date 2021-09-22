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
import org.openqa.selenium.support.ui.Select;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * User Story 1: As a user, I should be able to login to LibraryCT.
 * AC#2:
 * Given librarian is on the loginPage
 * Then verify that the title is “Login - Library”
 * When librarian enters valid email address and password
 * And librarian click sign in button
 * Then verify that there are 3 modules on the page
 */

public class LoginLibrarian2 {

    WebDriver driver;

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
    public void librarianLogIn() {
        driver.get("https://library2.cybertekschool.com/login.html");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // verify that the title is “Login - Library”
        assertTrue(driver.getTitle().equals("Login - Library"));

        // librarian enters valid email address
        driver.findElement(By.id("inputEmail")).sendKeys("librarian50@library");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // librarian enters valid password
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys("Sdet2022*");
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // librarian clicks sign in button
        driver.findElement(By.cssSelector("button.btn.btn-lg.btn-primary.btn-block")).click();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        // verify that there are 3 modules on the page
        List<WebElement> modules = driver.findElements(By.className("title"));
        assertTrue(modules.size() == 3);

        // ArrayList<String> librarians = new ArrayList<>
        //(Arrays.asList("librarian50@library", "librarian19@library"));
        //for (String eachLibrarian : librarians) {


    }
}
