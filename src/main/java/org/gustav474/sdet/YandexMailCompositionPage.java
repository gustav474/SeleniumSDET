package org.gustav474.sdet;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
public class YandexMailCompositionPage {
    private WebDriver driver;
    private String to;
    private String subject;
    private By toLocator = By.className("composeYabbles");
    private By subjectLocator = By.className("composeTextField");
    private By messageBodyLocator = By.xpath("//div[@id='cke_1_contents']/child::div/child::div");
    private By composeButtonLocator = By.xpath("//div[@class='ComposeSendButton-Text']/parent::span/parent::button");
    private By successfullyMailSendingLocator = By.xpath("//div[@class='ComposeDoneScreen-Title']//child::span");

    public YandexMailCompositionPage(WebDriver driver) {
        this.driver = driver;
    }

    public YandexMailCompositionPage composeMail(int countOfMatchingMails) {
        String messageBody = String.format("Колличество найденных писем с темой 'Simbirsoft Тестовое задание': %d",
                countOfMatchingMails);
        new WebDriverWait(driver, 7)
                .until(ExpectedConditions.elementToBeClickable(toLocator))
                .sendKeys(to);
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
