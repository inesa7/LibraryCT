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

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserStories1_7 {

    WebDriver driver;
    ArrayList<String> allUsers = new ArrayList<>(Arrays.asList("librarian53@library","librarian20@library",
            "student62@library","student63@library","student64@library"));
    ArrayList<String> allLibrarians = new ArrayList<>(Arrays.asList("librarian53@library","librarian20@library"));
    ArrayList<String> students = new ArrayList<>(Arrays.asList("student62@library","student63@library","student64@library"));


    @BeforeEach
    public void setupWebDriver(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://library2.cybertekschool.com/login.html");

    }

    @AfterEach
    public void closeBrowser(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.quit();
    }


    @Test
    public void librariansLoginAndLogoutSuccessfully_US1_TC1(){


        for (String eachLibrarian : allLibrarians) {

            String expectedTitle = "Login - Library";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle,actualTitle);

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachLibrarian);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            //make sure that there are 3 modules
            List<String> expectedModules = new ArrayList<>(Arrays.asList("Dashboard","Users","Books"));

            String actualModule1 = driver.findElement(By.linkText("Dashboard")).getText();
            String actualModule2 = driver.findElement(By.linkText("Users")).getText();
            String actualModule3 = driver.findElement(By.linkText("Books")).getText();
            List<String> actualModules = new ArrayList<>(Arrays.asList(actualModule1,actualModule2,actualModule3));
            assertTrue( expectedModules.equals(actualModules));

            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();

        }
    }

    @Test
    public void testStudentsLoginAndLogoutSuccessfully_US1_TC2(){

        for (String eachStudent : students) {

            String expectedUrl = "https://library2.cybertekschool.com/login.html";
            String actualUrl = driver.getCurrentUrl();
            assertEquals(expectedUrl,actualUrl);

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachStudent);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            List<String> expectedModules = new ArrayList<>(Arrays.asList("Books", "Borrowing Books"));
            String actualModule1 = driver.findElement(By.cssSelector("span[class='title']")).getText();
            String actualModule2 = driver.findElement(By.cssSelector("#menu_item .nav-item:nth-of-type(2) .nav-link")).getText();
            List<String> actualModules = new ArrayList<>(Arrays.asList(actualModule1,actualModule2));
            assertTrue(expectedModules.equals(actualModules));

            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();

        }
    }

    @Test
    public void testUsersLoginFailure_US1_TC3(){

        for (String eachUser : allUsers) {

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachUser+"15");
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            WebElement errorMessage = driver.findElement(By.xpath("//div[@class='mb-4']/div"));
            String expectedErrMsg = "Sorry, Wrong Email or Password";
            String actualErrMsg = errorMessage.getText();
            assertEquals(expectedErrMsg,actualErrMsg);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://library2.cybertekschool.com/login.html");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
        driver.findElement(By.cssSelector("#inputEmail")).sendKeys("librarian53@library");
        driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");

    }

    @Test
    public void testLibrariansAddingUser_US2_TC1() throws InterruptedException {

        int i = 0;
        for (String eachLibrarian : allLibrarians) {

            String expectedTitle = "Login - Library";
            String actualTitle = driver.getTitle();
            assertEquals(expectedTitle, actualTitle);

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachLibrarian);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            driver.findElement(By.cssSelector("#menu_item .nav-item:nth-of-type(2) .title")).click();
            driver.findElement(By.cssSelector(".portlet-title [data-target]")).click();
            driver.findElement(By.cssSelector("input[name='full_name']")).click();
            driver.findElement(By.cssSelector("input[name='full_name']")).sendKeys("Muhtar Kral");
            driver.findElement(By.cssSelector("input[name='password']")).sendKeys("12345KralimBen!");

            //this email needs to change everytime

            driver.findElement(By.cssSelector("input[name='email']")).sendKeys("muhtar272"+i++ +"@gmail.com");

            driver.findElement(By.cssSelector("#add_user_modal .btn-primary")).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            String expectedToastMsg = "The user has been created.";
            WebElement actualToast = driver.findElement(By.cssSelector(".toast-message"));
            String actualToastMsg = actualToast.getText();
            assertEquals(expectedToastMsg,actualToastMsg);
            Thread.sleep(5000);  //Toast message is on the logout button. We should wait to pass



            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();

        }
    }

    @Test
    public void testLibrariansAddingNewBook_US3_TC1() throws InterruptedException {

        for (String eachLibrarian : allLibrarians) {
            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachLibrarian);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//li[@class='nav-item'][3]/a")).click();
            driver.findElement(By.xpath("//div[@class='portlet-title']/span/a")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("input[name='isbn']")).sendKeys("1000");
            driver.findElement(By.cssSelector("input[name='name']")).sendKeys("Asya'nin Gozyaslari");
            driver.findElement(By.cssSelector("input[name='year']")).sendKeys("2021");
            driver.findElement(By.cssSelector("input[name='author']")).sendKeys("Furkan");
            driver.findElement(By.cssSelector("select#book_group_id")).click();
            driver.findElement(By.cssSelector("select#book_group_id > option[value='5']")).click();
            driver.findElement(By.cssSelector(".modal-footer .btn-primary")).click();

            String expectedToastMsg = "The book has been created.";
            WebElement actualToast = driver.findElement(By.cssSelector(".toast-message"));
            String actualToastMsg = actualToast.getText();
            assertEquals(expectedToastMsg,actualToastMsg);

            Thread.sleep(5000); //Toast message is on the logout button. We should wait to pass
            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();
        }
    }

    @Test
    public void testLibrariansSelectUsers_US5_TC1(){

        for (String eachLibrarian : allLibrarians) {

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachLibrarian);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//li[2]//a ")).click();
            WebElement userGroupBox = driver.findElement(By.xpath("//div[@class='form-group']//select"));
            userGroupBox.click();
            Select userGroupObj = new Select(userGroupBox);
            userGroupObj.selectByValue("null");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            userGroupBox.click();
            userGroupObj.selectByValue("2");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            userGroupBox.click();
            userGroupObj.selectByValue("3");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            List<WebElement> userGroupObjects = driver.findElements(By.xpath("//select[@id='user_groups']//option"));
            assertTrue(userGroupObjects.size() == 3);

            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();
        }
    }

    @Test
    public void testLibrariansSelectUserStatus_US6_TC1(){

        for (String eachLibrarian : allLibrarians) {

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachLibrarian);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//li[2]//a ")).click();
            WebElement statusDropDownBox = driver.findElement(By.xpath("//select[@id='user_status']"));
            statusDropDownBox.click();
            Select statusObj = new Select(statusDropDownBox);
            statusObj.selectByValue("ACTIVE");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            statusDropDownBox.click();
            statusObj.selectByValue("INACTIVE");

            List<WebElement> statusObjects = driver.findElements(By.xpath("//select[@id='user_status']//option"));
            assertTrue(statusObjects.size() == 2);

            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();
        }
    }

    @Test
    public void testUsersFilterBookCategories_US7_TC1(){

        for (String eachUser : allUsers) {

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachUser);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//li[@class='nav-item']//a//i[@class='fa fa-book']")).click();
            driver.findElement(By.xpath("//div[@class='col-md-6']//select")).click();

            List<WebElement> totalOptions = driver.findElements(By.xpath("//select[@id='book_categories']//option"));
            int expectedTotalOpt = 21;
            int actualTotalOpt = totalOptions.size();
            assertTrue(expectedTotalOpt == actualTotalOpt);

            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();
        }
    }

    @Test
    public void testUsersSelectBook_US7_TC2(){

        for (String eachUser : allUsers) {

            driver.findElement(By.cssSelector("#inputEmail")).sendKeys(eachUser);
            driver.findElement(By.id("inputPassword")).sendKeys("Sdet2022*");
            driver.findElement(By.cssSelector("button[type='submit']")).click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//li[@class='nav-item']//a//i[@class='fa fa-book']")).click();
            WebElement bookCtgrDropDwn=  driver.findElement(By.xpath("//div[@class='col-md-6']//select"));
            bookCtgrDropDwn.click();

            Select categoryObj = new Select(bookCtgrDropDwn);
            categoryObj.selectByValue("6");
            String actualOption = driver.findElement(By.xpath("//select[@id='book_categories']//option[@value='6']")).getText();
            String expectedOption = "Drama";
            assertTrue(expectedOption.equals(actualOption));

            driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']")).click();
            driver.findElement(By.cssSelector("a[class='dropdown-item']")).click();
        }
    }


}