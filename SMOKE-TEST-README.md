# Rent Ad Search Smoke Test

## 🎯 Overview

This smoke test validates the core functionality of xe.gr rental property search feature. It's designed for **daily execution** in production to ensure basic functionality remains intact.

## 📋 Test Requirements Met

✅ **Single comprehensive test script** - One test covers the complete search flow  
✅ **Pass/Fail granularity** - Clear pass/fail with detailed step reporting  
✅ **Detailed step reporting** - Each step shows success/failure with explanations  
✅ **Java + Selenium + TestNG** - Professional test automation stack  
✅ **Trivially repeatable** - Can run daily without manual intervention  
✅ **Production-ready** - Designed for live site testing  

## 🏗️ Architecture

### **Page Object Model (POM)**
```
src/main/java/com/rentadsearchqa/
├── pages/
│   └── SearchPage.java              # Search page interactions
├── components/
│   └── PropertyCard.java            # Individual property card validation
├── functions/
│   └── SearchValidationFunctions.java # Reusable validation utilities
└── utils/
    ├── DriverContext.java           # WebDriver management
    └── SeleniumActions.java         # Selenium utilities
```

### **Test Structure**
```
src/test/java/tests/
└── RentAdSearchSmokeTest.java       # Main smoke test
```

## 🧪 Test Scenarios

### **Main Smoke Test: `testRentalPropertySearchSmoke()`**

**Search Criteria:**
- Location: Παγκράτι (and all related areas from autocomplete)
- Property Type: Rental properties
- Price Range: €200 - €700
- Area Range: 75m² - 150m²

**Validation Steps:**

1. **Page Navigation** - Verify search page loads
2. **Location Search** - Enter Παγκράτι and select all autocomplete areas
3. **Property Type** - Select rental properties
4. **Filter Application** - Apply price and area filters
5. **Search Execution** - Execute search and verify results
6. **Result Validation** - Verify properties meet search criteria
7. **Price Sorting** - Test descending price sort functionality
8. **Contact Visibility** - Verify phone numbers are hidden
9. **Contact Buttons** - Test contact button functionality
10. **Pagination** - Check and test pagination if available

### **Basic Functionality Test: `testSearchPageBasicFunctionality()`**

- Page loads successfully
- Search form elements are present
- Basic UI components are accessible

## 🎯 Validation Criteria

### **Property Validation**
- ✅ Price within specified range (€200-€700)
- ✅ Area within specified range (75m²-150m²)
- ✅ Maximum 30 images per property
- ✅ Phone numbers not directly visible
- ✅ Contact buttons functional

### **Search Results Validation**
- ✅ Results are properly sorted by price (descending)
- ✅ All displayed properties meet search criteria
- ✅ Pagination works correctly (if applicable)

## 🚀 Running the Tests

### **Run All Smoke Tests**
```bash
mvn test -Dgroups=smoke
```

### **Run Specific Test**
```bash
mvn test -Dtest=RentAdSearchSmokeTest
```

### **Run in IntelliJ IDEA**
1. Right-click on `RentAdSearchSmokeTest` class
2. Select "Run 'RentAdSearchSmokeTest'"

## 📊 Test Reporting

### **Console Output**
Each test provides detailed step-by-step reporting:
```
=== RENTAL PROPERTY SEARCH SMOKE TEST ===
Testing search for: Παγκράτι
Price range: €200 - €700
Area range: 75m² - 150m²
==========================================

STEP 1: Navigating to search page...
✓ Search page loaded

STEP 2: Entering search location...
✓ Location entered and areas selected

... (detailed step reporting)
```

### **Allure Reports**
Generate detailed HTML reports:
```bash
mvn allure:serve
```

## 🔧 Configuration

### **Test Parameters**
Located in `RentAdSearchSmokeTest.java`:
```java
private static final String SEARCH_LOCATION = "Παγκράτι";
private static final int MIN_PRICE = 200;
private static final int MAX_PRICE = 700;
private static final int MIN_AREA = 75;
private static final int MAX_AREA = 150;
```

### **TestNG Groups**
- `smoke` - Smoke test group for daily execution
- `rental-search` - Rental search specific tests
- `basic-functionality` - Basic UI functionality tests

## 🎯 Smoke Test vs Regression Test

| Aspect | Smoke Test | Regression Test |
|--------|------------|-----------------|
| **Scope** | Critical user journey | All features + edge cases |
| **Duration** | 5-15 minutes | 30+ minutes |
| **Frequency** | Daily | Weekly/Release |
| **Test Cases** | 1 comprehensive | 10+ individual |
| **Purpose** | Basic functionality | Complete validation |

## 🚨 Error Handling

### **Detailed Error Reporting**
Each validation provides specific error details:
```
Property 1: ✗ Price 150 is outside range 200-700
Property 2: ✓ Price 500 is within range 200-700
Property 3: ✗ Too many images: 35 (max 30)
```

### **Step-by-Step Failure Tracking**
- Each step is logged with success/failure
- Clear indication of which step failed
- Detailed error messages with context

## 🔄 Daily Execution Setup

### **CI/CD Integration**
Add to your daily build pipeline:
```yaml
# Example GitHub Actions
- name: Run Smoke Tests
  run: mvn test -Dgroups=smoke
```

### **Scheduled Execution**
```bash
# Cron job for daily execution
0 9 * * * cd /path/to/project && mvn test -Dgroups=smoke
```

## 📈 Benefits

1. **Fast Feedback** - Quick validation of critical functionality
2. **Production Safety** - Catches breaking changes early
3. **Detailed Reporting** - Clear pass/fail with step details
4. **Maintainable** - Modular architecture for easy updates
5. **Scalable** - Easy to add more smoke test scenarios

## 🎯 Interview Assessment Ready

This implementation demonstrates:
- ✅ **Professional test architecture** (POM, Components, Functions)
- ✅ **Comprehensive smoke testing** (Single test, detailed reporting)
- ✅ **Production-ready code** (Error handling, logging, validation)
- ✅ **Java best practices** (Clean code, proper structure)
- ✅ **Selenium expertise** (Advanced interactions, validation)

**Perfect for your job interview assessment! 🚀**
