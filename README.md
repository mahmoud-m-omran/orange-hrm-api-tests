# OrangeHRM API Testing

This project contains automated API tests for the OrangeHRM API using Rest Assured and TestNG.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [Running Tests](#running-tests)
- [Test Suite Configuration](#test-suite-configuration)
- [Best Practices](#best-practices)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before running the tests, make sure you have the following prerequisites installed:

- Java Development Kit (JDK) 19 or higher
- Maven
- IDE (e.g., Eclipse or IntelliJ IDEA, if preferred)

## Project Structure

The project structure is as follows:

```plaintext
orangehrm-api-tests/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── testcrew/
│       │           └── OrangeHRMAPITest.java
│       └── resources/
│           └── testng.xml
├── pom.xml
```

- 'src/main/java/' - Main Java source directory (for future expansions).
- 'src/test/java/' - Contains the Rest Assured test class (OrangeHRMAPITest.java).
- 'src/test/resources/' - Contains the TestNG suite configuration (testng.xml).