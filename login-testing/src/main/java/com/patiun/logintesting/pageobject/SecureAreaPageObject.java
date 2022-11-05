package com.patiun.logintesting.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SecureAreaPageObject {

    private static final By PAGE_NAME_BY = By.xpath("//h2");

    private final WebDriver driver;

    public SecureAreaPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageName() {
        WebElement pageNameElement = driver.findElement(PAGE_NAME_BY);
        return pageNameElement.getText();
    }

}
