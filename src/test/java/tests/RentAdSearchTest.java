package tests;

import com.rentadsearchqa.config.TestBase;
import com.rentadsearchqa.functions.properties;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Map;
/**
 * Test class for rent ad search functionality.
 * 
 * This class contains smoke tests for the xe.gr rental property search feature.
 * It validates the complete search flow including area selection, filtering,
 * and result validation for rental properties in Παγκράτι area.
 */
public class RentAdSearchTest extends TestBase {
    
    /**
     * Smoke test for rental property search in Παγκράτι area.
     * 
     * This test validates the complete rental property search workflow:
     * 1. Searches for rental properties in Παγκράτι area
     * 2. Validates phone information functionality
     * 3. Applies price filter (€200-€700) and size filter (75-150 sq.m.)
     * 4. Validates that all results meet the filter criteria
     * 5. Verifies proper sorting and pagination
     * 
     * Test Criteria:
     * - Location: Παγκράτι (and all related areas from autocomplete)
     * - Property Type: Rental properties
     * - Price Range: €200 - €700
     * - Area Range: 75m² - 150m²
     */
    @Test
    @Epic("Rental Property Search")
    @Feature("Smoke Testing")
    @Story("Daily Production Validation")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Comprehensive smoke test for xe.gr rental property search functionality. " +
                "Validates complete user journey from search to result validation with detailed step reporting.")
    public void testSearchRentAdsInPangrati() {
        properties propertySearch = new properties(driver);
        
        // Execute test steps with detailed Allure reporting
        searchForRentalProperties(propertySearch);
        validatePhoneInfoFunctionality(propertySearch);
        applyFiltersAndValidateResults(propertySearch);
    }
    
    /**
     * Step 1: Search for rental properties in Παγκράτι area
     * Handles autocomplete selection and executes search
     */
    @Step("Search for rental properties in Παγκράτι area")
    private void searchForRentalProperties(properties propertySearch) {
        String searchArea = "Παγκράτι";
        propertySearch.searchRentAdsInArea(searchArea);
    }
    
    /**
     * Step 2: Validate phone information functionality
     * Tests the complete phone info interaction flow
     */
    @Step("Validate phone information functionality")
    private void validatePhoneInfoFunctionality(properties propertySearch) {
        propertySearch.validatePhoneInfoInProperty();
    }
    
    /**
     * Step 3: Apply filters and validate results
     * Applies price and size filters, then validates all results meet criteria
     */
    @Step("Apply price and size filters, then validate all results")
    private void applyFiltersAndValidateResults(properties propertySearch) {
        Map<String, String> priceFilter = Map.of("title", "price", "min", "200", "max", "700");
        Map<String, String> sizeFilter = Map.of("title", "size", "min", "75", "max", "150");
        propertySearch.filterAndValidateResults(Arrays.asList(priceFilter, sizeFilter));
    }
}
