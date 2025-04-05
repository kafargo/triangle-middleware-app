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

- **DELETE `/quadrilateral/test/default`**  
  Clear the stored sides and reset the API state.

---

## Technical Notes

- Uses `QuadService` to store side values in memory.
- Responses from `POST` and `GET` return side values + type in JSON format.
- No database or persistence layer — values reset on app restart.
- `DELETE` resets the initialized state. After calling it, `GET` and `PUT` will return 400 until a new `POST` is made.
- Intended for internal use only (testing, debugging, and demos).
