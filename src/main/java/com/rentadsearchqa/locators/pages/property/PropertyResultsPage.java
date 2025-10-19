package com.rentadsearchqa.locators.pages;

import com.rentadsearchqa.locators.components.ResultsPage;
import org.openqa.selenium.WebDriver;

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
}


        