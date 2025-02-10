package com.basics.tests.patterns.page_object_model.base;

import com.basics.tests.config.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BasePage {
    private static final int DEFAULT_WAIT_DURATION = 10;
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    private final String baseUrl;

    public BasePage(WebDriver driver) {
        this(driver, DEFAULT_WAIT_DURATION);
    }

    public BasePage(WebDriver driver, int waitDuration) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
        baseUrl = Configuration.getProperty("heroku.url");
    }

    protected abstract String getPageSlug();

    public void open() {
        driver.get(baseUrl + getPageSlug());
    }

    public boolean atPage() {
        return wait.until(urlContains(getPageSlug()));
    }

    public void clickOnButtonByText(String buttonText) {
        By locator = By.xpath(String.format("//button[text()='%s']", buttonText.replace("'", "\\'")));
        wait.until(elementToBeClickable(locator)).click();
    }

    public void clickonButtonByCssSelector(By locator){
        wait.until(elementToBeClickable(locator)).click();
    }

    protected void waitForElementAppearance(WebElement element){
        wait.until(visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element){
        wait.until(elementToBeClickable(element));
    }
}


