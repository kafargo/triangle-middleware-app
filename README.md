# Triangle Middleware Application

This project is a Spring Boot application that provides a RESTful API to determine the type of triangle based on the lengths of its sides. The application includes a POST endpoint that accepts three values representing the lengths of the sides of a triangle and returns whether the triangle is scalene, isosceles, or equilateral.

## Project Structure

```
triangle-middleware-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── msse640
│   │   │           └── triangle
│   │   │               ├── TriangleMiddlewareApplication.java
│   │   │               ├── controller
│   │   │               │   └── TriangleController.java
│   │   │               └── model
│   │   │                   └── Triangle.java
│   │   └── resources
│   │       └── application.properties
│   ├── test
│   │   ├── java
│   │   │   └── org
│   │   │       └── msse640
│   │   │           └── triangle
│   │   │               └── model
│   │   │                   └── TriangleTest.java
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Running the Application

1. Build the application using Maven:

   ```
   mvn clean install
   ```

2. Run the application:
   ```
   mvn spring-boot:run
   ```

### Accessing the API

Once the application is running, you can access the API at `http://localhost:8080/swagger-ui.html`.

### Testing

Unit tests can be run by running the folliwing command:

```
mvn test
```

## Triangle Types

- **Equilateral**: All three sides are equal.
- **Isosceles**: Two sides are equal.
- **Scalene**: All three sides are different.

## License

This project is licensed under the MIT License.
