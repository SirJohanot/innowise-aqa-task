package com.innowise.logintesting.pageobject;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HerokuAppPageObjectTest {

    private WebDriver driver;
    private HerokuAppPageObject pageObject;

    @Step("Go to the HerokuApp Login Page")
    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        pageObject = new HerokuAppPageObject(driver);
    }

    @Step("Leave HerokuApp")
    @AfterEach
    public void tearDown() {
        savePageScreenshot(driver);
        pageObject = null;
        driver.quit();
    }

    @Test
    @Description("Login should redirect to secure area when the user is valid")
    public void testLoginShouldRedirectToSecureAreaWhenTheUserIsValid() {
        //given
        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        String expectedPageName = "Secure Area";
        //when
        String actualPageName = getPageNameAfterLogin(username, password);
        //then
        Assertions.assertEquals(expectedPageName, actualPageName);
    }

    @Test
    @Description("Login should redirect to login page when the user is not valid")
    public void testLoginShouldRedirectToLoginPageWhenTheUserIsNotValid() {
        //given
        String username = "blahBlahBlahInvalid";
        String password = "NotSoSecretPassword!";
        String expectedPageName = "Login Page";
        //when
        String actualPageName = getPageNameAfterLogin(username, password);
        //then
        Assertions.assertEquals(expectedPageName, actualPageName);
    }

    @Step("Log in with username={username} and password={password}")
    private String getPageNameAfterLogin(String username, String password) {
        WebDriver driverAfterLogin = pageObject.login(username, password);
        By pageNameBy = By.xpath("//h2");
        WebElement pageNameElement = driverAfterLogin.findElement(pageNameBy);
        return pageNameElement.getText();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] savePageScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
