# User Management Application

A Spring Boot application for managing user information with CRUD operations.

## Features

- Create, Read, Update, and Delete users
- Search users by ID, name, surname, email, and nationality
- RESTful API endpoints
- In-memory H2 database for development
- Comprehensive error handling
- Input validation

## Technology Stack

- Java 8
- Spring Boot 2.7.0
- Spring Data JPA
- H2 Database
- Maven

## Project Structure

```
/workspace
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── usermanagement
│   │   │           ├── UserManagementApplication.java
│   │   │           ├── controller
│   │   │           │   └── UserController.java
│   │   │           ├── model
│   │   │           │   └── User.java
│   │   │           ├── repository
│   │   │           │   └── UserRepository.java
│   │   │           └── service
│   │   │               ├── UserService.java
│   │   │               └── UserServiceImpl.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── usermanagement
│                   └── UserControllerTests.java
├── .gitignore
├── pom.xml
└── README.md
```

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/user-management-app.git
   ```

2. Navigate to the project directory:
   ```
   cd user-management-app
   ```

3. Build the project:
   ```
   mvn clean install
   ```

4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start on port 8080.

## API Endpoints

### User Management

- **Create a new user**
  - POST `/api/users`
  - Request body: User object

- **Get all users**
  - GET `/api/users`

- **Get user by ID**
  - GET `/api/users/{id}`

- **Update user**
  - PUT `/api/users/{id}`
  - Request body: User object

- **Delete user**
  - DELETE `/api/users/{id}`

### Search Operations

- **Search user by email**
  - GET `/api/users/search/email?email={email}`

- **Search users by name**
  - GET `/api/users/search/name?name={name}`

- **Search users by surname**
  - GET `/api/users/search/surname?surname={surname}`

- **Search users by nationality**
  - GET `/api/users/search/nationality?nationality={nationality}`

## User Model

The User entity has the following fields:

- `id` (Long): Unique identifier
- `name` (String): User's first name
- `surname` (String): User's last name
- `email` (String): User's email address
- `nationality` (String): User's nationality

## Database

The application uses an H2 in-memory database for development. The H2 console is available at `/h2-console` with the following default credentials:

- JDBC URL: `jdbc:h2:mem:userdb`
- Username: `sa`
- Password: `password`

## Testing

Run the tests using Maven:

```
mvn test
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.
