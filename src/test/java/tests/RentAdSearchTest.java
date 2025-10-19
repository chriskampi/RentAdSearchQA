package tests;

import com.rentadsearchqa.config.TestBase;
import com.rentadsearchqa.functions.properties;
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
    public void testSearchRentAdsInPangrati() {
        // Create properties instance
        properties propertySearch = new properties(driver);
        
        // Define search parameters
        String searchArea = "Παγκράτι";
        Map<String, String> priceFilter = Map.of("title", "price", "min", "200", "max", "700");
        Map<String, String> sizeFilter = Map.of("title", "size", "min", "75", "max", "150");
        
        // Search for rent ads in Παγκράτι
        propertySearch.searchRentAdsInArea(searchArea);
        propertySearch.validatePhoneInfoInProperty();
        propertySearch.filterAndValidateResults(Arrays.asList(priceFilter, sizeFilter));
    }
}
