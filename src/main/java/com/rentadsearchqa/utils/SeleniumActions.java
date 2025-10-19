package com.rentadsearchqa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.util.List;

/**
 * Utility class for common Selenium actions with enhanced error handling and waiting mechanisms.
 */
public class SeleniumActions {
    
    private final WebDriver driver;
    private final int waitSeconds;
    
    /**
     * Constructor for SeleniumActions
     * 
     * @param driver The WebDriver instance
     * @param waitSeconds Default wait time in seconds for element operations
     */
    public SeleniumActions(WebDriver driver, int waitSeconds) {
        this.driver = driver;
        this.waitSeconds = waitSeconds;
    }
    
    /**
     * Constructor with default wait time of 10 seconds
     * 
     * @param driver The WebDriver instance
     */
    public SeleniumActions(WebDriver driver) {
        this(driver, 10);
    }
    
    /**
     * Open a URL in the browser
     * 
     * @param url The URL to navigate to
     */
    public void openUrl(String url) {
        driver.get(url);
    }
    
    /**
     * Find an element by XPath with optional existence validation
     * 
     * @param xpath The XPath to find
     * @param exists If true, return the element. If false, just validate existence and return true/false
     * @return WebElement if exists=true, boolean if exists=false
     */
    public Object find(String xpath, boolean exists) {
        if (exists) {
            // Original behavior - wait for element and return it
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return element;
        } else {
            // Just check if element exists without waiting
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0)); // No wait
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
    
    /**
     * Find an element by XPath (default behavior - returns the element)
     * 
     * @param xpath The XPath to find
     * @return WebElement
     */
    public WebElement find(String xpath) {
        return (WebElement) find(xpath, true);
    }
    
    /**
     * After validating the existence of the xpath, click it
     * 
     * @param xpath The XPath of the element to click
     */
    public void findAndClick(String xpath) {
        WebElement element = find(xpath);
        element.click();
    }
    
    /**
     * After validating the existence of the xpath, send keys to it
     * 
     * @param xpath The XPath of the element to type into
     * @param text The text to send
     */
    public void findAndType(String xpath, String text) {
        WebElement element = find(xpath);
        element.clear();
        try {
            Thread.sleep(300); // Small delay to ensure clear operation completes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        element.sendKeys(text);
    }
    
    /**
     * Find elements using xpath pattern and assert that the count matches the expected list length.
     * Validates each expected value exists using the xpath pattern,
     * and counts all elements that match the xpath pattern.
     * 
     * @param xpathPattern The XPath pattern to find elements (e.g., "//option[text()='{}']")
     * @param expectedValues Array of expected values to validate and count against
     * @throws AssertionError If the number of found elements doesn't match the expected array length
     */
    public void findAndAssertCount(String xpathPattern, String[] expectedValues) {
        // Validate each expected value exists using the xpath pattern
        for (String value : expectedValues) {
            String path = xpathPattern + "[contains(.,'" + value + "')]";
            find(path, true);
        }
        
        // Count all elements that match the xpath pattern
        List<WebElement> elements = driver.findElements(By.xpath(xpathPattern));
        int foundCount = elements.size();
        
        int expectedCount = expectedValues.length;
        
        if (foundCount != expectedCount) {
            throw new AssertionError(
                String.format("Expected %d elements but found %d elements", 
                             expectedCount, foundCount)
            );
        }
    }
    
    /**
     * Find elements using xpath pattern and assert that the count matches the expected list length.
     * This is an overloaded method that accepts a List<String> instead of String array.
     * 
     * @param xpathPattern The XPath pattern to find elements
     * @param expectedValues List of expected values to validate and count against
     * @throws AssertionError If the number of found elements doesn't match the expected list length
     */
    public void findAndAssertCount(String xpathPattern, List<String> expectedValues) {
        String[] expectedArray = expectedValues.toArray(new String[0]);
        findAndAssertCount(xpathPattern, expectedArray);
    }

    
    /**
     * Count the number of elements matching the given XPath pattern
     * 
     * @param xpathPattern The XPath pattern to search for
     * @return The number of elements found
     */
    public int countElements(String xpathPattern) {
        return driver.findElements(By.xpath(xpathPattern)).size();
    }
    
    /**
     * Find all elements matching the given XPath pattern
     * 
     * @param xpathPattern The XPath pattern to search for
     * @return List of WebElements matching the pattern
     */
    public List<WebElement> findElements(String xpathPattern) {
        return driver.findElements(By.xpath(xpathPattern));
    }
    
    /**
     * Scroll to the end of the page step by step to load all content progressively.
     * This method scrolls down in increments of 1000 pixels, waiting for content to load,
     * until no new content is loaded. Useful for lazy-loaded pages.
     */
    public void scrollToEndOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        // Get initial page height
        Long lastHeight = (Long) js.executeScript("return document.body.scrollHeight");
        
        while (true) {
            // Scroll down by 1000 pixels at a time
            js.executeScript("window.scrollBy(0, 1000);");
            
            // Wait for content to load
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            
            // Get new page height after scrolling
            Long newHeight = (Long) js.executeScript("return document.body.scrollHeight");
            
            // If page height hasn't changed, we've reached the end
            if (newHeight.equals(lastHeight)) {
                break;
            }
            
            lastHeight = newHeight;
        }
        
        // Final scroll to absolute bottom
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        // Final wait to ensure all content is loaded
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Scroll to the start of the page (leftmost position) step by step.
     * This method scrolls left in increments of 1000 pixels until reaching the beginning.
     * Useful for horizontal scrolling scenarios.
     */
    public void scrollToStartOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        // Get initial page height
        Long lastLeft = (Long) js.executeScript("return document.body.scrollLeft");
        
        while (true) {
            // Scroll down by 1000 pixels at a time
            js.executeScript("window.scrollBy(-1000, 0);");
            
            // Wait for content to load
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            
            // Get new page height after scrolling
            Long newLeft = (Long) js.executeScript("return document.body.scrollLeft");
            
            // If page height hasn't changed, we've reached the end
            if (newLeft.equals(lastLeft)) {
                break;
            }
            
            lastLeft = newLeft;
        }
        
        // Final scroll to absolute bottom
        js.executeScript("window.scrollTo(0, document.body.scrollLeft);");
    }

    /**
     * Send the Escape key to the browser.
     * Useful for closing dialogs, dropdowns, or canceling operations.
     */
    public void sendkeyEscape() {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();
    }
}
