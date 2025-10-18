package com.rentadsearchqa.utils;

import org.openqa.selenium.WebDriver;

/**
 * Global context manager for WebDriver instances.
 * 
 * This class provides a singleton pattern to store and access the current
 * WebDriver instance throughout the application without needing to pass
 * it as a parameter to every function.
 */
public class DriverContext {
    
    private static DriverContext instance;
    private WebDriver driver;
    
    // Private constructor to prevent instantiation
    private DriverContext() {}
    
    /**
     * Get the singleton instance of DriverContext
     * 
     * @return The singleton instance
     */
    public static synchronized DriverContext getInstance() {
        if (instance == null) {
            instance = new DriverContext();
        }
        return instance;
    }
    
    /**
     * Set the current WebDriver instance.
     * 
     * @param driver The WebDriver instance to store
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Get the current WebDriver instance.
     * 
     * @return The current WebDriver instance
     * @throws RuntimeException If no driver has been set
     */
    public WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("No driver has been set. Call setDriver() first.");
        }
        return driver;
    }
    
    /**
     * Clear the current WebDriver instance.
     */
    public void clearDriver() {
        this.driver = null;
    }
    
    /**
     * Check if a driver is currently set.
     * 
     * @return True if a driver is set, False otherwise
     */
    public boolean hasDriver() {
        return driver != null;
    }
}
