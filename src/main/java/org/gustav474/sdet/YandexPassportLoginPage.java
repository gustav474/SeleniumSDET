package org.gustav474.sdet;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
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
        pushSubmitButton();
        return this;
    }

    public YandexPassportLoginPage typePassword(String password) {
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.elementToBeClickable(passwordLocator))
                .sendKeys(password);
        return this;
    }
}
