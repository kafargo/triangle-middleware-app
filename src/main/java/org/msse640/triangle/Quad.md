# Quadrilateral API – Internal Documentation

This module provides a simple REST API to evaluate quadrilateral types based on four side lengths.

---

## Functionality

- **POST `/quadrilateral/type`**  
  Evaluate a new shape and update default side values.

- **PUT `/quadrilateral/test/default`**  
  Update the stored default sides (no evaluation).

- **GET `/quadrilateral/test/default`**  
  Evaluate the current stored sides.

---

## Technical Notes

- Uses `QuadService` to store side values in memory.
- Responses from `POST` and `GET` return side values + type in JSON format.
- No database or persistence layer — values reset on app restart.
- Intended for internal use only (testing, debugging, and demos).
