package org.gustav474.sdet;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class YandexPassportLoginPage {
    private WebDriver driver;
    private String login;
    private String password;
    By loginLocator = By.xpath("//input[@id='passp-field-login']");
    By submitButtonLocator = By.xpath("//button[@type='submit']");
    By passwordLocator = By.xpath("//input[@id='passp-field-passwd']");

    public YandexPassportLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexPassportLoginPage pushSubmitButton() {
        driver.findElement(submitButtonLocator).click();
        return this;
    }

    public YandexPassportLoginPage typeLogin () {
        driver.findElement(loginLocator).sendKeys(login);
        pushSubmitButton();
        return this;
    }

    public YandexMailInboxPage typePassword () {
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.elementToBeClickable(passwordLocator))
                .sendKeys(password);
        pushSubmitButton();
        return new YandexMailInboxPage(driver);
    }
}
