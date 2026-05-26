# Library Management System

A backend REST API built with Spring Boot that handles everything a library needs — managing books, registering users, authenticating with JWT tokens, and tracking who borrowed what and when.

This started as a learning project and grew into something I'm genuinely proud of. It covers the full backend lifecycle: API design, database schema, security, caching, and deployment.

---

## Tech Stack

- **Java 21** + **Spring Boot 3.5**
- **Spring Security** + **JWT** — stateless authentication with role-based access
- **Spring Data JPA** + **Hibernate** — ORM and database interaction
- **MySQL** — relational database for persistent storage
- **Redis** — caching layer for fast book lookups
- **Lombok** — reduces boilerplate code
- **Maven** — dependency management

---

## What It Does

- Users can register and log in — passwords are encrypted with BCrypt
- Every protected route requires a valid JWT token in the `Authorization` header
- Admins can add, update, and delete books
- Members can borrow and return books
- The system tracks borrowing history, due dates, and overdue books
- Redis caches book lookups so repeated queries don't hit the database

---

## API Endpoints

### Auth
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/auth/register` | Register a new user | No |
| POST | `/auth/login` | Login and receive JWT token | No |

### Books
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/books` | Add one or more books | Yes |
| GET | `/api/books/{title}` | Get book by title | Yes |
| PUT | `/api/books/{title}/status` | Update availability status | Yes |
| DELETE | `/api/books/{title}` | Delete a book | Yes |

### Borrowing
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/borrow/{username}/{bookId}` | Borrow a book | Yes |
| PUT | `/api/borrow/return/{recordId}` | Return a borrowed book | Yes |
| GET | `/api/borrow/history/{username}` | View borrowing history | Yes |
| GET | `/api/borrow/overdue` | List all overdue books | Yes |

---

## Project Structure

```
src/main/java/com/example/librarymanagementsystem/
│
├── controller/
│   ├── AuthController.java          # Register and login
│   ├── LibraryManagementController.java  # Book CRUD
│   └── BorrowingRecordController.java    # Borrow and return
│
├── service/
│   ├── LibraryManagementService.java
│   ├── BorrowingRecordService.java
│   └── CustomUserDetailsService.java    # Loads user for Spring Security
│
├── entity/
│   ├── BookDetails.java             # Book table
│   ├── User.java                    # Users table
│   └── BorrowingRecord.java         # Borrowing records table
│
├── repo/
│   ├── LibraryManagementRepo.java
│   ├── UserRepository.java
│   └── BorrowingRecordRepository.java   # Includes overdue JPQL query
│
├── helper/
│   ├── JwtUtil.java                 # Token generation and validation
│   ├── JwtFilter.java               # Intercepts every request
│   └── RedisHelper.java             # Caching logic
│
└── SecurityConfig.java              # Spring Security configuration
```

---

## Running Locally

### Prerequisites
- Java 21
- MySQL 8
- Redis

### Steps

1. Clone the repository
```bash
git clone https://github.com/Susenther/library-management-system.git
cd library-management-system
```

2. Create `src/main/resources/application.properties` (not committed for security):
```properties
spring.application.name=librarymanagementsystem
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/librarydb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.data.redis.host=localhost
spring.data.redis.port=6379

logging.level.org.springframework=INFO
logging.file.name=logs/lms.log
```

3. Start MySQL and Redis, then run:
```bash
mvn spring-boot:run
```

4. The API will be available at `http://localhost:8080`

---

## How Authentication Works

1. Register via `POST /auth/register`
2. Login via `POST /auth/login` — you'll receive a JWT token
3. For all protected routes, add this header:
```
Authorization: Bearer <your_token>
```

---

## Database Schema

Three tables are auto-created by Hibernate on startup:

- **book_details** — stores book information and availability status
- **users** — stores credentials with BCrypt-encrypted passwords and roles
- **borrowing_records** — tracks every borrow event with borrow date, due date, return date, and status

---

## Things I Learned Building This

- How JWT authentication actually works under the hood — not just copying boilerplate
- Why stateless sessions matter for scalable APIs
- The difference between `@Cacheable`, `@CachePut`, and `@CacheEvict` in Redis
- How Spring Security filter chains intercept requests before they reach controllers
- Designing relational schemas that reflect real-world relationships

---

## Author

**Susenther E**
- GitHub: [github.com/Susenther](https://github.com/Susenther)
- LinkedIn: [linkedin.com/in/susenther](https://linkedin.com/in/susenther)
- Email: susenthar289@gmail.com
