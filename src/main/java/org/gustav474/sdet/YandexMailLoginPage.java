package org.gustav474.sdet;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Data
public class YandexMailLoginPage {
    private WebDriver driver;
    private final String targetYandexPage = "https://mail.yandex.com";
    private String login;
    private String password;
    By logInButtonLocator = By.linkText("Log in");

    public YandexMailLoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get(targetYandexPage);
    }

    public YandexPassportLoginPage pushLogInButton () {
        driver.findElement(logInButtonLocator).click();
        return new YandexPassportLoginPage(driver);
    }
}
