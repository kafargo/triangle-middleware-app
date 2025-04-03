# Postman Testing Guide – Quadrilateral API

This guide outlines how to test each endpoint of the Quadrilateral Evaluation API using Postman.

---

## How to Use Postman to Test the API

When testing endpoints in Postman, you have two main options for sending query parameters:

1. **Use the URL directly:**  
   Append the parameters to the URL like so:
   `http://localhost:8080/quadrilateral/type?side1=2&side2=2&side3=2&side4=2`

2. **Use the Params tab:**  
   - Enter the base URL: `http://localhost:8080/quadrilateral/type`
   - Switch to the **Params** tab
   - Add each parameter (`side1`, `side2`, etc.) and their values in the table

Both options work the same and produce the same result. The Params tab just separates the values out for clarity.

---

## Base URLs

- **Local Development:**  
  `http://localhost:8080`

- **Production (Railway App):**  
  `https://triangle-middleware-app-production.up.railway.app`

---

## 1. POST `/quadrilateral/type`

**Query Parameters:**
- `side1` (double): Length of side 1  
- `side2` (double): Length of side 2  
- `side3` (double): Length of side 3  
- `side4` (double): Length of side 4

**Purpose:**  
Initialize and evaluate a new quadrilateral.  
This endpoint sets the side values for use in GET and PUT.

**Method:** `POST`

**Request URL (Local):**  
`http://localhost:8080/quadrilateral/type?side1=2&side2=2&side3=2&side4=2`

**Request URL (Production):**  
`https://triangle-middleware-app-production.up.railway.app/quadrilateral/type?side1=2&side2=2&side3=2&side4=2`

**On success:**  
Returns `200 OK` with JSON body:
```
{
  "side1": 2,
  "side2": 2,
  "side3": 2,
  "side4": 2,
  "type": "Type of Quadrilateral: Square"
}
```

---

## 2. GET `/quadrilateral/test/default`

**Query Parameters:**
- None (uses stored values from previous POST)

**Purpose:**  
Retrieve the current default side values and evaluate the quadrilateral.

**Note:** Requires POST to be called first.

**Method:** `GET`

**Request URL:**  
`http://localhost:8080/quadrilateral/test/default`

**If uninitialized:**  
Returns `400 Bad Request` with message:
```
No sides have been set. Please use POST /quadrilateral/type first.
```

**Note:** A 400 response here is not a bug. It's expected behavior when sides have not yet been initialized. This is useful during testing to verify that the POST endpoint is required.

**On success:**  
Returns `200 OK` with JSON body:
```
{
  "side1": 2,
  "side2": 2,
  "side3": 2,
  "side4": 2,
  "type": "Type of Quadrilateral: Square"
}
```

---

## 3. PUT `/quadrilateral/test/default`

**Query Parameters:**
- `side1` (double): New length of side 1  
- `side2` (double): New length of side 2  
- `side3` (double): New length of side 3  
- `side4` (double): New length of side 4

**Purpose:**  
Update the currently stored side values (must have been initialized via POST first).

**Method:** `PUT`

**Request URL:**  
`http://localhost:8080/quadrilateral/test/default?side1=6&side2=4&side3=6&side4=4`

**If uninitialized:**  
Returns `400 Bad Request` with message:
```
Cannot update sides. Please use POST /quadrilateral/type first.
```

**Note:** A 400 response from this endpoint is also expected if POST has not been called. It confirms that the app enforces proper initialization flow.

**On success:**  
Returns `200 OK` with a confirmation message:
```
Defaults updated to: [6.0, 4.0, 6.0, 4.0]
```

---

## Postman Setup Tips

- For `POST` and `PUT`, either:
  - Use the **Params tab** to enter `side1` through `side4`, or
  - Append the parameters directly to the request URL

- Set the request method (`POST`, `PUT`, or `GET`) using the dropdown next to the URL bar in Postman.

---

## Optional Enhancements

- Create a Postman **Collection** with these 3 requests saved.
- Add test scripts to verify status codes or response formats.

Let your team know: **Always call POST first** if they see a 400 error from GET or PUT.

