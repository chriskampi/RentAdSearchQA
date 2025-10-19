package com.rentadsearchqa.locators.pages;

import com.rentadsearchqa.locators.components.SearchPage;
import org.openqa.selenium.WebDriver;

/**
 * Property page class that extends SearchPage and uses only
 * the paths and functionality from SearchPage via super.
 */
public class PropertyPage extends SearchPage {

    /**
     * Constructor for PropertyPage
     * 
     * @param driver The WebDriver instance for browser automation
     */
    public PropertyPage(WebDriver driver) {
        super(driver);
    }

    // ===== TYPE FUNCTION =====
    /**
     * Type text into the area search input field
     * 
     * @param text The area name to search for (e.g., "Παγκράτι")
     */
    public void typeAreaSearchInput(String text) {
        typeSearchInput("area", text);
    }

}
