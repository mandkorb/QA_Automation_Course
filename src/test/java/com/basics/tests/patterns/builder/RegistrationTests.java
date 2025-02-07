package com.basics.tests.patterns.builder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class RegistrationTests {
    private WebDriver driver;
    private RegistrationPage registrationPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver(); // Або через DriverFactory
        registrationPage = new RegistrationPage(driver);
        driver.get("https://yourapp.com/register");
    }

    @Test
    public void testUserRegistration() {
        UserBuilder testUser = UserBuilder.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .password("SecurePass123")
                .build();

        registrationPage.register(testUser);
        assertTrue(registrationPage.isRegistrationSuccessful(), "Реєстрація не вдалася");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
