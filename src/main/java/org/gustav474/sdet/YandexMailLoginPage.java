package org.gustav474.sdet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YandexMailLoginPage {
    private WebDriver driver;
    private By logInButtonLocator = By.xpath("//a[contains(@href, 'https://passport.yandex.com/auth')]");

    public YandexMailLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexMailLoginPage open(String path) {
        driver.get(path);
        return this;
    }

    public YandexPassportLoginPage pushLogInButton () {
        driver.findElement(logInButtonLocator).click();
        return new YandexPassportLoginPage(driver);
    }
}
