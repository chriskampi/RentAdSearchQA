package com.rentadsearchqa.functions;

import com.rentadsearchqa.locators.pages.PropertyPage;
import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Functions for property search operations
 */
public class properties extends SeleniumActions {
    
    private final PropertyPage propertyPage;
    
    public properties(WebDriver driver) {
        super(driver, 10);
        this.propertyPage = new PropertyPage(driver);
    }
    
    /**
     * Search for rent ads in a specific area and handle dropdown options
     * @param areaName The area name to search for (e.g., "Παγκράτι")
     */
    public void searchRentAdsInArea(String areaName) {
        // Type the area name in search input
        propertyPage.typeAreaSearchInput(areaName);
        
        // Get all dropdown options and collect their texts
        List<WebElement> dropdownOptions = propertyPage.findDropdownOptions();
        List<String> optionTexts = new ArrayList<>();
        
        // Collect all option texts first (to avoid stale elements)
        for (WebElement option : dropdownOptions) {
            optionTexts.add(option.getText());
        }
        
        // For each option text, check if it's already selected, if not, click it
        for (String optionText : optionTexts) {
            int count = propertyPage.countSelectedResultButtons(optionText);
            
            if (count == 0) {
                propertyPage.clickDropdownOption(optionText);
                propertyPage.typeAreaSearchInput(areaName);
            }
        }
        
        // Click search button
        propertyPage.clickSearchButton();
    }
}