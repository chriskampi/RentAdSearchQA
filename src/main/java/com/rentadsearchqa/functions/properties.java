package com.rentadsearchqa.functions;

import com.rentadsearchqa.locators.components.SearchPage;
import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * Functions for property search operations
 */
public class properties extends SeleniumActions {
    
    private SearchPage searchPage;
    
    public properties(WebDriver driver) {
        super(driver, 10);
        this.searchPage = new SearchPage(driver);
    }
    
    /**
     * Search for rent ads in a specific area and handle dropdown options
     * @param areaName The area name to search for (e.g., "Παγκράτι")
     */
    public void searchRentAdsInArea(String areaName) {
        // Type the area name in search input
        searchPage.typeSearchInput("area", areaName);
        
        // Get all dropdown options
        List<WebElement> dropdownOptions = searchPage.findDropdownOptions(areaName);
        
        // For each option, check if it's already selected, if not, click it
        for (WebElement option : dropdownOptions) {
            String optionText = option.getText();
            int count = searchPage.countDropdownOptionButtons(optionText);
            
            if (count == 0) {
                searchPage.clickDropdownOption(optionText);
            }
        }
        
        // Click search button
        searchPage.clickSearchButton();
    }
}