package com.rentadsearchqa.locators.components;

import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Example component class showing paths as variables and basic functions
 * based on SeleniumActions utility.
 */
public class SearchPage {
    
    private final SeleniumActions actions;
    
    // ===== PATHS AS VARIABLES =====
    private static final String SEARCH_INPUT = "//input[@id='search']";
    private static final String SEARCH_BUTTON = "//button[@type='submit']";
    private static final String RESULTS_CONTAINER = "//div[@class='results']";
    
    // ===== CONSTRUCTOR =====
    public SearchPage(WebDriver driver) {
        this.actions = new SeleniumActions(driver, 10);
    }
    
    // ===== FIND FUNCTION =====
    public WebElement find(String xpath) {
        return actions.find(xpath);
    }
    
    // ===== CLICK FUNCTION =====
    public void click(String xpath) {
        actions.findAndClick(xpath);
    }
    
    // ===== TYPE FUNCTION =====
    public void type(String xpath, String text) {
        actions.findAndType(xpath, text);
    }
    
    
}
