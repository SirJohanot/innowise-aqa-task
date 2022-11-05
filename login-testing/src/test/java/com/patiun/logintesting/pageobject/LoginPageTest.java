package com.patiun.logintesting.pageobject;

import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPageObject pageObject;

    @Step("Launch the web browser and go to the HerokuApp login page")
    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        pageObject = new LoginPageObject(driver);
    }

    @AfterEach
    public void tearDown() {
        savePageScreenshot(driver);
        tearDownDriverAndPageObject();
    }

    @Test
    @Story("User tries to log in with valid credentials")
    @Description("Login should redirect to the secure area when the user is valid")
    public void testLoginShouldRedirectToSecureAreaWhenTheUserIsValid() {
        //given
        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        String expectedPageName = "Secure Area";
        //when
        SecureAreaPageObject afterLogin = pageObject.loginAs(username, password);
        String actualPageName = afterLogin.getPageName();
        //then
        Assertions.assertEquals(expectedPageName, actualPageName);
    }

    @Test
    @Story("User tries to log in with invalid credentials")
    @Description("Login should redirect to the login page when the user is not valid")
    public void testLoginShouldRedirectToLoginPageWhenTheUserIsNotValid() {
        //given
        String username = "blahBlahBlahInvalid";
        String password = "NotSoSecretPassword!";
        String expectedPageName = "Login Page";
        //when
        LoginPageObject afterLogin = pageObject.loginAsExpectingError(username, password);
        String actualPageName = afterLogin.getPageName();
        //then
        Assertions.assertEquals(expectedPageName, actualPageName);
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    private byte[] savePageScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step("Leave HerokuApp and quit the web browser")
    private void tearDownDriverAndPageObject() {
        pageObject = null;
        driver.quit();
    }
}
