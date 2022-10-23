package com.innowise.logintesting.pageobject;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HerokuAppPageObjectTest {

    private WebDriver driver;
    private HerokuAppPageObject pageObject;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        pageObject = new HerokuAppPageObject(driver);
    }

    @AfterEach
    public void cleanup() {
        pageObject = null;
        driver.quit();
    }

    @Test
    public void testLoginShouldRedirectToSecureAreaWhenTheUserIsValid(){
        //given
        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        String expectedPageName = "Secure Area";
        //when
        String actualPageName = pageObject.login(username, password).findElement(By.xpath("//h2")).getText();
        //then
        Assertions.assertEquals(expectedPageName, actualPageName);
    }

    @Test
    public void testLoginShouldRedirectToLoginPageWhenTheUserIsNotValid(){
        //given
        String username = "blahBlahBlahInvalid";
        String password = "NotSoSecretPassword!";
        String expectedPageName = "Login Page";
        //when
        String actualPageName = pageObject.login(username, password).findElement(By.xpath("//h2")).getText();
        //then
        Assertions.assertEquals(expectedPageName, actualPageName);
    }
}
