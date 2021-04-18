package org.gustav474.sdet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YandexPassportLoginPage {
    private WebDriver driver;
    private By loginLocator = By.xpath("//input[@id='passp-field-login']");
    private By submitButtonLocator = By.xpath("//button[@type='submit']");
    private By passwordLocator = By.xpath("//input[@id='passp-field-passwd']");

    public YandexPassportLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexMailInboxPage pushSubmitButton() {
        driver.findElement(submitButtonLocator).click();
        return new YandexMailInboxPage(driver);
    }

    public YandexPassportLoginPage typeLogin(String login) {
        driver.findElement(loginLocator).sendKeys(login);
        return this;
    }

    public YandexPassportLoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }
}
