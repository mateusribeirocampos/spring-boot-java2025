# REST API with Spring Boot and Java

A RESTful web service built with Spring Boot 3.4.0, Java 21, and MySQL, featuring person management with API versioning, custom mappers, and comprehensive exception handling.

## 🚀 Features

- **RESTful API**: Full CRUD operations for person management
- **API Versioning**: Multiple API versions (v1 and v2) for backward compatibility
- **Object Mapping**: DozerMapper integration and custom mappers for DTO conversion
- **Database Integration**: MySQL database with JPA/Hibernate
- **Exception Handling**: Custom exception responses and resource not found handling
- **Testing**: Unit tests with mocks and comprehensive test coverage
- **Logging**: Configurable logging levels for debugging and monitoring

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.4.0**
- **Spring Data JPA**
- **MySQL**
- **DozerMapper 7.0.0**
- **Maven**
- **JUnit 5** (for testing)

## 📁 Project Structure

```
src/
├── main/
│   ├── java/br/com/campos/
│   │   ├── controller/          # REST controllers
│   │   │   ├── PersonController.java
│   │   │   └── TestLogController.java
│   │   ├── data/dto/            # Data Transfer Objects
│   │   │   ├── v1/PersonDTO.java
│   │   │   └── v2/PersonDTOV2.java
│   │   ├── exception/           # Exception handling
│   │   │   ├── ExceptionResponse.java
│   │   │   ├── ResourceNotFoundException.java
│   │   │   └── handler/CustomEntityResponseHandler.java
│   │   ├── mapper/              # Object mapping utilities
│   │   │   ├── ObjectMapper.java
│   │   │   └── custom/PersonMapper.java
│   │   ├── model/               # JPA entities
│   │   │   └── Person.java
│   │   ├── repository/          # Data access layer
│   │   │   └── PersonRepository.java
│   │   ├── services/            # Business logic layer
│   │   │   └── PersonServices.java
│   │   ├── converters/          # Utility converters
│   │   │   └── NumberConverter.java
│   │   └── Startup.java         # Main application class
│   └── resources/
│       └── application.yml      # Application configuration
└── test/
    └── java/br/com/campos/
        ├── unitTests/mapper/    # Unit tests for mappers
        └── StartupTests.java    # Integration tests
```

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+

### Environment Variables

Set the following environment variables before running the application:

```bash
DATABASE_URL=jdbc:mysql://localhost:3306/your_database
DATABASE_USERNAME=your_username
DATABASE_PASSWORD=your_password
```

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd spring-boot-and-java
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

## 📚 API Documentation

### Person Management API

#### Base URL: `/person`

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/person/{id}` | Get person by ID | - | PersonDTO |
| GET | `/person` | Get all persons | - | List&lt;PersonDTO&gt; |
| POST | `/person` | Create new person (v1) | PersonDTO | PersonDTO |
| POST | `/person/v2` | Create new person (v2) | PersonDTOV2 | PersonDTOV2 |
| PUT | `/person` | Update existing person | PersonDTO | PersonDTO |
| DELETE | `/person/{id}` | Delete person by ID | - | 204 No Content |

### API Versioning

The API supports multiple versions:

- **v1**: Standard PersonDTO with basic fields
- **v2**: Enhanced PersonDTOV2 with additional fields and features

### Request/Response Examples

#### Create Person (v1)
```json
POST /person
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "address": "123 Main St",
  "gender": "Male"
}
```

#### Create Person (v2)
```json
POST /person/v2
Content-Type: application/json

{
  "firstName": "Jane",
  "lastName": "Smith",
  "address": "456 Oak Ave",
  "gender": "Female"
  // Additional v2-specific fields
}
```

## 🗄️ Database Schema

### Person Table
```sql
CREATE TABLE person (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(80) NOT NULL,
  last_name VARCHAR(80) NOT NULL,
  address VARCHAR(100) NOT NULL,
  gender VARCHAR(6) NOT NULL
);
```

## 🔧 Configuration

### Application Configuration (`application.yml`)

```yaml
spring:
  application:
    name: rest-with-spring-boot-and-java
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    open-in-view: false

logging:
  level:
    br.com.campos: DEBUG
```

## 🧪 Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=ObjectMapperTests
```

### Test Structure

- **Unit Tests**: Located in `src/test/java/br/com/campos/unitTests/`
- **Mock Data**: Utility classes for generating test data
- **Integration Tests**: End-to-end testing of the application

## 📊 Architecture

### Layered Architecture

1. **Controller Layer**: Handles HTTP requests and responses
2. **Service Layer**: Contains business logic
3. **Repository Layer**: Data access and persistence
4. **Model Layer**: JPA entities representing database tables

### Design Patterns

- **DTO Pattern**: Separate data transfer objects for API communication
- **Repository Pattern**: Abstracted data access layer
- **Custom Mapper Pattern**: Specialized object mapping for complex transformations
- **Exception Handling Pattern**: Centralized error handling and response formatting

## 🔄 Object Mapping

The application uses two mapping approaches:

1. **DozerMapper**: Generic object mapping utility
2. **Custom Mappers**: Specialized mappers for complex object transformations (e.g., PersonMapper)

## 📝 Logging

Configurable logging levels:
- **DEBUG**: Detailed application flow (br.com.campos package)
- **INFO**: General application information
- **WARN/ERROR**: Issues and exceptions

## 🚧 Development Status

This project is currently in active development. Features being worked on:

- [ ] Authentication and Authorization
- [ ] API Documentation (Swagger/OpenAPI)
- [ ] Pagination and Sorting
- [ ] Validation Enhancements
- [ ] Caching Implementation
- [ ] Performance Monitoring
- [ ] Docker Support
- [ ] CI/CD Pipeline

## 🤝 Contributing

This project is still under development. Contributions, suggestions, and feedback are welcome as new features are added.

## 📄 License

This project is a demo/learning project for Spring Boot development.

## 📞 Contact

For questions or suggestions about this project, please open an issue in the repository.

---

**Note**: This README will be updated as new features and functionalities are added to the project.