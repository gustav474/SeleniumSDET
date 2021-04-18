package org.gustav474.sdet;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {
    private static Properties defaultProps;
    private WebDriver driver;
    String node = defaultProps.getProperty("node");
    String login = defaultProps.getProperty("login");
    String password = defaultProps.getProperty("password");
    String to = defaultProps.getProperty("to");
    String subject = defaultProps.getProperty("subject");
    String matchingTitle = defaultProps.getProperty("matchingTitle");
    String targetYandexPage = defaultProps.getProperty("targetYandexPage");

    /**
     * Reading properties from application.properties file
     */
    @BeforeClass
    public static void beforeClass() {
        defaultProps = new Properties();
        FileInputStream fis;
        InputStreamReader isr;
        try {
            fis = new FileInputStream("src/test/java/resources/application.properties");
            isr = new InputStreamReader(fis, "UTF-8");
            defaultProps.load(isr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void before() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(node), caps);
        driver.manage().timeouts().implicitlyWait(7000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void test1YandexMailInboxAuthorization() {
        YandexMailLoginPage yandexMailLoginPage = new YandexMailLoginPage(driver);
        yandexMailLoginPage.open(targetYandexPage);

        YandexPassportLoginPage yandexPassportLoginPage = yandexMailLoginPage.pushLogInButton();
        yandexPassportLoginPage.typeLogin(login);
        yandexPassportLoginPage.pushSubmitButton();
        yandexPassportLoginPage.typePassword(password);
        YandexMailInboxPage yandexMailInboxPage = yandexPassportLoginPage.pushSubmitButton();

        Assert.assertTrue(yandexMailInboxPage.isMailInboxPage());
    }

    @Test
    public void test2YandexMailInboxMessageCountingByTitle() {
        YandexMailLoginPage yandexMailLoginPage = new YandexMailLoginPage(driver);
        yandexMailLoginPage.open(targetYandexPage);

        YandexPassportLoginPage yandexPassportLoginPage = yandexMailLoginPage.pushLogInButton();
        yandexPassportLoginPage.typeLogin(login);
        yandexPassportLoginPage.pushSubmitButton();
        yandexPassportLoginPage.typePassword(password);
        YandexMailInboxPage yandexMailInboxPage = yandexPassportLoginPage.pushSubmitButton();
        int countOfMatchhing = yandexMailInboxPage.matchTitleByTextCounting(matchingTitle);

        Assert.assertEquals(3, countOfMatchhing);
    }

    @Test
    public void test3YandexSendMail() {
        YandexMailLoginPage yandexMailLoginPage = new YandexMailLoginPage(driver);
        yandexMailLoginPage.open(targetYandexPage);

        YandexPassportLoginPage yandexPassportLoginPage = yandexMailLoginPage.pushLogInButton();
        yandexPassportLoginPage.typeLogin(login);
        yandexPassportLoginPage.pushSubmitButton();
        yandexPassportLoginPage.typePassword(password);
        YandexMailInboxPage yandexMailInboxPage = yandexPassportLoginPage.pushSubmitButton();
        int countOfMatchhing = yandexMailInboxPage.matchTitleByTextCounting(matchingTitle);
        YandexMailCompositionPage yandexMailCompositionPage = yandexMailInboxPage.pushComposeMailButton();
        yandexMailCompositionPage.composeMail(countOfMatchhing, to, subject);
        
        Assert.assertTrue(yandexMailCompositionPage.isMailSendingSuccessfully());
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}