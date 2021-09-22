package libraryCT;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.TestBase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * User Story 5:
 * As a librarian, I want to select user groups.
 * AC:
 * Given librarian is on the homePage When librarian click Users module
 * And librarian click user group dropdown Then verify librarian have 3 options
 */

public class LibrarianSelectsUserStatus extends TestBase {

    @Test
    public void userStatus() {

        driver.get("https://library2.cybertekschool.com/login.html");

        WebElement emailBox = driver.findElement(By.id("inputEmail"));
        emailBox.sendKeys("librarian50@library");
        WebElement password = driver.findElement(By.id("inputPassword"));
        password.sendKeys("Sdet2022*");


        WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
        signIn.click();

        WebElement usersModule = driver.findElement(By.xpath("//span[.='Users']"));
        usersModule.click();

        WebElement status = driver.findElement(By.id("user_status"));
        status.click();

        Select obj = new Select(driver.findElement(By.id("user_status")));
        List<WebElement> objects = obj.getOptions();

        assertEquals(2, objects.size());

    }


}