package org.gustav474.sdet;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Data
public class YandexMailLoginPage {
    private WebDriver driver;;
    private String login;
    private String password;
    private By logInButtonLocator = By.xpath("//a[contains(@href, 'https://passport.yandex.com/auth')]");

    public YandexMailLoginPage(WebDriver driver) {
        this.driver = driver;
//        driver.get(targetYandexPage);
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
