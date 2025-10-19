package com.rentadsearchqa.locators.pages;

import com.rentadsearchqa.locators.components.ResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Results page class that extends ResultsPage and uses only
 * the paths and functionality from ResultsPage via super.
 */
public class PropertyResultsPage extends ResultsPage {

    /**
     * Constructor for PropertyResultsPage
     * 
     * @param driver The WebDriver instance for browser automation
     */
    public PropertyResultsPage(WebDriver driver) {
        super(driver);
    }

    // ===== PATHS AS VARIABLES =====
    private static final String BUTTON_SIZE_FILTER = "//button[@data-testid='size-filter-button']";
    private static final String INPUT_MINIMUM_SIZE = "//input[@data-testid='minimum_size_input']";
    private static final String INPUT_MAXIMUM_SIZE = "//input[@data-testid='maximum_size_input']";
    private static final String BUTTON_SUBMIT_INPUT = "//button[@data-testid='submit-input']";


    // ===== CLICK FUNCTION =====
    /**
     * Click the size filter button to open size filter options
     */
    public void clickSizeFilterButton() {
        findAndClick(BUTTON_SIZE_FILTER);
    }

    /**
     * Click on a property ad price span
     */
    public void clickPropertyAdPriceSpan() {
        findAndClick(spanAdPrice("property"));
    }

    /**
     * Click the submit input button to apply filter changes
     */
    public void clickSubmitInputButton() {
        findAndClick(BUTTON_SUBMIT_INPUT);
    }

    // ===== TYPE FUNCTION =====
    /**
     * Type minimum size value into the minimum size input field
     * 
     * @param size The minimum size value to enter
     */
    public void typeMinimumSize(String size) {
        findAndType(INPUT_MINIMUM_SIZE, size);
    }

    /**
     * Type maximum size value into the maximum size input field
     * 
     * @param size The maximum size value to enter
     */
    public void typeMaximumSize(String size) {
        findAndType(INPUT_MAXIMUM_SIZE, size);
    }

    // ===== FIND FUNCTION =====
    /**
     * Find all property result elements by scrolling to the end of the page
     * to ensure all lazy-loaded content is visible
     * 
     * @return List of WebElements representing property results
     */
    public List<WebElement> findPropertyResults() {
        // Scroll to the end of the page to load all property results
        scrollToEndOfPage();
        return findElements(h3AdResult("property"));
    }

    /**
     * Find all property price elements by scrolling to the end of the page
     * to ensure all lazy-loaded content is visible
     * 
     * @return List of WebElements representing property prices
     */
    public List<WebElement> findPropertyPrices() {
        // Scroll to the end of the page to load all property results
        scrollToEndOfPage();
        return findElements(spanAdPrice("property"));
    }

    /**
     * Find all property image elements by scrolling to the end of the page
     * to ensure all lazy-loaded content is visible
     * 
     * @return List of WebElements representing property image containers
     */
    public List<WebElement> findPropertyImages() {
        // Scroll to the end of the page to load all property results
        scrollToEndOfPage();
        return findElements(divAdImages("property"));
    }
}


        