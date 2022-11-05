package com.innowise.logintesting.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageObject {

    private static final By PAGE_NAME_BY = By.xpath("//h2");
    private static final By USERNAME_BY = By.xpath("//input[@id='username']");
    private static final By PASSWORD_BY = By.xpath("//input[@id='password']");
    private static final By LOGIN_BUTTON_BY = By.xpath("//button[@class='radius']");

    private final WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageName() {
        WebElement pageNameElement = driver.findElement(PAGE_NAME_BY);
        return pageNameElement.getText();
    }

    @Step("Log in with username='{username}' and password={password}")
    public SecureAreaPageObject loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new SecureAreaPageObject(driver);
    }

    @Step("Log in with username='{username}' and password={password}")
    public LoginPageObject loginAsExpectingError(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    @Step("Enter '{username}' into the 'Username' input field")
    private void enterUsername(String username) {
        driver.findElement(USERNAME_BY).sendKeys(username);
    }

    @Step("Enter '{password}' into the 'Password' input field")
    private void enterPassword(String password) {
        driver.findElement(PASSWORD_BY).sendKeys(password);
    }

    @Step("Hit the 'Login' button")
    private void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON_BY).click();
    }
}
