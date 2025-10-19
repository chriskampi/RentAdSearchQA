package com.rentadsearchqa.locators.components;

import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
/**
 * Results page class that extends SeleniumActions and uses only
 * the paths and functionality from SeleniumActions via super.
 */
public class ResultsPage extends SeleniumActions {

    // ===== PATHS AS VARIABLES =====
    private static final String BUTTON_MORE_FILTERS = "//button[@class='filters-button']";
    private static final String BUTTON_PRICE_FILTER = "//button[@data-testid='price-filter-button']";
    private static final String INPUT_MINIMUM_PRICE = "//input[@data-testid='minimum_price_input']";
    private static final String INPUT_MAXIMUM_PRICE = "//input[@data-testid='maximum_price_input']";

    // ===== CONSTRUCTOR =====
    public ResultsPage(WebDriver driver) {
        super(driver, 10);
    }

    // ===== CLICK FUNCTION =====
    public void clickMoreFiltersButton() {
        findAndClick(BUTTON_MORE_FILTERS);
    }

    public void clickPriceFilterButton() {
        findAndClick(BUTTON_PRICE_FILTER);
    }

    // ===== TYPE FUNCTION =====
    public void typeMinimumPrice(String price) {
        findAndType(INPUT_MINIMUM_PRICE, price);
    }

    public void typeMaximumPrice(String price) {
        findAndType(INPUT_MAXIMUM_PRICE, price);

    }

}