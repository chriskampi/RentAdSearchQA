package com.rentadsearchqa.locators.components;

import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;

/**
 * Example component class showing paths as variables and basic functions
 * based on SeleniumActions utility.
 */
public class SearchPage extends SeleniumActions {
    
    // ===== PATHS AS VARIABLES =====
    private static final String INPUT_SEARCH_BASE_BAR = "//input[@type='text']";
    private static final String INPUT_SEARCH = "//input[@type='submit']";
    private static final String BUTTON_DROPDOWN = "//button[contains(@data-testid,'dropdown')]";
    private static final String BUTTON_TAB = "//button[contains(@data-testid,'tab')]";
    private static final String BUTTON_SELECTED_RESULT = "//button[@class='area-tag-button']";
    private static final String BUTTON_DROPDOWN_OPTION = "//button[contains(@data-testid,'dropdown_option')]";

    /**
     * Constructor for SearchPage component
     * 
     * @param driver The WebDriver instance for browser automation
     */
    public SearchPage(WebDriver driver) {
        super(driver, 10);
    }

    // ===== CONSTRUCTORS =====
    /**
     * Construct XPath for search input field based on search type
     * 
     * @param searchType The type of search input (e.g., "area", "property")
     * @return Complete XPath for the search input field
     */
    private String constructSearchInput(String searchType) {
        return INPUT_SEARCH_BASE_BAR + "[contains(@data-testid,'" + searchType + "')]";
    }
    
    /**
     * Construct XPath for dropdown button based on dropdown type
     * 
     * @param dropdownType The type of dropdown button
     * @return Complete XPath for the dropdown button
     */
    private String constructDropdownButton(String dropdownType) {
        return BUTTON_DROPDOWN + "[contains(@data-testid,'" + dropdownType + "')]";
    }
    
    /**
     * Construct XPath for tab button based on tab type
     * 
     * @param tabType The type of tab button
     * @return Complete XPath for the tab button
     */
    private String constructTabButton(String tabType) {
        return BUTTON_TAB + "[contains(@data-testid,'" + tabType + "')]";
    }

    /**
     * Construct XPath for dropdown option button with specific text
     * 
     * @param text The text content of the dropdown option
     * @return Complete XPath for the dropdown option button
     */
    private String constructDropdownOptionButton(String text) {
        return BUTTON_DROPDOWN_OPTION + "[.='" + text + "']";
    }

    /**
     * Construct XPath for selected result button with specific text
     * 
     * @param text The text content of the selected result
     * @return Complete XPath for the selected result button
     */
    private String constructSelectedResultButton(String text) {
        return BUTTON_SELECTED_RESULT + "[.='" + text + "']";
    }
    
    // ===== CLICK FUNCTION =====
    /**
     * Click the search button to execute the search
     */
    public void clickSearchButton() {
        findAndClick(INPUT_SEARCH);
    }

    /**
     * Click a dropdown option button with the specified text
     * 
     * @param text The text content of the dropdown option to click
     */
    public void clickDropdownOption(String text) {
        findAndClick(constructDropdownOptionButton(text));
    }
    
    
    // ===== TYPE FUNCTION =====
    /**
     * Type text into a search input field based on search type
     * 
     * @param searchType The type of search input (e.g., "area", "property")
     * @param text The text to type into the input field
     */
    public void typeSearchInput(String searchType, String text) {
        findAndType(constructSearchInput(searchType), text);
    }

    // ===== COUNT FUNCTION =====
    /**
     * Count dropdown option buttons with specific text
     * 
     * @param text The text content to search for
     * @return Number of dropdown option buttons found
     */
    public int countDropdownOptionButtons(String text) {
        return countElements(constructDropdownOptionButton(text));
    }

    /**
     * Count selected result buttons with specific text
     * 
     * @param text The text content to search for
     * @return Number of selected result buttons found
     */
    public int countSelectedResultButtons(String text) {
        return countElements(constructSelectedResultButton(text));
    }
    
    /**
     * Find all dropdown option elements
     * 
     * @return List of WebElements representing dropdown options
     */
    public List<WebElement> findDropdownOptions() {
        return findElements(BUTTON_DROPDOWN_OPTION);
    }
    
}
