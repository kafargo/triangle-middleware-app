# Introduction

Proved an overview of the program, how you handled errors, and your choice of Unit Tests

# Program Details

What IDE did you use? How did you get data into the program? Driver file, prompt user for input, menu, read
data from a file, through your Unit tests only? Did you write results to a file?

# Example Test Data

What test data did you input into the program.
| Testcase ID | Test Case Description        | Input Values (side1, side2, side3) | Expected Outcome |
|-------------|------------------------------|------------------------------------|------------------|
| TC1         | Test Equilateral Triangle    | 3, 3, 3                            | "Equilateral"    |
| TC2         | Test Equilateral Triangle with Decimal Values | 6.2, 6.2, 6.2 | "Equilateral" |
| TC3         | Test Isosceles Triangle      | 3, 3, 4 | "Isosceles" |
| TC4         | Test Scalene Triangle        | 3, 4, 5 | "Scalene" |
| TC5         | Test Triangle with a single Zero value Side| 0, 4, 5 | "Invalid triangle sides" |
| TC6         | Test Invalid Triangle (All Sides Zero) | 0, 0, 0 | "Invalid triangle sides" |
| TC7         | Test Invalid Input (Empty Side) | 5, 6, " " | HTTP 400 Bad Request |
| TC          | Test Scalene Triangle with Decimal Values | 3.5, 4.5, 5.5 | "Scalene" |
| TC9         | Test Invalid Triangle (Negative Side) | -5, 2, 2 | "Triangle sides cannot be negative value" |
| TC10        | Test Invalid Input (String Input) | "abc", 4, 5 | HTTP 400 Bad Request |
| TC11        | Test Invalid Isosceles Triangle (Sum of Two Sides Equal to Third) | 2, 2, 4 | "The sum of two sides shouldn't be equal to the other side" |
| TC12        | Test Invalid Isosceles Triangle (Sum of Two Sides Less Than Third) | 2, 2, 5 | "The sum of two sides shouldn't be less than the other side" |
| TC13        | Test Invalid Scalene Triangle (Sum of Two Sides Equal to Third) | 1, 2, 3 | "The sum of two sides shouldn't be equal to the other side" |
| TC14        | Test Invalid Scalene Triangle (Sum of Two Sides Less Than Third) | 2, 5, 13 | "The sum of two sides shouldn't be less than the other side" |


# Unit Tests

Describe the Unit Tests you created. Why did you choose those.
The unit tests in `TriangleControllerTest.java` validate the `TriangleController` class, which determines the type of triangle based on side lengths. The tests cover various scenarios to ensure correct handling of inputs and expected outcomes. Here's a summary:

### **Test Cases**
1. **Equilateral Triangle**:
   - Input: `3, 3, 3`
   - Expected: `"Equilateral"`
   - Purpose: Verify identification of triangles with all sides equal.

2. **Equilateral Triangle with Decimals**:
   - Input: `6.2, 6.2, 6.2`
   - Expected: `"Equilateral"`
   - Purpose: Ensure handling of decimal values.

3. **Isosceles Triangle**:
   - Input: `3, 3, 4`
   - Expected: `"Isosceles"`
   - Purpose: Verify identification of triangles with two equal sides.

4. **Scalene Triangle**:
   - Input: `3, 4, 5`
   - Expected: `"Scalene"`
   - Purpose: Verify identification of triangles with all sides different.

5. **Invalid Triangle (Zero Side)**:
   - Input: `0, 4, 5`
   - Expected: `"Invalid triangle sides"`
   - Purpose: Detect invalid triangles with a zero side.

6. **Invalid Triangle (All Sides Zero)**:
   - Input: `0, 0, 0`
   - Expected: `"Invalid triangle sides"`
   - Purpose: Handle cases where all sides are zero.

7. **Invalid Input (Empty Side)**:
   - Input: `5, 6, " "`
   - Expected: HTTP 400 Bad Request
   - Purpose: Handle empty or non-numeric inputs.

8. **Scalene Triangle with Decimals**:
   - Input: `3.5, 4.5, 5.5`
   - Expected: `"Scalene"`
   - Purpose: Validate decimal side lengths.

9. **Invalid Triangle (Negative Side)**:
   - Input: `-5, 2, 2`
   - Expected: `"Triangle sides cannot be negative value"`
   - Purpose: Reject negative side lengths.

10. **Invalid Input (String Input)**:
    - Input: `"abc", 4, 5`
    - Expected: HTTP 400 Bad Request
    - Purpose: Handle non-numeric inputs.

11. **Invalid Isosceles Triangle (Sum of Two Sides Equal to Third)**:
    - Input: `2, 2, 4`
    - Expected: `"The sum of two sides shouldn't be equal to the other side"`
    - Purpose: Enforce triangle inequality theorem.

12. **Invalid Isosceles Triangle (Sum of Two Sides Less Than Third)**:
    - Input: `2, 2, 5`
    - Expected: `"The sum of two sides shouldn't be less than the other side"`
    - Purpose: Further validate triangle inequality.

13. **Invalid Scalene Triangle (Sum of Two Sides Equal to Third)**:
    - Input: `1, 2, 3`
    - Expected: `"The sum of two sides shouldn't be equal to the other side"`
    - Purpose: Handle edge cases for scalene triangles.

14. **Invalid Scalene Triangle (Sum of Two Sides Less Than Third)**:
    - Input: `2, 5, 13`
    - Expected: `"The sum of two sides shouldn't be less than the other side"`
    - Purpose: Further validate triangle inequality for scalene triangles.

### **Why These Tests Were Chosen**
1. **Coverage**: Tests cover all valid triangle types (equilateral, isosceles, scalene).
2. **Edge Cases**: Include zero, negative, and invalid inputs to ensure robustness.
3. **Triangle Inequality**: Validate that the sum of any two sides is greater than the third.
4. **Decimal Values**: Ensure correct handling of non-integer side lengths.
5. **Error Handling**: Verify appropriate error messages and HTTP status codes.
6. **Robustness**: Ensure the system handles a wide range of inputs, including edge cases and invalid data.

These tests ensure the `TriangleController` is reliable, accurate, and robust in determining triangle types and handling invalid inputs.

# Bugs Encountered

Our codes looked like they were working fine until we started to test our program to see if they satisfy the inequality theorem. Our code is not sophisticated  enough to pass the inequality theorem logic.

Another bug we found was that our input didn't accept decimals or the system wouldn't work with decimal. So we changed our Input values from Integer to Double.

# Problems

What kinds of problems did you encounter
One of the main problems faced was trying to comeup with as many test cases as possible to ensure we have a working quality program. Eventhough our test cases are not comprehensive but it still has good coverage.

Another problem faced was coming up with a robhust logic in our code to fulfill all the specifications that makes the Traingles Scalene, Equilateral or Isosceles.The problem was encountered when we tried to capture the logic of inequality theorem for all three triangles.

# Screen Shots

Includes screen shots showing successful program runs and Unit test runs. Clearly label each

# Git Hub Link

https://github.com/kafargo/triangle-middleware-app

# Recommendations

What recommendations would you make to improve this assignment.
