package com.rentadsearchqa.config;

import com.rentadsearchqa.utils.DriverContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.Duration;

/**
 * Base test class that provides WebDriver setup and teardown functionality.
 * This replaces the pytest fixtures from the Python version.
 */
public class TestBase {
    
    protected WebDriver driver;
    protected String baseUrl;
    
    @BeforeSuite
    public void setupSuite() {
        // Setup WebDriverManager and clear cache
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }
    
    @BeforeMethod
    public void setupBrowser() {
        setupChromeDriver();
        setupBaseUrl();
        navigateToBaseUrl();
        handleCookieConsent();
        setupDriverContext();
    }
    
    /**
     * Setup Chrome browser for testing
     * This replaces the browser fixture from conftest.py
     */
    private void setupChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        
        // Chrome options (equivalent to Python version)
        // chromeOptions.addArguments("--headless"); // Commented out to run in visible mode
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("--disable-extensions");
        
        try {
            // Setup Chrome driver with better error handling
            driver = new ChromeDriver(chromeOptions);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        } catch (Exception e) {
            throw new RuntimeException("Could not start Chrome browser: " + e.getMessage(), e);
        }
    }
    
    /**
     * Setup base URL for testing
     * This sets the base URL for xe.gr website
     */
    private void setupBaseUrl() {
        baseUrl = "https://www.xe.gr/";
    }
    
    /**
     * Navigate to base URL and maximize window
     * This replaces the setup_browser_test fixture from conftest.py
     */
    private void navigateToBaseUrl() {
        driver.get(baseUrl);
        driver.manage().window().maximize();
    }
    
    /**
     * Handle cookie consent dialog by clicking accept button if it appears
     */
    private void handleCookieConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='accept-btn']")));
            acceptButton.click();
            System.out.println("Cookie consent accepted automatically");
        } catch (Exception e) {
            // Cookie consent dialog didn't appear or couldn't be clicked
            System.out.println("Cookie consent dialog not found or already handled: " + e.getMessage());
        }
    }
    
    /**
     * Set the driver in the global context
     * This replaces the driver context setup from conftest.py
     */
    private void setupDriverContext() {
        DriverContext.getInstance().setDriver(driver);
    }
    
    /**
     * Build URL by appending path to base URL
     * 
     * @param path The path to append to base URL
     * @return Complete URL
     */
    public String buildUrl(String path) {
        if (path.startsWith("/")) {
            return baseUrl + path.substring(1);
        }
        return baseUrl + path;
    }
    
    /**
     * Navigate to a specific URL path
     * 
     * @param path The path to navigate to
     */
    public void navigateToPath(String path) {
        String fullUrl = buildUrl(path);
        driver.get(fullUrl);
    }
    
    @AfterMethod
    public void teardownBrowser() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                // Ignore cleanup errors
                System.err.println("Error during driver cleanup: " + e.getMessage());
            }
        }
        DriverContext.getInstance().clearDriver();
    }
    
    @AfterSuite
    public void teardownSuite() {
        // Additional cleanup if needed
    }
}
