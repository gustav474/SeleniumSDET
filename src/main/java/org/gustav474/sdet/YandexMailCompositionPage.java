package org.gustav474.sdet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMailCompositionPage {
    private WebDriver driver;
    private By toLocator = By.className("composeYabbles");
    private By subjectLocator = By.className("composeTextField");
    private By messageBodyLocator = By.xpath("//div[@id='cke_1_contents']/div/div");
    private By composeButtonLocator = By.xpath("//div[@class='ComposeSendButton-Text']/../..");
    private By successfullyMailSendingLocator = By.xpath("//div[@class='ComposeDoneScreen-Title']/span");

    public YandexMailCompositionPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexMailCompositionPage composeMail(int countOfMatchingMails, String to, String subject) {
        String messageBody = String.format("Колличество найденных писем с темой 'Simbirsoft Тестовое задание': %d",
                countOfMatchingMails);
        driver.findElement(toLocator).sendKeys(to);
        driver.findElement(subjectLocator).sendKeys(subject);
        driver.findElement(messageBodyLocator).sendKeys(messageBody);

        submitButtonPush();
        return this;
    }

    private void submitButtonPush() {
        driver.findElement(composeButtonLocator).click();
    }

    public boolean isMailSendingSuccessfully() {
        boolean isMailSendingSuccessfully = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.textToBePresentInElementLocated(
                        successfullyMailSendingLocator, "Письмо отправлено"));
        return isMailSendingSuccessfully;
    }

}
