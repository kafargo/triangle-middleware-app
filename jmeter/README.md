# JMeter Test Plan – Middleware App

This folder contains a JMeter test plan for load testing the Middleware App backend.

## 📄 Files

- `Middleware App Test.jmx` – JMeter test plan
- `README.md` – Instructions and test structure overview

---

## 🏗️ JMeter Test Structure

```
Test Plan: Middleware App Test
└── Thread Group (2 users)
    ├── User Defined Variables
    │   ├── protocol = https
    │   ├── baseHost = triangle-middleware-app-production.up.railway.app
    │   ├── localHost = localhost
    │   ├── localPort = 8080
    │   ├── basePathTri = /triangle/type
    │   └── basePathQuad = /quad/type
    ├── HTTP Request
    └── View Results Tree
```

---

## 🧪 Running the Test

### In GUI Mode:

1. Open JMeter
2. Load the file: `File → Open → Middleware App Test.jmx`
3. Check or update the following variables in **User Defined Variables**:
   - `protocol`
   - `baseHost` or `localHost` + `localPort`
   - `basePathTri` or `basePathQuad`
4. Run the test using the green **Start** button.

---

## ✅ Notes

- You can modify the number of users or loop count in the **Thread Group**.
- Use `${protocol}`, `${baseHost}`, `${basePathTri}`, and `${basePathQuad}` in HTTP Request fields:
  - **Protocol**: `${protocol}`
  - **Server Name or IP**: `${baseHost}` (or `${localHost}:${localPort}`)
  - **Path**: `${basePathTri}` or `${basePathQuad}`
- These variables are used in the **HTTP Request** sampler fields to make the test plan reusable for local or live environments.
- Results are visible in the **View Results Tree** listener.

---


