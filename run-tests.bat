@echo off
echo ========================================
echo Rent Ad Search QA - Java Test Runner
echo ========================================
echo.

echo Checking Java version...
java -version
echo.

echo Checking Maven version...
mvn -version
echo.

echo Compiling project...
mvn clean compile
if %ERRORLEVEL% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo.
echo Running tests...
mvn test
if %ERRORLEVEL% neq 0 (
    echo Tests failed!
    pause
    exit /b 1
)

echo.
echo Tests completed successfully!
echo.
echo To view detailed reports, run: mvn allure:serve
echo.
pause
