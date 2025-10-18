# Rent Ad Search QA - Java Version

This is the Java conversion of the Python Selenium testing framework.

## Project Structure

```
src/
├── main/java/com/rentadsearchqa/
│   └── utils/
│       ├── DriverContext.java          # Singleton for WebDriver management
│       └── SeleniumActions.java        # Utility class for Selenium operations
├── test/java/
│   ├── com/rentadsearchqa/config/
│   │   └── TestBase.java               # Base test class with setup/teardown
│   └── tests/
│       └── RentAdSearchTest.java       # Example test class
├── pom.xml                             # Maven configuration
└── testng.xml                          # TestNG suite configuration
```

## Key Features

### 1. DriverContext (Singleton Pattern)
- Manages WebDriver instances globally
- Thread-safe singleton implementation
- Easy access without passing driver as parameter

```java
// Get driver instance
WebDriver driver = DriverContext.getInstance().getDriver();

// Set driver instance
DriverContext.getInstance().setDriver(driver);
```

### 2. SeleniumActions Utility Class
- Common Selenium operations with built-in waiting
- Enhanced error handling
- Type-safe method signatures

```java
SeleniumActions actions = new SeleniumActions(driver, 10);

// Find element
WebElement element = actions.find("//input[@id='search']");

// Click element
actions.findAndClick("//button[@id='submit']");

// Type text
actions.findAndType("//input[@id='search']", "test search");

// Validate element count
String[] expected = {"Option1", "Option2"};
actions.findAndAssertCount("//option", expected);
```

### 3. TestBase Configuration
- Automatic WebDriver setup and teardown
- Chrome browser configuration
- HTML file path setup
- Equivalent to pytest fixtures

### 4. TestNG Integration
- Modern testing framework
- Better reporting capabilities
- Parallel test execution support

## Running Tests

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Chrome browser installed

### Commands

```bash
# Compile the project
mvn compile

# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=RentAdSearchTest

# Generate Allure report
mvn allure:serve
```

## Migration Notes from Python

### Equivalent Concepts

| Python | Java |
|--------|------|
| `conftest.py` fixtures | `TestBase` class with `@BeforeMethod`/`@AfterMethod` |
| `DriverContext` class | Same concept, Java singleton pattern |
| `SeleniumActions` class | Same functionality, Java syntax |
| `pytest.ini` | `testng.xml` + Maven configuration |
| `requirements.txt` | `pom.xml` dependencies |

### Key Differences

1. **Type Safety**: Java provides compile-time type checking
2. **Method Names**: Java uses camelCase (e.g., `sendKeys` vs `send_keys`)
3. **Exception Handling**: Java requires explicit exception handling
4. **Imports**: Java requires explicit imports for all classes
5. **Annotations**: TestNG uses annotations instead of pytest decorators

## Configuration

### Chrome Options
All Chrome options from the Python version are preserved:
- `--no-sandbox`
- `--disable-dev-shm-usage`
- `--disable-gpu`
- `--window-size=1920,1080`
- `--disable-web-security`
- `--allow-running-insecure-content`
- `--disable-extensions`

### Base URL Configuration
The framework is configured to test the live xe.gr website:
```java
String baseUrl = "https://www.xe.gr/";
```

### URL Building
You can build URLs dynamically for different sections:
```java
// Navigate to specific paths
navigateToPath("properties");  // → https://www.xe.gr/properties
navigateToPath("jobs");        // → https://www.xe.gr/jobs
buildUrl("cars");              // → https://www.xe.gr/cars
```

## Benefits of Java Version

1. **Better IDE Support**: IntelliJ IDEA, Eclipse with excellent Selenium support
2. **Type Safety**: Compile-time error detection
3. **Enterprise Ready**: Better suited for large-scale projects
4. **Performance**: Generally faster execution
5. **Rich Ecosystem**: Extensive testing libraries and tools
6. **Maven/Gradle**: Superior dependency management
7. **Better Reporting**: Allure, ExtentReports, etc.

## Next Steps

1. Replace example test methods with your actual test logic
2. Add more test classes as needed
3. Configure Allure reporting for better test reports
4. Add parallel execution configuration if needed
5. Integrate with CI/CD pipelines

## Troubleshooting

### Common Issues

1. **WebDriver not found**: Ensure Chrome browser is installed
2. **Website not accessible**: Check your internet connection and xe.gr availability
3. **Compilation errors**: Ensure Java 11+ is installed and configured
4. **Maven issues**: Ensure Maven is properly installed and configured

### Debug Mode
To run tests in debug mode, uncomment the headless option in `TestBase.java`:
```java
chromeOptions.addArguments("--headless"); // Uncomment for headless mode
```
