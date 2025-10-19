package com.rentadsearchqa.functions;

import com.rentadsearchqa.locators.pages.PropertyPage;
import com.rentadsearchqa.locators.pages.PropertyResultsPage;
import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Functions for property search operations
 */
public class properties extends SeleniumActions {
    
    private final PropertyPage propertyPage;
    private final PropertyResultsPage propertyResultsPage;
    /**
     * Constructor for properties class
     * 
     * @param driver The WebDriver instance for browser automation
     */
    public properties(WebDriver driver) {
        super(driver, 10);
        this.propertyPage = new PropertyPage(driver);
        this.propertyResultsPage = new PropertyResultsPage(driver);
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

    /**
     * Apply filters to property search results and validate that all results meet the filter criteria.
     * Supports price and size filters with validation of:
     * - Price range validation (min-max)
     * - Size range validation (min-max) 
     * - Image count validation (max 30 per property)
     * - Price sorting validation (ascending order)
     * - Multi-page result handling
     * 
     * @param filters List of filter maps containing:
     *                - "title": filter type ("price" or "size")
     *                - "min": minimum value for the filter
     *                - "max": maximum value for the filter
     * @throws AssertionError If any property doesn't meet the filter criteria
     */
    public void filterAndValidateResults(List<Map<String, String>> filters) {
        // Apply all filters first
        for (Map<String, String> filter : filters) {
            if (filter.get("title").equals("price")) {
                propertyResultsPage.clickPriceFilterButton();
                propertyResultsPage.typeMinimumPrice(filter.get("min"));
                propertyResultsPage.typeMaximumPrice(filter.get("max"));
                propertyResultsPage.clickSubmitInputButton();
            } else if (filter.get("title").equals("size")) {
                propertyResultsPage.clickSizeFilterButton();
                propertyResultsPage.typeMinimumSize(filter.get("min"));
                propertyResultsPage.typeMaximumSize(filter.get("max"));
                propertyResultsPage.clickSubmitInputButton();
            }
        }
        propertyResultsPage.clickOpenSortingButton();
        propertyResultsPage.clickAscendingOptionButton();
        // Check if there are multiple pages
        List<WebElement> pageResults = propertyResultsPage.findPageResults();
        int totalPages = pageResults.size();
        
        // Validate all pages
        for (int page = 1; page <= totalPages; page++) {
            // Validate image count for each property (regardless of filters)
            List<WebElement> propertyImages = propertyResultsPage.findPropertyImages();
            for (int i = 0; i < propertyImages.size(); i++) {
                String imageXpath = "(//div[@data-testid='property-ad-image-container'])[" + (i + 1) + "]//img";
                List<WebElement> images = propertyResultsPage.findElements(imageXpath);
                String pageInfo = totalPages > 1 ? " on page " + page : "";
                assert images.size() <= 30 : 
                    "Property " + (i + 1) + " has " + images.size() + " images, which exceeds the limit of 30" + pageInfo;
            }
            
            // Validate results on current page for all applicable filters
            for (Map<String, String> filter : filters) {
                if (filter.get("title").equals("price")) {
                    List<WebElement> propertyPrices = propertyResultsPage.findPropertyPrices();
                    Pattern pricePattern = Pattern.compile("(\\d+)\\s*€");
                    int minPrice = Integer.parseInt(filter.get("min"));
                    int maxPrice = Integer.parseInt(filter.get("max"));
                    
                    // Extract and validate prices
                    List<Integer> prices = new ArrayList<>();
                    for (WebElement propertyPrice : propertyPrices) {
                        String propertyPriceText = propertyPrice.getText();
                        Matcher matcher = pricePattern.matcher(propertyPriceText);
                        if (matcher.find()) {
                            int propertyPriceValue = Integer.parseInt(matcher.group(1));
                            prices.add(propertyPriceValue);
                            String pageInfo = totalPages > 1 ? " on page " + page : "";
                            assert propertyPriceValue >= minPrice && propertyPriceValue <= maxPrice : 
                                "Property price " + propertyPriceValue + " not in range " + minPrice + "-" + maxPrice
                                        + pageInfo;
                        }
                    }
                    
                    // Assert prices are sorted in ascending order
                    for (int i = 1; i < prices.size(); i++) {
                        String pageInfo = totalPages > 1 ? " on page " + page : "";
                        assert prices.get(i) >= prices.get(i - 1) : 
                            "Property prices are not sorted ascending. Price " + prices.get(i) + 
                            " is less than previous price " + prices.get(i - 1) + pageInfo;
                    }
                }
                if (filter.get("title").equals("size")) {
                    List<WebElement> propertyResults = propertyResultsPage.findPropertyResults();
                    Pattern sizePattern = Pattern.compile("(\\d+)\\s*τ\\.μ\\.");
                    int minSize = Integer.parseInt(filter.get("min"));
                    int maxSize = Integer.parseInt(filter.get("max"));
                    
                    for (WebElement property : propertyResults) {
                        String propertyText = property.getText();
                        Matcher matcher = sizePattern.matcher(propertyText);
                        if (matcher.find()) {
                            int propertySize = Integer.parseInt(matcher.group(1));
                            String pageInfo = totalPages > 1 ? " on page " + page : "";
                            assert propertySize >= minSize && propertySize <= maxSize : 
                                "Property size " + propertySize + " not in range " + minSize + "-" + maxSize + pageInfo;
                        }
                    }
                }
            }
            
            // Click to next page (only if not the last page)
            if (page < totalPages) {
                propertyResultsPage.clickPageResult(String.valueOf(page + 1));
            }
        }
    }

    /**
     * Validates phone information visibility in property listings.
     * Tests the phone info functionality by:
     * 1. Verifying phone info is initially hidden
     * 2. Clicking on property ad price to reveal phone info
     * 3. Clicking phone info button to show contact details
     * 4. Verifying phone info is now visible
     * 5. Closing phone info dialog using escape key
     * 6. Verifying phone info is hidden again
     * 
     * This method tests the complete phone info interaction flow.
     */
    public void validatePhoneInfoInProperty() {
        propertyResultsPage.findPhoneInfo(false);
        propertyResultsPage.clickPropertyAdPriceSpan();
        propertyResultsPage.clickPhoneInfoButton();
        propertyResultsPage.findPhoneInfo(true);
        sendkeyEscape();
        propertyResultsPage.findPhoneInfo(false);
        sendkeyEscape();
    }

}