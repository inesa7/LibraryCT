package libraryCT;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.TestBase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentFiltersBookCategoriesDropdown extends TestBase {


    @Test
    public void filterBookCategories() {
        driver.get("https://library2.cybertekschool.com/login.html");

        ArrayList<String> emailsL = new ArrayList<>(Arrays.asList("librarian50@library",
                "librarian19@library", "student58@library", "student59@library",
                "student60@library"));

        for (String each : emailsL) {

            WebElement emailBox = driver.findElement(By.id("inputEmail"));
            emailBox.sendKeys(each);
            WebElement password = driver.findElement(By.id("inputPassword"));
            password.sendKeys("Sdet2022*");


            WebElement signIn = driver.findElement(By.xpath("//button[text()='Sign in']"));
            signIn.click();

            WebElement book=driver.findElement(By.xpath("//span[.='Books']"));
            book.click();

            WebElement allCategories=driver.findElement(By.id("book_categories"));
            allCategories.click();

            Select obj=new Select(allCategories);

            List<WebElement>allObjects= obj.getOptions();

            int expectedResult=21;

            assertEquals(expectedResult, allObjects.size());

            driver.findElement(By.xpath("//a[@role='button']")).click();




            driver.findElement(By.linkText("Log Out")).click();



        }


    }
}