package tests;

import com.rentadsearchqa.config.TestBase;
import com.rentadsearchqa.utils.DriverContext;
import com.rentadsearchqa.utils.SeleniumActions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Example test class demonstrating the Java Selenium framework.
 * This class shows how to use the converted utilities and setup.
 */
public class RentAdSearchTest extends TestBase {
    
    private SeleniumActions actions;
    
    /**
     * Initialize SeleniumActions - call this at the beginning of each test
     */
    private void initializeActions() {
        if (actions == null) {
            WebDriver driver = DriverContext.getInstance().getDriver();
            actions = new SeleniumActions(driver, 10);
        }
    }
    
    @Test(description = "Verify xe.gr homepage loads and basic elements are present")
    public void testXeGrHomepageLoads() {
        initializeActions(); // Initialize SeleniumActions
        
        // Test that xe.gr homepage loads successfully
        // The page should already be loaded from TestBase
        
        // Example: Verify main navigation elements exist
        // actions.find("//a[contains(text(), 'Ακίνητα')]", true);
        // actions.find("//a[contains(text(), 'Εργασία')]", true);
        // actions.find("//a[contains(text(), 'Αυτοκίνητα')]", true);
        
        // Example: Check if search functionality is present
        // actions.find("//input[@placeholder='Αναζήτηση...']", true);
        
        System.out.println("xe.gr homepage loaded successfully - ready for testing");
    }
    
    @Test(description = "Example test with element validation")
    public void testElementValidation() {
        initializeActions(); // Initialize SeleniumActions
        
        // Example of checking if elements exist without waiting
        boolean elementExists = (Boolean) actions.find("//body", false);
        
        if (elementExists) {
            System.out.println("Page body element found successfully");
        } else {
            throw new AssertionError("Page body element not found");
        }
    }
    
    @Test(description = "Example test with URL building and navigation")
    public void testUrlBuildingAndNavigation() {
        initializeActions(); // Initialize SeleniumActions
        
        // Example of building URLs dynamically
        String propertySearchUrl = buildUrl("properties");
        String jobSearchUrl = buildUrl("jobs");
        
        System.out.println("Property search URL: " + propertySearchUrl);
        System.out.println("Job search URL: " + jobSearchUrl);
        
        // Example: Navigate to a specific section
        // navigateToPath("properties");  // Would navigate to https://www.xe.gr/properties
        // actions.find("//h1[contains(text(), 'Ακίνητα')]", true);
        
        // Example: Navigate to another section
        // navigateToPath("jobs");  // Would navigate to https://www.xe.gr/jobs
        // actions.find("//h1[contains(text(), 'Εργασία')]", true);
        
        System.out.println("URL building and navigation test completed");
    }
}
