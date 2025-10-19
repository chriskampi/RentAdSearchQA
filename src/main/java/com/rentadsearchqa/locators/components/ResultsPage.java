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

    // ===== CONSTRUCTOR =====
    public ResultsPage(WebDriver driver) {
        super(driver, 10);
    }

    // ===== CONSTRUCTORS =====
    public String h3AdResult(String item) {
        return "//h3[contains(@data-testid,'" + item + "-ad-title')]";
    }

    public String spanAdPrice(String item) {
        return "//span[@data-testid='" + item + "-ad-price']";
    }

    public String divAdImages(String item) {
        return "//div[@data-testid='" + item + "-ad-image-container']";
    }

    public void clickOpenSortingButton() {
        findAndClick(BUTTON_OPEN_SORTING);
    }

    public void clickAscendingOptionButton() {
        findAndClick(BUTTON_ASCENDING_OPTION);
    }


    // ===== CLICK FUNCTION =====
    public void clickMoreFiltersButton() {
        findAndClick(BUTTON_MORE_FILTERS);
    }

    public void clickPriceFilterButton() {
        findAndClick(BUTTON_PRICE_FILTER);
    }

    public void clickAdPriceSpan(String item) {
        findAndClick(spanAdPrice(item));
    }

    // ===== TYPE FUNCTION =====
    public void typeMinimumPrice(String price) {
        findAndType(INPUT_MINIMUM_PRICE, price);
    }

    public void typeMaximumPrice(String price) {
        findAndType(INPUT_MAXIMUM_PRICE, price);

    }

    public void clickPageResult(String page) {
        findAndClick(A_PAGE_RESULT + "[" + page + "]");
    }

    public void clickPhoneInfoButton() {
        findAndClick(BUTTON_PHONE_INFO);
    }

    // ===== FIND FUNCTION =====
    public List<WebElement> findPageResults() {
        return findElements(A_PAGE_RESULT);
    }

    public void findPhoneInfo(boolean exists) {
        find(DIV_PHONE_INFO, exists);
    }
}