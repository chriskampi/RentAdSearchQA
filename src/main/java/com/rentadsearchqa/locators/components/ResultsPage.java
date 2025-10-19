package com.rentadsearchqa.locators.components;

import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
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
    private static final String A_PAGE_RESULT = "//a[@aria-current='page']";
    private static final String BUTTON_OPEN_SORTING = "//button[contains(@data-testid,'sorting-dropdown')]";
    private static final String BUTTON_ASCENDING_OPTION = "//button[@data-id='price_asc']";
    private static final String DIV_PHONE_INFO = "//div[@data-testid='phones']";
    private static final String BUTTON_PHONE_INFO = "//button[@data-testid='call-action-button']";

    /**
     * Constructor for ResultsPage component
     * 
     * @param driver The WebDriver instance for browser automation
     */
    public ResultsPage(WebDriver driver) {
        super(driver, 10);
    }

    // ===== CONSTRUCTORS =====
    /**
     * Construct XPath for ad result heading based on item type
     * 
     * @param item The type of item (e.g., "property")
     * @return Complete XPath for the ad result heading
     */
    public String h3AdResult(String item) {
        return "//h3[contains(@data-testid,'" + item + "-ad-title')]";
    }

    /**
     * Construct XPath for ad price span based on item type
     * 
     * @param item The type of item (e.g., "property")
     * @return Complete XPath for the ad price span
     */
    public String spanAdPrice(String item) {
        return "//span[@data-testid='" + item + "-ad-price']";
    }

    /**
     * Construct XPath for ad images container based on item type
     * 
     * @param item The type of item (e.g., "property")
     * @return Complete XPath for the ad images container
     */
    public String divAdImages(String item) {
        return "//div[@data-testid='" + item + "-ad-image-container']";
    }

    /**
     * Click the sorting dropdown button to open sorting options
     */
    public void clickOpenSortingButton() {
        findAndClick(BUTTON_OPEN_SORTING);
    }

    /**
     * Click the ascending option button to sort results in ascending order
     */
    public void clickAscendingOptionButton() {
        findAndClick(BUTTON_ASCENDING_OPTION);
    }


    // ===== CLICK FUNCTION =====
    /**
     * Click the more filters button to open additional filter options
     */
    public void clickMoreFiltersButton() {
        findAndClick(BUTTON_MORE_FILTERS);
    }

    /**
     * Click the price filter button to open price filter options
     */
    public void clickPriceFilterButton() {
        findAndClick(BUTTON_PRICE_FILTER);
    }

    /**
     * Click on an ad price span for a specific item
     * 
     * @param item The type of item (e.g., "property")
     */
    public void clickAdPriceSpan(String item) {
        findAndClick(spanAdPrice(item));
    }

    // ===== TYPE FUNCTION =====
    /**
     * Type minimum price value into the minimum price input field
     * 
     * @param price The minimum price value to enter
     */
    public void typeMinimumPrice(String price) {
        findAndType(INPUT_MINIMUM_PRICE, price);
    }

    /**
     * Type maximum price value into the maximum price input field
     * 
     * @param price The maximum price value to enter
     */
    public void typeMaximumPrice(String price) {
        findAndType(INPUT_MAXIMUM_PRICE, price);

    }

    /**
     * Click on a specific page result number
     * 
     * @param page The page number to navigate to
     */
    public void clickPageResult(String page) {
        findAndClick(A_PAGE_RESULT + "[" + page + "]");
    }

    /**
     * Click the phone info button to show contact information
     */
    public void clickPhoneInfoButton() {
        findAndClick(BUTTON_PHONE_INFO);
    }

    // ===== FIND FUNCTION =====
    /**
     * Find all page result elements (pagination)
     * 
     * @return List of WebElements representing page numbers
     */
    public List<WebElement> findPageResults() {
        return findElements(A_PAGE_RESULT);
    }

    /**
     * Find phone info element and validate its existence
     * 
     * @param exists If true, validates element exists; if false, validates element does not exist
     */
    public void findPhoneInfo(boolean exists) {
        find(DIV_PHONE_INFO, exists);
    }
}