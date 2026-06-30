# API Testing Demo

This collection demonstrates core API testing concepts using two public REST APIs:

- **[JSONPlaceholder](https://jsonplaceholder.typicode.com)** — a free fake REST API for prototyping and testing.
- **[DummyJSON](https://dummyjson.com)** — a dummy JSON API that supports authentication flows.

---

## What's Covered

### Posts (JSONPlaceholder)

Covers the full CRUD lifecycle for a `posts` resource.

| Request | Method | Endpoint | Description |
|---|---|---|---|
| Get Posts | GET | `/posts` | Retrieves the full list of posts. Validates a 200 OK status with an array of post objects. |
| Get Single Post | GET | `/posts/1` | Fetches a single post by ID. Validates the response contains `id`, `title`, `body`, `userId`. |
| Create Post | POST | `/posts` | Creates a new post with `title`, `body`, and `userId`. Validates a 201 Created response. |
| Update Post | PUT | `/posts/1` | Fully updates an existing post. Validates a 200 OK with updated data. |
| Delete Post | DELETE | `/posts/1` | Removes a post by ID. Validates a 200 OK confirming deletion. |

### Authentication (DummyJSON)

Demonstrates authentication scenarios including success and failure cases.

| Request | Method | Endpoint | Description |
|---|---|---|---|
| Login and Get Token | POST | `/auth/login` | Authenticates with valid credentials and retrieves a JWT access token. |
| Get Auth User | GET | `/auth/me` | Fetches the authenticated user's profile using a Bearer token. |
| Invalid Login Request | POST | `/auth/login` | **Negative test.** Uses an incorrect password — expects a 400 or 401 error. |
| Invalid Method | GET | `/auth/login` | **Negative test.** Uses an unsupported HTTP method — expects a 404 or 405 error. |

---

## Request Details

### Get Posts

- **Method:** GET
- **URL:** `https://jsonplaceholder.typicode.com/posts`
- **Purpose:** Retrieves the full list of posts from the JSONPlaceholder API. Demonstrates a basic read operation.
- **Expected Response:** `200 OK` with an array of post objects.

---

### Get Single Post

- **Method:** GET
- **URL:** `https://jsonplaceholder.typicode.com/posts/1`
- **Purpose:** Fetches a specific post by its ID.
- **Expected Response:** `200 OK` with a single post object containing `id`, `title`, `body`, and `userId`.

---

### Create Post

- **Method:** POST
- **URL:** `https://jsonplaceholder.typicode.com/posts`
- **Purpose:** Creates a new post resource. The request body includes `title`, `body`, and `userId`.
- **Request Body:**

```json
{
  "title": "Aditi",
  "body": "Testing",
  "userId": 1
}
```

- **Expected Response:** `201 Created` with the new post object including an assigned `id`.

---

### Update Post

- **Method:** PUT
- **URL:** `https://jsonplaceholder.typicode.com/posts/1`
- **Purpose:** Fully updates an existing post with new `title` and `body` values.
- **Request Body:**

```json
{
  "title": "Aditi",
  "body": "Testing Update"
}
```

- **Expected Response:** `200 OK` with the updated post data reflected in the response.

---

### Delete Post

- **Method:** DELETE
- **URL:** `https://jsonplaceholder.typicode.com/posts/1`
- **Purpose:** Removes a specific post by its ID.
- **Expected Response:** `200 OK` confirming the resource has been successfully deleted.

---

### Login and Get Token

- **Method:** POST
- **URL:** `https://dummyjson.com/auth/login`
- **Purpose:** Authenticates a user with valid credentials and retrieves a JWT access token for use in subsequent requests.
- **Request Body:**

```json
{
  "username": "emilys",
  "password": "emilyspass"
}
```

- **Expected Response:** `200 OK` with a JWT token in the response body.

---

### Get Auth User

- **Method:** GET
- **URL:** `https://dummyjson.com/auth/me`
- **Headers:**
  - `Authorization: Bearer <JWT_TOKEN>`
- **Purpose:** Retrieves the profile of the currently authenticated user. Requires the JWT token obtained from the Login request.
- **Expected Response:** `200 OK` with the authenticated user's profile data.

---

### Invalid Login Request _(Negative Test)_

- **Method:** POST
- **URL:** `https://dummyjson.com/auth/login`
- **Purpose:** Tests error handling for invalid credentials. Ensures the authentication endpoint rejects bad passwords.
- **Request Body:**

```json
{
  "username": "emilys",
  "password": "invalid_password"
}
```

- **Expected Response:** `400` or `401` with an error message.

---

### Invalid Method _(Negative Test)_

- **Method:** GET
- **URL:** `https://dummyjson.com/auth/login`
- **Purpose:** Tests that the login endpoint correctly rejects unsupported HTTP methods.
- **Expected Response:** `404` or `405 Method Not Allowed`.

---

## APIs Used

| API | Base URL | Purpose |
|---|---|---|
| JSONPlaceholder | `https://jsonplaceholder.typicode.com` | Free fake REST API for CRUD testing |
| DummyJSON | `https://dummyjson.com` | Dummy JSON API with authentication support |
