package tests;

import com.rentadsearchqa.config.TestBase;
import com.rentadsearchqa.functions.properties;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.Map;
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
        propertySearch.filterAndValidateResults(Arrays.asList(
            Map.of("title", "price", "min", "200", "max", "700"),
            Map.of("title", "size", "min", "75", "max", "150")
        ));
    }
}
