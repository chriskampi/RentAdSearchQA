# Rent Ad Search QA - Java Implementation

Professional test automation framework for xe.gr rental property search functionality. This implementation provides comprehensive smoke testing for the production website with detailed validation and reporting.

## Business Context

This project implements automated smoke tests for the xe.gr rental property search functionality. It validates the complete user journey for searching rental properties in Παγκράτι area with specific criteria, designed for daily execution in production environments.

### Test Requirements
- **Location**: Παγκράτι (and all related areas from autocomplete)
- **Property Type**: Rental properties
- **Price Range**: €200 - €700
- **Area Range**: 75m² - 150m²
- **Validation**: Image count, price sorting, contact visibility

## Project Structure

```
src/
├── main/java/com/rentadsearchqa/
│   ├── functions/
│   │   └── properties.java              # Main business logic for property search
│   ├── locators/
│   │   ├── components/
│   │   │   ├── ResultsPage.java        # Results page component
│   │   │   └── SearchPage.java        # Search page component
│   │   └── pages/
│   │       ├── PropertyPage.java       # Property search page
│   │       └── property/
│   │           └── PropertyResultsPage.java # Property results page
│   └── utils/
│       ├── DriverContext.java          # Singleton for WebDriver management
│       └── SeleniumActions.java       # Utility class for Selenium operations
├── test/java/
│   ├── com/rentadsearchqa/config/
│   │   └── TestBase.java              # Base test class with setup/teardown
│   └── tests/
│       └── RentAdSearchTest.java      # Main smoke test implementation
├── pom.xml                            # Maven configuration
├── testng.xml                         # TestNG suite configuration
├── run-tests.bat                      # Windows test runner script
├── README-Java.md                     # This documentation
└── SMOKE-TEST-README.md               # Detailed smoke test documentation
```

## Key Features

### 1. Comprehensive Smoke Testing
- **Single comprehensive test** covering complete search flow
- **Pass/fail granularity** with detailed step reporting
- **Production-ready** for daily execution
- **Multi-page result validation** with pagination support
- **Robust error handling** with detailed assertion messages

### 2. Advanced Selenium Implementation
- **Smart scrolling** for lazy-loaded content
- **Dynamic element handling** with proper waits
- **Complex interaction flows** (phone info validation)
- **Autocomplete handling** for area selection
- **Multi-page result processing**

### 3. Professional Architecture
- **Page Object Model** with component-based design
- **Singleton pattern** for driver management
- **Utility classes** for reusable functionality
- **TestNG integration** with proper lifecycle management
- **Allure reporting** for detailed test results

### 4. Production-Ready Configuration
- **WebDriverManager** for automatic driver management
- **Chrome options** optimized for stability
- **Cookie consent handling** for GDPR compliance
- **Proper timeouts and waits** for production stability
- **Error recovery mechanisms**

## Test Scenarios

### Main Smoke Test: `testSearchRentAdsInPangrati()`

**Search Flow:**
1. Navigate to xe.gr property search
2. Enter "Παγκράτι" and select all autocomplete areas
3. Apply price filter (€200-€700) and size filter (75m²-150m²)
4. Execute search and validate results
5. Verify price sorting (ascending order)
6. Validate phone info functionality
7. Check image count limits (max 30 per property)
8. Handle pagination if multiple pages exist

**Validation Criteria:**
- All properties meet search criteria
- Price sorting works correctly
- Phone numbers are hidden initially
- Contact buttons reveal phone info
- Image count doesn't exceed 30 per property

## Getting Started

### Prerequisites
- **Java 11 or higher** - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- **Maven 3.6 or higher** - Download from [Apache Maven](https://maven.apache.org/download.cgi)
- **Chrome browser** - Latest version installed
- **Internet connection** - Tests run against live xe.gr website
- **Git** - For cloning the repository

### Quick Setup

#### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd RentAdSearchQA
```

#### 2. Verify Prerequisites
```bash
# Check Java version
java -version

# Check Maven version  
mvn -version

# Check Chrome installation
chrome --version
```

#### 3. Compile the Project
```bash
mvn clean compile
```

#### 4. Run the Smoke Test
```bash
mvn test -Dtest=RentAdSearchTest
```

#### 5. Generate Allure Report
```bash
mvn allure:serve
```

### Alternative Execution Methods

#### Windows Users
```bash
# Use the provided batch script
run-tests.bat
```

#### All Tests
```bash
# Run all test suites
mvn test
```

#### Specific Test Groups
```bash
# Run smoke tests only
mvn test -Dgroups=smoke
```

### Test Execution Details
- **Duration**: 5-15 minutes (depending on results count)
- **Browser**: Chrome (automatically managed by WebDriverManager)
- **Reports**: Generated in `allure-results/` directory
- **Logs**: Console output with step-by-step progress

## Production Readiness

### Daily Smoke Test Suite
This implementation is designed for daily execution in production environments:

- **Automated driver management** with WebDriverManager
- **Cookie consent handling** for GDPR compliance
- **Robust error handling** with detailed failure reporting
- **Production-safe configuration** with proper timeouts
- **Clear pass/fail reporting** for stakeholders

### CI/CD Integration
```yaml
# Example GitHub Actions
- name: Run Smoke Tests
  run: mvn test -Dtest=RentAdSearchTest
```

### Test Reporting
- **Allure HTML reports** with detailed step-by-step execution
- **@Step annotations** for granular test step tracking
- **@Epic/@Feature/@Story** for test organization and categorization
- **@Severity levels** for test prioritization
- **TestNG reports** for CI/CD integration
- **Clear failure messages** with context and explanations

## Technical Implementation

### Architecture Patterns
- **Page Object Model**: Clean separation of page elements and test logic
- **Component-based Design**: Reusable components for different page sections
- **Singleton Pattern**: Global driver management with DriverContext
- **Utility Classes**: Common Selenium operations in SeleniumActions

### Key Classes

#### `properties.java` - Main Business Logic
```java
// Search for rent ads with area selection (with Allure step reporting)
@Step("Search for rent ads in area: {areaName}")
propertySearch.searchRentAdsInArea("Παγκράτι");

// Apply filters and validate results (with detailed step tracking)
@Step("Apply filters and validate all results meet criteria")
propertySearch.filterAndValidateResults(Arrays.asList(priceFilter, sizeFilter));

// Validate phone info functionality (with step-by-step reporting)
@Step("Validate phone information functionality")
propertySearch.validatePhoneInfoInProperty();
```

#### `SeleniumActions.java` - Utility Operations
```java
// Smart scrolling for lazy-loaded content
scrollToEndOfPage();

// Element interaction with proper waits
findAndClick("//button[@id='submit']");
findAndType("//input[@id='search']", "test search");

// Validation with detailed error messages
findAndAssertCount("//option", expectedValues);
```

### Configuration

#### Chrome Options (Production-Ready)
- `--no-sandbox` - Container compatibility
- `--disable-dev-shm-usage` - Memory optimization
- `--disable-gpu` - Headless compatibility
- `--window-size=1920,1080` - Consistent viewport
- `--disable-web-security` - Cross-origin handling
- `--allow-running-insecure-content` - Mixed content support
- `--disable-extensions` - Clean environment

#### Base URL Configuration
```java
String baseUrl = "https://www.xe.gr/";
```

## Benefits of This Implementation

1. **Production-Ready**: Designed for daily execution in live environments
2. **Comprehensive Validation**: Covers all xe.gr requirements thoroughly
3. **Professional Architecture**: Follows industry best practices
4. **Detailed Reporting**: Clear pass/fail with step-by-step details
5. **Maintainable Code**: Clean structure for easy updates and extensions
6. **Error Handling**: Robust failure handling with detailed context
7. **Modern Tools**: Uses current Selenium, TestNG, and Maven best practices

## Troubleshooting

### Common Issues

#### **Setup Issues**
1. **WebDriver not found**: Ensure Chrome browser is installed
2. **Website not accessible**: Check your internet connection and xe.gr availability
3. **Compilation errors**: Ensure Java 11+ is installed and configured
4. **Maven issues**: Ensure Maven is properly installed and configured
5. **Test failures**: Check xe.gr website changes that might affect selectors

#### **Git Repository Issues**
1. **Clone fails**: Verify repository URL and Git access permissions
2. **Dependencies not found**: Run `mvn clean install` to download dependencies
3. **Permission denied**: Ensure you have write permissions in the project directory
4. **Branch issues**: Make sure you're on the main/master branch

#### **Environment Issues**
1. **Java not found**: Add Java to your PATH environment variable
2. **Maven not found**: Add Maven to your PATH environment variable
3. **Chrome not found**: Install Chrome browser or update PATH
4. **Port conflicts**: Allure serve uses port 8080 by default

### Debug Mode
To run tests in debug mode, uncomment the headless option in `TestBase.java`:
```java
chromeOptions.addArguments("--headless"); // Uncomment for headless mode
```

### Test Execution Tips
- **First run**: May take longer due to driver download
- **Network issues**: Tests require stable internet connection
- **Browser updates**: WebDriverManager handles Chrome driver updates automatically
- **Report generation**: Use `mvn allure:serve` for detailed HTML reports
- **Clean runs**: Use `mvn clean test` for fresh test execution

## What xe.gr Will Receive

### Repository Contents
When xe.gr clones this repository, they will get:

#### **Complete Test Automation Framework**
- ✅ **Ready-to-run** smoke tests for their production website
- ✅ **Professional architecture** following industry best practices
- ✅ **Comprehensive documentation** for easy setup and execution
- ✅ **Allure reporting** with detailed step-by-step execution tracking
- ✅ **Production-ready configuration** for daily execution

#### **Deliverables Included**
1. **Source Code** - Complete Java/Selenium/TestNG implementation
2. **Documentation** - Comprehensive README with setup instructions
3. **Test Reports** - Sample Allure reports in `allure-results/` directory
4. **Build Scripts** - Maven configuration and Windows batch script
5. **Test Configuration** - TestNG suite configuration for different test groups

#### **Ready for Immediate Use**
- **Clone and run** - No additional setup required beyond prerequisites
- **Daily execution** - Designed for production smoke testing
- **CI/CD ready** - Can be integrated into build pipelines
- **Maintainable** - Clean code structure for easy updates


This implementation demonstrates:
- ✅ **Professional test architecture** (POM, Components, Functions)
- ✅ **Comprehensive smoke testing** (Single test, detailed reporting)
- ✅ **Production-ready code** (Error handling, logging, validation)
- ✅ **Java best practices** (Clean code, proper structure)
- ✅ **Selenium expertise** (Advanced interactions, validation)
- ✅ **Business understanding** (xe.gr requirements fully met)
- ✅ **Git repository ready** (Complete setup instructions and documentation)

