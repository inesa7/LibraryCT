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
 *                 Given student is on the loginPage
 *                 Then verify that the URL is “https://library2.cybertekschool.com/login.html”
 *                 When student enters valid email address and password
 *                 And student clicks sign in button
 *                 Then verify that there are 2 modules on the page
 */

public class studentLogin {

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


    //public static void main(String[] args) throws InterruptedException{


    @Test
    public void loginStudent() {


        ArrayList<String> studentEmails = new ArrayList<>
                (Arrays.asList("student58@library", "student59@library", "student60@library"));

        for (String eachEmail : studentEmails) {

            //Given student is on the loginPage
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            driver.get("https://library2.cybertekschool.com/login.html");
            driver.manage().window().maximize();

            //Then verify that the URL is “https://library2.cybertekschool.com/login.html”
            String expectedURL = "https://library2.cybertekschool.com/login.html";
            String actualURL = driver.getCurrentUrl();
            System.out.println("actual URL = " + actualURL);

            if (expectedURL.equals(actualURL)) {
                System.out.println("Test passed. Expected URL matches actual URL");
            } else {
                System.out.println("Test failed. Expected URL does not match actual URL");
                System.out.println("Expected URL = " + expectedURL);
                System.out.println("Actual URL = " + actualURL);
            }


            //When student enters valid email address and password
            driver.findElement(By.id("inputEmail")).sendKeys(eachEmail);
            System.out.println("User: " + eachEmail);
            //password
            String password = "Sdet2022*";
            driver.findElement(By.id("inputPassword")).sendKeys(password);
            //System.out.println("password = " + password);

            //And student click sign in button
            driver.findElement(By.cssSelector("#login-form > button")).click();


            // verify that there are 2 modules on the page
            List<WebElement> modules = driver.findElements(By.className("title"));
            assertEquals(modules.size(), 2);
            System.out.println("modules.size() = " + modules.size());

//            WebElement moduleBooks = driver.findElement(By.linkText("Books"));
//            WebElement moduleBorrowingBooks = driver.findElement(By.linkText("Borrowing Books"));
//            ArrayList<String> actualModules = new ArrayList<>
//                    (Arrays.asList(moduleBooks.getText(), moduleBorrowingBooks.getText()));
//            ArrayList<String> expectedModules = new ArrayList<>();
//            expectedModules.addAll(Arrays.asList("Books", "Borrowing Books"));
//
//            if (actualModules.size() == expectedModules.size() && actualModules.equals(expectedModules)) {
//                System.out.println("Test passed. Expected result matches actual result");
//            } else {
//                System.out.println("Test failed. Expected result does not match actual result");
//            }

//                List<WebElement> modules = driver.findElements(By.xpath("//li[@class='nav-item']"));
//                System.out.println("modules.size() = " + modules.size());




        }

    }
}

