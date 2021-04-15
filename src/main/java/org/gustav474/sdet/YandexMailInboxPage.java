package org.gustav474.sdet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class YandexMailInboxPage {
    private WebDriver driver;
    By mailTitleLocator = By.xpath(
            "//span[@class='mail-MessageSnippet-Item mail-MessageSnippet-Item_subject']/child::span");
    By composeLocator = By.xpath("//a[@href='#compose']");

    public YandexMailInboxPage(WebDriver driver) {
        this.driver = driver;
    }

    private List<WebElement> getAllMailsTitle() {
        List<WebElement> mailTitles = new WebDriverWait(driver, 15).until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(mailTitleLocator));
        return mailTitles;
    }

    public int matchTitleByTextCounting(String matchingTitle) {
        List<WebElement> listOfTitles = getAllMailsTitle();
        int i = 0;
        for (WebElement spanTitle : listOfTitles) {
            if (spanTitle.getText().equals(matchingTitle)) {
                i++;
            }
        }
        return i;
    }

    public YandexMailCompositionPage pushComposeMailButton() {
        driver.findElement(composeLocator).click();
        return new YandexMailCompositionPage(driver);
    }

    public boolean isMailInboxPage() {
        boolean isMailInboxPage = new WebDriverWait(driver, 10).until(
                ExpectedConditions.titleContains("Входящие — Яндекс.Почта"));
        return isMailInboxPage;
    }
}
