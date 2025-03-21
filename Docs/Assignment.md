# Introduction

## Overview of the Program

The **Triangle Middleware Application** is a Spring Boot-based RESTful API designed to determine the type of a triangle based on the lengths of its sides. The application provides a single endpoint, `/triangle/type`, which accepts three parameters (`side1`, `side2`, and `side3`) representing the lengths of the triangle's sides. Based on the input, the API returns one of the following results:

- **Equilateral**: All three sides are equal.
- **Isosceles**: Two sides are equal.
- **Scalene**: All three sides are different.
- **Invalid triangle sides**: The input does not form a valid triangle (e.g., sides are zero or negative).

The application also integrates Swagger for API documentation, making it easy to test and explore the API via a web interface at `/swagger-ui.html`.

## How Errors Were Handled

Error handling was implemented at multiple levels to ensure robustness and user-friendly responses:

1. **Input Validation**:

   - The `Triangle` model class checks if any side is less than or equal to zero. If so, it returns `"Invalid triangle sides"`.
   - The `TriangleController` uses Spring's `@RequestParam` to automatically validate and parse the input parameters. If invalid data (e.g., non-numeric strings) is provided, Spring automatically returns a `400 Bad Request` response.

2. **Exception Handling**:

   - The `Triangle` class gracefully handles invalid input by returning a meaningful message (`"Invalid triangle sides"`) instead of throwing exceptions.
   - For invalid string inputs (e.g., `"abc"`), the unit tests ensure that a `NumberFormatException` is thrown and handled appropriately.

3. **API-Level Error Responses**:

   - The API returns appropriate HTTP status codes:
     - `200 OK` for valid triangle types.
     - `400 Bad Request` for invalid input (e.g., non-numeric values).

## Choice of Unit Tests

Unit tests (using JUnit) were designed to cover all possible scenarios for determining the type of a triangle. The tests ensure the correctness of the application and validate its behavior under edge cases. Below is an overview of the unit tests:

### 1. Controller Tests

- **Purpose**: To test the `/triangle/type` endpoint and ensure it returns the correct triangle type or error response.
- **Test Cases**:
  - **Equilateral Triangle**: Input sides `3, 3, 3` should return `"Equilateral"`.
  - **Isosceles Triangle**: Input sides `3, 3, 4` should return `"Isosceles"`.
  - **Scalene Triangle**: Input sides `3, 4, 5` should return `"Scalene"`.
  - **Invalid Triangle**: Input sides `0, 4, 5` should return `"Invalid triangle sides"`.
  - **Invalid String Input**: Input sides `abc, 4, 5` should return a `400 Bad Request`.

### 2. Model Tests

- **Purpose**: To test the logic in the `Triangle` class for determining the triangle type.
- **Test Cases**:
  - **Equilateral Triangle**: Verify that `Triangle(3, 3, 3).getType()` returns `"Equilateral"`.
  - **Isosceles Triangle**: Verify that `Triangle(3, 3, 4).getType()` returns `"Isosceles"`.
  - **Scalene Triangle**: Verify that `Triangle(3, 4, 5).getType()` returns `"Scalene"`.
  - **Invalid Triangle**: Verify that `Triangle(0, 4, 5).getType()` returns `"Invalid triangle sides"`.
  - **Invalid String Input**: Ensure that passing a non-numeric value (e.g., `"zyx"`) throws a `NumberFormatException`.

### 3. Integration Tests

- While not explicitly implemented, the combination of controller and model tests ensures end-to-end validation of the application's functionality.

## Summary

The **Triangle Middleware Application** is a robust and well-tested API that handles errors gracefully and provides clear feedback to users. The choice of unit tests ensures comprehensive coverage of all possible scenarios, including valid inputs, invalid inputs, and edge cases. This approach guarantees the reliability and correctness of the application in real-world usage.

# Program Details

## VS Code as a IDE Choice

Visual Studio Code (VS Code) is an excellent IDE for running and managing the **Triangle Middleware Application** due to its lightweight nature, extensive plugin ecosystem, and robust debugging capabilities. Here are some key benefits:

1. **Integrated Development Environment**:

   - VS Code provides a seamless environment for writing, testing, and debugging Java applications. With extensions like the "Java Extension Pack," you can easily manage Maven dependencies, run unit tests, and debug your Spring Boot application.

2. **Built-in Terminal**:

   - The integrated terminal allows you to execute Maven commands (e.g., `mvn clean install`, `mvn spring-boot:run`) without leaving the IDE, streamlining the development workflow.

3. **Spring Boot Support**:

   - Extensions like "Spring Boot Tools" provide features such as live application monitoring, Spring Boot-specific code assistance, and easy navigation through Spring components.

4. **Swagger Integration**:

   - VS Code's REST Client extension allows you to test the API directly from the IDE by sending HTTP requests to the application's endpoints.

5. **Git Integration**:

   - The built-in Git support makes it easy to manage version control, push changes to GitHub, and collaborate with team members.

6. **Debugging**:
   - VS Code's debugging tools allow you to set breakpoints, inspect variables, and step through the code, making it easier to identify and resolve issues.

---

## Technical Overview of the Triangle Middleware Application

The **Triangle Middleware Application** is a Spring Boot-based RESTful API designed to determine the type of a triangle based on the lengths of its sides. Below is a technical breakdown of the application:

1. **Core Functionality**:

   - The application provides a single endpoint, `/triangle/type`, which accepts three parameters (`side1`, `side2`, `side3`) representing the lengths of the triangle's sides.
   - Based on the input, the API determines the type of triangle:
     - **Equilateral**: All three sides are equal.
     - **Isosceles**: Two sides are equal.
     - **Scalene**: All three sides are different.
     - **Invalid triangle sides**: The input does not form a valid triangle (e.g., sides are zero or negative).

2. **Error Handling**:

   - Input validation ensures that sides are positive numbers.
   - Invalid inputs (e.g., non-numeric values) result in a `400 Bad Request` response.
   - The application gracefully handles invalid triangle sides by returning a meaningful message.

3. **Swagger Integration**:

   - Swagger is integrated for API documentation and testing. The Swagger UI is accessible at `/swagger-ui.html`.

4. **Unit Testing**:

   - Comprehensive unit tests (using JUnit) validate the application's functionality, covering all edge cases.

5. **Configuration**:
   - The application is configured to run on port `8080` (defined in `application.properties`).

---

## How to Interface with the API

### Hosted Endpoint

If the application is hosted on a server, you can interact with the API by sending HTTP POST requests to the `/triangle/type` endpoint. Below is an example using `curl`:

```bash
curl -X POST "http://<host>:8080/triangle/type" \
     -d "side1=3" -d "side2=3" -d "side3=3"
```

### Using Swagger to Interface with the API

Swagger provides an interactive web interface to explore and test the API. To access Swagger:

1. **Start the Application**:

   - Run the application using the following Maven command:
     ```bash
     mvn spring-boot:run
     ```

2. **Access the Swagger UI**:

   - Open a web browser and navigate to:
     ```
     http://localhost:8080/swagger-ui.html
     ```

3. **Explore the API**:

   - The Swagger UI will display the available endpoints, including `/triangle/type`.
   - You can test the endpoint by providing input values for `side1`, `side2`, and `side3` directly in the Swagger interface.

4. **Example Usage in Swagger**:

   - Input: `side1=3`, `side2=3`, `side3=3`
     - Expected Response: `"Equilateral"`
   - Input: `side1=3`, `side2=3`, `side3=4`
     - Expected Response: `"Isosceles"`
   - Input: `side1=3`, `side2=4`, `side3=5`
     - Expected Response: `"Scalene"`
   - Input: `side1=0`, `side2=4`, `side3=5`
     - Expected Response: `"Invalid triangle sides"`

5. **Benefits of Swagger**:
   - Provides a user-friendly interface for testing the API without needing external tools like `curl` or Postman.
   - Automatically documents the API, making it easier for developers to understand and use the endpoints.
   - Displays detailed request and response formats, including error messages for invalid inputs.

By using Swagger, you can efficiently test and interact with the **Triangle Middleware Application** without writing additional client code.

# Example Test Data

What test data did you input into the program.

# Unit Tests

Describe the Unit Tests you created. Why did you choose those.

# Bugs Encountered

Describe any bugs you encountered as the result of your testing and what did you do to fix them?

# Problems

What kinds of problems did you encounter

# Screen Shots

Includes screen shots showing successful program runs and Unit test runs. Clearly label each

# Git Hub Link

https://github.com/kafargo/triangle-middleware-app

# Recommendations

What recommendations would you make to improve this assignment.
