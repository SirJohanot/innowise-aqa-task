package com.innowise.logintesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuAppPageObject {

    private static final By USERNAME_BY = By.xpath("//input[@id='username']");
    private static final By PASSWORD_BY = By.xpath("//input[@id='password']");
    private static final By LOGIN_BUTTON_BY = By.xpath("//button[@class='radius']");

    private final WebDriver driver;

    public HerokuAppPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver login(String username, String password){
        driver.findElement(USERNAME_BY).sendKeys(username);
        driver.findElement(PASSWORD_BY).sendKeys(password);
        driver.findElement(LOGIN_BUTTON_BY).click();
        return driver;
    }
}
