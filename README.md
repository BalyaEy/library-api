# 📚 Library Management API

A secure and extensible RESTful API for managing library operations such as user registration, authentication, and resource management (books, authors, borrowings, etc.). Built using Spring Boot, PostgreSQL, and follows layered architecture principles.

---

## 🚀 Features

- ✅ User registration with role assignment (ADMIN / MEMBER)
- 🔐 Password encryption with BCrypt
- 🧩 Layered architecture (Controller, Service, Repository, DTO)
- 🗄 PostgreSQL database integration
- 📦 Ready for containerization (Docker support planned)
- 🧪 Testable with Postman

---

## 🛠 Technologies Used

- Java 17+
- Spring Boot 3.5.x
- Spring Security (JWT planned)
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- Docker (coming soon)

---

## 📂 Project Structure

```
src/main/java/com/gus/library
│
├── controller       → REST Controllers (e.g. AuthController)
├── service          → Business logic (e.g. AuthenticationService)
├── repository       → JPA interfaces (e.g. UserRepository)
├── entity           → Database models (e.g. User, Role)
├── dto              → Data Transfer Objects (e.g. RegisterRequest)
├── config           → Configuration classes (e.g. AppConfig)
└── LibraryApiApplication.java
```

---

## 📡 API Endpoints (in progress)

| Method | Endpoint              | Description               |
|--------|------------------------|---------------------------|
| POST   | `/api/auth/register`   | Register a new user       |
| POST   | `/api/auth/login`      | Authenticate & return JWT |

> More endpoints coming soon: books, authors, borrowings...

---

## 🔧 Configuration

Update your database settings in `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/librarydb
    username: postgres
    password: your_password (you should declare)
```

---

## ▶️ Run the Project

### Using Maven

```bash
./mvnw spring-boot:run
```

---

## 🧪 Test the API

You can use [Postman](https://www.postman.com/) or similar tools to test endpoints like:

```http
POST http://localhost:8085/api/auth/register
```

Request Body (JSON):

```json
{
  "username": "Adam",
  "email": "x@x.com",
  "password": "1234",
  "role": "ROLE_ADMIN"
}
```

---

## 📦 Docker Support (planned)

- Dockerfile and docker-compose setup will be added for PostgreSQL + API containerization.

---

## 📌 To Do

- [x] User registration
- [ ] User login
- [ ] JWT authentication
- [ ] Role-based authorization
- [ ] Book & borrowing management
- [ ] Full Docker support

---

## 🙋‍♂️ Author

Eyüp Boyacı — [GitHub](https://github.com/BalyaEy)  
Project developed for educational and portfolio purposes.

