package com.rentadsearchqa.config;

import com.rentadsearchqa.utils.DriverContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    protected String htmlFilePath;
    
    @BeforeSuite
    public void setupSuite() {
        // Setup WebDriverManager and clear cache
        WebDriverManager.chromedriver().clearDriverCache().setup();
    }
    
    @BeforeMethod
    public void setupBrowser() {
        setupChromeDriver();
        setupHtmlFilePath();
        navigateToHtmlFile();
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
     * Setup HTML file path for testing
     * This replaces the html_file_path fixture from conftest.py
     */
    private void setupHtmlFilePath() {
        String username = System.getProperty("user.name");
        htmlFilePath = String.format("file:///C:/Users/%s/Downloads/QA%%20Programming%%20Exercise.html", username);
    }
    
    /**
     * Navigate to HTML file and maximize window
     * This replaces the setup_browser_test fixture from conftest.py
     */
    private void navigateToHtmlFile() {
        driver.get(htmlFilePath);
        driver.manage().window().maximize();
    }
    
    /**
     * Set the driver in the global context
     * This replaces the driver context setup from conftest.py
     */
    private void setupDriverContext() {
        DriverContext.getInstance().setDriver(driver);
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
