package automation_basics.selenium.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AlertsTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setupDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void openMainPage() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void clickJSAlert() {
        clickOnButtonByText("Click for JS Alert");

        Alert alert = wait.until(alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");
        alert.accept();

        WebElement resultMessage = driver.findElement(By.id("result"));
        Assert.assertEquals(resultMessage.getText(), "You successfully clicked an alert");
    }

    @Test
    public void clickJSAlertConfirm() {
        clickOnButtonByText("Click for JS Confirm");
        Alert alert = wait.until(alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();
        WebElement resultMessage = driver.findElement(By.id("result"));
        Assert.assertEquals(resultMessage.getText(), "You clicked: Cancel");

        clickOnButtonByText("Click for JS Confirm");
        alert.accept();

        Assert.assertEquals(resultMessage.getText(), "You clicked: Ok");
    }

    @Test
    public void clickJSPrompt(){
        String promptMessage = "test";

        clickOnButtonByText("Click for JS Prompt");
        Alert alert = wait.until(alertIsPresent());
        alert.dismiss();
        WebElement resultMessage = driver.findElement(By.id("result"));
        Assert.assertEquals(resultMessage.getText(), "You entered: null");

        clickOnButtonByText("Click for JS Prompt");
        alert.sendKeys(promptMessage);
        alert.accept();
        Assert.assertEquals(resultMessage.getText(), String.format("You entered: %s", promptMessage));
    }

    private void clickOnButtonByText(String text) {
        driver.findElement(By.xpath("//button[text()='%s']".formatted(text))).click();
    }

}
