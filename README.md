# MSSE 640 Assignment 1

This project was built by [@JohnMKreski](https://github.com/JohnMKreski), [@Yabina](https://github.com/Yabina), and [@kafargo](https://github.com/kafargo)

## :scroll: [Assignment Details](./Docs/Assignment.md)

<details>

<summary>Running The Triangle Middleware Application</summary>

# Triangle Middleware Application

This project is a Spring Boot application that provides a RESTful API to determine the type of triangle based on the lengths of its sides. The application includes a POST endpoint that accepts three values representing the lengths of the sides of a triangle and returns whether the triangle is scalene, isosceles, or equilateral.

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

## License

This project is licensed under the MIT License.

</details>
