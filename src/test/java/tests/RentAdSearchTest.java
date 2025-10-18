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
    
    @Override
    protected void setupDriverContext() {
        super.setupDriverContext();
        WebDriver driver = DriverContext.getInstance().getDriver();
        actions = new SeleniumActions(driver, 10);
    }
    
    @Test(description = "Verify page loads and basic elements are present")
    public void testPageLoads() {
        // Example test demonstrating basic functionality
        // This would be replaced with your actual test logic
        
        // Example: Find and click a button
        // actions.findAndClick("//button[@id='searchButton']");
        
        // Example: Type text into an input field
        // actions.findAndType("//input[@id='searchInput']", "test search");
        
        // Example: Verify elements exist
        // actions.find("//div[@class='results']", true);
        
        // Example: Assert count of elements
        // String[] expectedValues = {"Option1", "Option2", "Option3"};
        // actions.findAndAssertCount("//option", expectedValues);
        
        System.out.println("Test executed successfully - Page loaded and ready for testing");
    }
    
    @Test(description = "Example test with element validation")
    public void testElementValidation() {
        // Example of checking if elements exist without waiting
        boolean elementExists = (Boolean) actions.find("//body", false);
        
        if (elementExists) {
            System.out.println("Page body element found successfully");
        } else {
            throw new AssertionError("Page body element not found");
        }
    }
    
    @Test(description = "Example test with list validation")
    public void testListValidation() {
        // Example of validating a list of elements
        List<String> expectedOptions = Arrays.asList("Option A", "Option B", "Option C");
        
        // This would validate that exactly these options exist and count matches
        // actions.findAndAssertCount("//select[@id='dropdown']//option", expectedOptions);
        
        System.out.println("List validation test completed");
    }
}
