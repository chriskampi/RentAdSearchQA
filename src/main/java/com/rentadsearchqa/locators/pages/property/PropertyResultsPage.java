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

    // ===== CONSTRUCTOR =====
    public PropertyResultsPage(WebDriver driver) {
        super(driver);
    }

    // ===== PATHS AS VARIABLES =====
    private static final String BUTTON_SIZE_FILTER = "//button[@data-testid='size-filter-button']";
    private static final String INPUT_MINIMUM_SIZE = "//input[@data-testid='minimum_size_input']";
    private static final String INPUT_MAXIMUM_SIZE = "//input[@data-testid='maximum_size_input']";
    private static final String BUTTON_SUBMIT_INPUT = "//button[@data-testid='submit-input']";


    // ===== CLICK FUNCTION =====
    public void clickSizeFilterButton() {
        findAndClick(BUTTON_SIZE_FILTER);
    }

    public void clickPropertyAdPriceSpan() {
        findAndClick(spanAdPrice("property"));
    }

    public void clickSubmitInputButton() {
        findAndClick(BUTTON_SUBMIT_INPUT);
    }

    // ===== TYPE FUNCTION =====
    public void typeMinimumSize(String size) {
        findAndType(INPUT_MINIMUM_SIZE, size);
    }

    public void typeMaximumSize(String size) {
        findAndType(INPUT_MAXIMUM_SIZE, size);
    }

    // ===== FIND FUNCTION =====

    public List<WebElement> findPropertyResults() {
        // Scroll to the end of the page to load all property results
        scrollToEndOfPage();
        return findElements(h3AdResult("property"));
    }

    public List<WebElement> findPropertyPrices() {
        // Scroll to the end of the page to load all property results
        scrollToEndOfPage();
        return findElements(spanAdPrice("property"));
    }

    public List<WebElement> findPropertyImages() {
        // Scroll to the end of the page to load all property results
        scrollToEndOfPage();
        return findElements(divAdImages("property"));
    }
}


        