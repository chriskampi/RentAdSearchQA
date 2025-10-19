package tests;

import com.rentadsearchqa.config.TestBase;
import com.rentadsearchqa.functions.properties;
import org.testng.annotations.Test;

/**
 * Test class for rent ad search functionality
 */
public class RentAdSearchTest extends TestBase {
    
    @Test
    public void testSearchRentAdsInPangrati() {
        // Create properties instance
        properties propertySearch = new properties(driver);
        
        // Search for rent ads in Παγκράτι
        propertySearch.searchRentAdsInArea("Παγκράτι");
    }
}
