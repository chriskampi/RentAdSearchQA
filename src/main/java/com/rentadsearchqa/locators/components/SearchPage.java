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

    // ===== CONSTRUCTOR =====
    public SearchPage(WebDriver driver) {
        super(driver, 10);
    }

    // ===== CONSTRUCTORS =====
    private String constructSearchInput(String searchType) {
        return INPUT_SEARCH_BASE_BAR + "[contains(@data-testid,'" + searchType + "')]";
    }
    
    private String constructDropdownButton(String dropdownType) {
        return BUTTON_DROPDOWN + "[contains(@data-testid,'" + dropdownType + "')]";
    }
    
    private String constructTabButton(String tabType) {
        return BUTTON_TAB + "[contains(@data-testid,'" + tabType + "')]";
    }

    private String constructDropdownOptionButton(String text) {
        return BUTTON_DROPDOWN_OPTION + "[.='" + text + "']";
    }

    private String constructSelectedResultButton(String text) {
        return BUTTON_SELECTED_RESULT + "[.='" + text + "']";
    }
    
    // ===== CLICK FUNCTION =====
    public void clickSearchButton() {
        findAndClick(INPUT_SEARCH);
    }

    public void clickDropdownOption(String text) {
        findAndClick(constructDropdownOptionButton(text));
    }
    
    
    // ===== TYPE FUNCTION =====
    public void typeSearchInput(String searchType, String text) {
        findAndType(constructSearchInput(searchType), text);
    }

    // ===== COUNT FUNCTION =====

    public int countDropdownOptionButtons(String text) {
        return countElements(constructDropdownOptionButton(text));
    }

    public int countSelectedResultButtons(String text) {
        return countElements(constructSelectedResultButton(text));
    }
    
    public List<WebElement> findDropdownOptions() {
        return findElements(BUTTON_DROPDOWN_OPTION);
    }
    
}
