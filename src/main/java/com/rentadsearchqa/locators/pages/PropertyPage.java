package com.rentadsearchqa.locators.pages;

import com.rentadsearchqa.locators.components.SearchPage;
import org.openqa.selenium.WebDriver;

/**
 * Property page class that extends SearchPage and uses only
 * the paths and functionality from SearchPage via super.
 */
public class PropertyPage extends SearchPage {

    // ===== CONSTRUCTOR =====
    public PropertyPage(WebDriver driver) {
        super(driver);
    }

    // ===== TYPE FUNCTION =====
    public void typeAreaSearchInput(String text) {
        typeSearchInput("area", text);
    }

}
