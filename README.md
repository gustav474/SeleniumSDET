# SeleniumSDET

## About
Log in to Yandex mail and count the number of letters with a given subject.

Use Selenium Grind, Page Object desing, Allure

## How to use it

### Before testing
 > The easiest mode to run the server in is "standalone". Just run:
```java -jar selenium-server-4.0.0-alpha-6.jar standalone```

> The server will be listening on http://localhost:4444/, and that's the URL you should point remote WebDrivers to (that is, you no longer need to use > > > http://localhost:4444/wd/hub) By default, the server will detect available drivers it can use (GeckoDriver, etc) by looking at the PATH You can use this mode just to make sure that things are working as you expect them to.


If you need other modes, check official docs or ```https://github.com/SeleniumHQ/selenium/wiki/Selenium-Grid-4```


* ```maven clean``` and ```maven test``` via Maven plugin
* ```allure:serve``` via Allure plugin
