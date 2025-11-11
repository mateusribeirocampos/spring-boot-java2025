# 📚 Testcontainer API - Spring Boot REST API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-25-orange.svg)](https://openjdk.java.net/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

API RESTful para gerenciamento de livros e autores, demonstrando relacionamento **Many-to-Many** com Spring Boot, JPA/Hibernate e MySQL.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Endpoints](#-endpoints)
  - [Books](#books)
  - [Users/Authors](#usersauthors)
  - [Relacionamentos](#relacionamentos-many-to-many)
- [Como Rodar](#-como-rodar)
- [Testes](#-testes)
- [Relacionamento Many-to-Many](#-relacionamento-many-to-many)
- [Documentação de Estudo](#-documentação-de-estudo)
- [Roadmap](#-roadmap)
- [Autor](#-autor)

---

## 🎯 Sobre o Projeto

Este projeto é uma **API REST** completa desenvolvida com **Spring Boot 3.5.7** e **Java 25**, focada em demonstrar:

- ✅ Arquitetura REST com boas práticas
- ✅ Relacionamento **Many-to-Many** bidirecional (Books ↔ Authors)
- ✅ CRUD completo para Books e Users
- ✅ Operações sobre relacionamentos (adicionar/remover autores de livros)
- ✅ Migrações de banco de dados com Flyway
- ✅ DTOs para separação de camadas
- ✅ Exception Handling global
- ✅ Validações com Bean Validation
- ✅ Testes com JUnit, Mockito e Testcontainers

### Funcionalidades Principais

- 📖 **Gerenciamento de Livros** (CRUD completo)
- 👤 **Gerenciamento de Usuários/Autores** (CRUD completo)
- 🔗 **Relacionamento Many-to-Many** entre Books e Authors
- ➕ **Adicionar autores a livros existentes** (POST)
- ➖ **Remover autores de livros** (DELETE)
- 🔍 **Consultas com relacionamentos carregados**
- 📊 **Paginação e ordenação** (futura implementação)

---

## 🛠️ Tecnologias

### Backend
- **Java 25** - Linguagem de programação
- **Spring Boot 3.5.7** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Hibernate** - ORM (Object-Relational Mapping)
- **Flyway** - Migrações de banco de dados
- **Bean Validation** - Validação de dados

### Banco de Dados
- **MySQL 8.0** - Banco de dados relacional
- **Testcontainers** - Testes de integração com containers Docker

### Testes
- **JUnit 5** - Framework de testes
- **Mockito** - Mocks para testes unitários
- **Spring Boot Test** - Testes de integração

### Ferramentas
- **Maven** - Gerenciador de dependências
- **SLF4J/Logback** - Logs
- **Docker** - Containerização (desenvolvimento e testes)

---

## 🏗️ Arquitetura

### Estrutura de Pacotes

```
com.campos.testcontainer
├── controllers/          # Controllers REST
│   ├── BookController
│   ├── UserController
│   └── exceptions/       # Exception handlers
├── services/            # Lógica de negócio
│   ├── BookService
│   ├── UserService
│   └── exceptions/      # Custom exceptions
├── repositories/        # Camada de persistência
│   ├── BookRepository
│   └── UserRepository
├── entities/           # Entidades JPA
│   ├── Book
│   └── User
├── data/dto/           # Data Transfer Objects
│   ├── bookdto/
│   │   ├── BookCreateDto
│   │   ├── BookUpdateDto
│   │   └── BookResponseDto
│   └── userdto/
│       ├── UserCreateDto
│       ├── UserUpdateDto
│       ├── UserResponseDto
│       └── UserSummaryDto
├── mapper/             # Conversores Entity ↔ DTO
│   ├── BookMapper
│   └── UserMapper
└── config/            # Configurações
    └── DbSeedConfig   # Seed de dados inicial
```

### Camadas

```
┌─────────────────────────────────────┐
│         REST Controllers            │
│  (BookController, UserController)   │
└─────────────────┬───────────────────┘
                  │
                  ▼
┌─────────────────────────────────────┐
│           Services                  │
│   (BookService, UserService)        │
│   - Lógica de negócio               │
│   - Validações                      │
│   - Transações                      │
└─────────────────┬───────────────────┘
                  │
                  ▼
┌─────────────────────────────────────┐
│         Repositories                │
│  (BookRepository, UserRepository)   │
│   - Acesso ao banco                 │
└─────────────────┬───────────────────┘
                  │
                  ▼
┌─────────────────────────────────────┐
│          Database (MySQL)           │
│   - book_tb                         │
│   - user_tb                         │
│   - user_book_tb (relacionamento)   │
└─────────────────────────────────────┘
```

---

## 📡 Endpoints

Base URL: `http://localhost:8080/api`

### Books

#### Listar todos os livros
```http
GET /api/books/v1
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Clean Code",
    "description": "A Handbook of Agile Software Craftsmanship",
    "price": 89.99,
    "launchDate": "2008-08-01 00:00:00",
    "author": [
      {
        "id": 1,
        "firstName": "Robert",
        "lastName": "Martin"
      }
    ]
  }
]
```

---

#### Buscar livro por ID
```http
GET /api/books/v1/{id}
```

**Parâmetros:**
- `id` (path) - ID do livro

**Resposta (200 OK):**
```json
{
  "id": 1,
  "title": "Clean Code",
  "description": "A Handbook of Agile Software Craftsmanship",
  "price": 89.99,
  "launchDate": "2008-08-01 00:00:00",
  "author": [
    {
      "id": 1,
      "firstName": "Robert",
      "lastName": "Martin"
    }
  ]
}
```

**Erros:**
- `404 Not Found` - Livro não encontrado

---

#### Criar novo livro
```http
POST /api/books/v1
Content-Type: application/json
```

**Body:**
```json
{
  "title": "Design Patterns",
  "description": "Elements of Reusable Object-Oriented Software",
  "price": 85.50,
  "launchDate": "1994-10-31 00:00:00",
  "authorsIds": [1, 2, 3]
}
```

**Resposta (201 Created):**
```json
{
  "id": 12,
  "title": "Design Patterns",
  "description": "Elements of Reusable Object-Oriented Software",
  "price": 85.50,
  "launchDate": "1994-10-31 00:00:00",
  "author": [
    { "id": 1, "firstName": "Erich", "lastName": "Gamma" },
    { "id": 2, "firstName": "Richard", "lastName": "Helm" },
    { "id": 3, "firstName": "Ralph", "lastName": "Johnson" }
  ]
}
```

**Validações:**
- `title`: obrigatório, máximo 100 caracteres
- `price`: obrigatório, deve ser positivo
- `launchDate`: obrigatório, formato `yyyy-MM-dd HH:mm:ss`
- `authorsIds`: opcional, lista de IDs válidos

**Erros:**
- `400 Bad Request` - Dados inválidos
- `404 Not Found` - Autor não encontrado

---

#### Atualizar livro
```http
PUT /api/books/v1/{id}
Content-Type: application/json
```

**Body:**
```json
{
  "title": "Clean Code - 2nd Edition",
  "description": "Updated version",
  "price": 99.99,
  "launchDate": "2020-01-01 00:00:00",
  "authorsIds": [1]
}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "title": "Clean Code - 2nd Edition",
  "description": "Updated version",
  "price": 99.99,
  "launchDate": "2020-01-01 00:00:00",
  "author": [
    { "id": 1, "firstName": "Robert", "lastName": "Martin" }
  ]
}
```

**Observação:** Se `authorsIds` for fornecido, **substitui** todos os autores atuais.

**Erros:**
- `404 Not Found` - Livro ou autor não encontrado
- `400 Bad Request` - Dados inválidos

---

#### Deletar livro
```http
DELETE /api/books/v1/{id}
```

**Resposta (204 No Content)**

**Observação:** Remove também os relacionamentos na tabela `user_book_tb`.

**Erros:**
- `404 Not Found` - Livro não encontrado

---

### Users/Authors

#### Listar todos os usuários
```http
GET /api/users/v1
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "firstName": "Robert",
    "lastName": "Martin",
    "email": "robert.martin@example.com",
    "gender": "Male",
    "birthDate": "1952-12-05",
    "phoneNumber": "1234567890",
    "address": "123 Main St",
    "state": "CA"
  }
]
```

---

#### Buscar usuário por ID
```http
GET /api/users/v1/{id}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "firstName": "Robert",
  "lastName": "Martin",
  "email": "robert.martin@example.com",
  "gender": "Male",
  "birthDate": "1952-12-05",
  "phoneNumber": "1234567890",
  "address": "123 Main St",
  "state": "CA"
}
```

**Erros:**
- `404 Not Found` - Usuário não encontrado

---

#### Criar novo usuário
```http
POST /api/users/v1
Content-Type: application/json
```

**Body:**
```json
{
  "firstName": "Kent",
  "lastName": "Beck",
  "email": "kent.beck@example.com",
  "password": "securePassword123",
  "gender": "Male",
  "birthDate": "1961-03-31",
  "phoneNumber": "9876543210",
  "address": "456 Oak Ave",
  "state": "OR"
}
```

**Resposta (201 Created):**
```json
{
  "id": 16,
  "firstName": "Kent",
  "lastName": "Beck",
  "email": "kent.beck@example.com",
  "gender": "Male",
  "birthDate": "1961-03-31",
  "phoneNumber": "9876543210",
  "address": "456 Oak Ave",
  "state": "OR"
}
```

**Validações:**
- `firstName`: obrigatório
- `lastName`: obrigatório
- `email`: obrigatório, formato válido
- `password`: obrigatório (não retorna na resposta)

**Erros:**
- `400 Bad Request` - Dados inválidos

---

#### Atualizar usuário
```http
PUT /api/users/v1/{id}
Content-Type: application/json
```

**Body:** (mesma estrutura do POST)

**Resposta (200 OK)**

**Erros:**
- `404 Not Found` - Usuário não encontrado
- `400 Bad Request` - Dados inválidos

---

#### Deletar usuário
```http
DELETE /api/users/v1/{id}
```

**Resposta (204 No Content)**

**Erros:**
- `404 Not Found` - Usuário não encontrado

---

### Relacionamentos Many-to-Many

#### Adicionar autor a um livro
```http
POST /api/books/v1/{bookId}/authors/{authorId}
```

**Parâmetros:**
- `bookId` (path) - ID do livro
- `authorId` (path) - ID do autor a adicionar

**Resposta (200 OK):**
```json
{
  "id": 5,
  "title": "Refactoring",
  "author": [
    { "id": 2, "firstName": "Martin", "lastName": "Fowler" },
    { "id": 8, "firstName": "Kent", "lastName": "Beck" }
  ],
  "price": 79.99,
  "launchDate": "2002-11-15 00:00:00",
  "description": "Improving the Design of Existing Code"
}
```

**Comportamento:**
- Adiciona o relacionamento entre book e author
- Atualiza **ambos** os lados (bidirecional)
- Insere registro na tabela `user_book_tb`

**Erros:**
- `404 Not Found` - Livro ou autor não encontrado
- `400 Bad Request` - Autor já vinculado ao livro

**Exemplo:**
```bash
# Adicionar Kent Beck (ID 8) ao livro Refactoring (ID 5)
POST http://localhost:8080/api/books/v1/5/authors/8
```

---

#### Remover autor de um livro
```http
DELETE /api/books/v1/{bookId}/authors/{authorId}
```

**Parâmetros:**
- `bookId` (path) - ID do livro
- `authorId` (path) - ID do autor a remover

**Resposta (200 OK):**
```json
{
  "id": 5,
  "title": "Refactoring",
  "author": [
    { "id": 2, "firstName": "Martin", "lastName": "Fowler" }
  ],
  "price": 79.99,
  "launchDate": "2002-11-15 00:00:00",
  "description": "Improving the Design of Existing Code"
}
```

**Comportamento:**
- Remove o relacionamento entre book e author
- Atualiza **ambos** os lados (bidirecional)
- Remove registro da tabela `user_book_tb`
- **Não** deleta o livro nem o autor (apenas o relacionamento)

**Erros:**
- `404 Not Found` - Livro ou autor não encontrado
- `400 Bad Request` - Autor não está vinculado ao livro

**Exemplo:**
```bash
# Remover Kent Beck (ID 8) do livro Refactoring (ID 5)
DELETE http://localhost:8080/api/books/v1/5/authors/8
```

---

## 🚀 Como Rodar

### Pré-requisitos

- **Java 25** instalado
- **Maven 3.8+** instalado
- **MySQL 8.0** rodando
- **Docker** (opcional, para Testcontainers)

### Configuração do Banco de Dados

1. **Criar banco de dados:**
```sql
CREATE DATABASE testcontainer_db;
```

2. **Configurar credenciais em `application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/testcontainer_db
spring.datasource.username=root
spring.datasource.password=sua_senha
```

Ou usar **variáveis de ambiente** (recomendado):
```bash
export DB_URL=jdbc:mysql://localhost:3306/testcontainer_db
export DB_USERNAME=root
export DB_PASSWORD=sua_senha
```

### Executar

```bash
# Clonar o repositório
git clone <repository-url>
cd testcontainer

# Compilar
mvn clean install

# Rodar
mvn spring-boot:run
```

### Verificar

Aplicação rodando em: `http://localhost:8080`

Testar endpoint:
```bash
curl http://localhost:8080/api/books/v1
```

---

## 🧪 Testes

### Executar todos os testes
```bash
mvn test
```

### Testes disponíveis

- **Testes Unitários** (Service layer)
  - Mock de repositories
  - Validação de lógica de negócio
  - Testes de exceções

- **Testes de Integração** (Controller layer)
  - MockMvc para simular requisições HTTP
  - Testes de endpoints REST
  - Validação de respostas JSON

- **Testes com Testcontainers**
  - Container MySQL real
  - Migrações Flyway
  - Testes end-to-end

### Exemplo de teste
```bash
# Testar apenas BookService
mvn test -Dtest=BookServiceTest

# Testar apenas endpoints de Book
mvn test -Dtest=BookControllerTest
```

---

## 🔗 Relacionamento Many-to-Many

### Estrutura do Banco

```sql
-- Tabela de Livros
CREATE TABLE book_tb (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  price DOUBLE NOT NULL,
  launch_date DATETIME(6) NOT NULL,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL
);

-- Tabela de Usuários/Autores
CREATE TABLE user_tb (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(80) NOT NULL,
  last_name VARCHAR(80) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  gender VARCHAR(10),
  birth_date DATE,
  phone_number VARCHAR(20),
  address VARCHAR(200),
  state VARCHAR(2),
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL
);

-- Tabela de Relacionamento (Many-to-Many)
CREATE TABLE user_book_tb (
  user_id BIGINT NOT NULL,
  book_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, book_id),
  FOREIGN KEY (user_id) REFERENCES user_tb(id),
  FOREIGN KEY (book_id) REFERENCES book_tb(id)
);
```

### Entidades JPA

```java
// Book.java
@Entity
@Table(name = "book_tb")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "authoredBooks")
    private Set<User> authors = new HashSet<>();
    // ... outros campos
}

// User.java
@Entity
@Table(name = "user_tb")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
        name = "user_book_tb",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> authoredBooks = new HashSet<>();
    // ... outros campos
}
```

### Relacionamento Bidirecional

**Importante:** Ao adicionar/remover relacionamentos, **sempre atualizar ambos os lados**:

```java
// ✅ CORRETO - Atualiza ambos os lados
author.getAuthoredBooks().add(book);  // Lado User
book.getAuthors().add(author);         // Lado Book

// ❌ ERRADO - Só um lado (inconsistente)
book.getAuthors().add(author);  // Relacionamento incompleto!
```

### Operações Disponíveis

| Operação | Endpoint | Verbo | Descrição |
|----------|----------|-------|-----------|
| Criar livro com autores | `/api/books/v1` | POST | Cria livro e vincula autores |
| Atualizar autores | `/api/books/v1/{id}` | PUT | Substitui todos os autores |
| Adicionar autor | `/api/books/v1/{bookId}/authors/{authorId}` | POST | Adiciona 1 autor |
| Remover autor | `/api/books/v1/{bookId}/authors/{authorId}` | DELETE | Remove 1 autor |

---

## 📖 Documentação de Estudo

Este projeto possui **documentação didática completa** no diretório:
```
/home/mrc/dev/obsidianVaults/Spring-boot-java2025/
```

### Documentos Disponíveis

| # | Documento | Tópico |
|---|-----------|--------|
| 043 | `043-entendendo-relacionamento-many-to-many-profundamente.md` | Conceitos de Many-to-Many |
| 044 | `044-code-review-senior-addauthortobook-exercicio1.md` | Code Review: addAuthorToBook |
| 045 | `045-endpoint-rest-addauthortobook-bookcontroller.md` | Endpoint POST (adicionar) |
| 046 | `046-endpoint-rest-removeauthorfrombook-delete.md` | Endpoint DELETE (remover) |
| 047 | `047-comparacao-post-vs-delete-rest-apis.md` | Comparação POST vs DELETE |
| 048 | `048-erros-comuns-endpoints-rest-troubleshooting.md` | Troubleshooting REST |

### Conteúdo da Documentação

- ✅ Conceitos teóricos profundos
- ✅ Passo a passo de implementação
- ✅ Erros comuns e como corrigir
- ✅ Boas práticas REST
- ✅ Code reviews detalhados
- ✅ Comparações lado a lado
- ✅ Exercícios práticos

**Total:** ~3800 linhas de documentação didática!

---

## 🗺️ Roadmap

### ✅ Implementado

- [x] CRUD de Books
- [x] CRUD de Users
- [x] Relacionamento Many-to-Many
- [x] Adicionar autor ao livro (POST)
- [x] Remover autor do livro (DELETE)
- [x] Migrações Flyway
- [x] DTOs e Mappers
- [x] Exception Handling global
- [x] Validações com Bean Validation
- [x] Testes unitários e integração
- [x] Testcontainers

### 🚧 Em Desenvolvimento

- [ ] Listar autores de um livro (GET)
- [ ] Listar livros de um autor (GET)
- [ ] Paginação e ordenação
- [ ] Documentação com Swagger/OpenAPI
- [ ] Autenticação e autorização (Spring Security)
- [ ] Cache com Redis
- [ ] Rate limiting

### 🔮 Futuro

- [ ] GraphQL API
- [ ] WebSockets para notificações
- [ ] Busca full-text (Elasticsearch)
- [ ] CI/CD pipeline
- [ ] Docker Compose para ambiente completo
- [ ] Kubernetes manifests

---

## 📊 Estrutura do Projeto

```
testcontainer/
├── src/
│   ├── main/
│   │   ├── java/com/campos/testcontainer/
│   │   │   ├── controllers/
│   │   │   │   ├── BookController.java
│   │   │   │   ├── UserController.java
│   │   │   │   └── exceptions/
│   │   │   │       └── GlobalExceptionHandler.java
│   │   │   ├── services/
│   │   │   │   ├── BookService.java
│   │   │   │   ├── UserService.java
│   │   │   │   └── exceptions/
│   │   │   │       └── ResourceNotFoundException.java
│   │   │   ├── repositories/
│   │   │   │   ├── BookRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── entities/
│   │   │   │   ├── Book.java
│   │   │   │   └── User.java
│   │   │   ├── data/dto/
│   │   │   │   ├── bookdto/
│   │   │   │   │   ├── BookCreateDto.java
│   │   │   │   │   ├── BookUpdateDto.java
│   │   │   │   │   └── BookResponseDto.java
│   │   │   │   └── userdto/
│   │   │   │       ├── UserCreateDto.java
│   │   │   │       ├── UserUpdateDto.java
│   │   │   │       ├── UserResponseDto.java
│   │   │   │       └── UserSummaryDto.java
│   │   │   ├── mapper/
│   │   │   │   ├── BookMapper.java
│   │   │   │   └── UserMapper.java
│   │   │   ├── config/
│   │   │   │   └── DbSeedConfig.java
│   │   │   └── TestcontainerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/migration/
│   │           ├── V1__create_user_table.sql
│   │           ├── V2__populate_table_user.sql
│   │           ├── V3__create_book_table.sql
│   │           ├── V4__populate_table_book.sql
│   │           ├── V5__create_user_book_table.sql
│   │           └── V6__populate_user_book_table.sql
│   └── test/
│       └── java/com/campos/testcontainer/
│           ├── services/
│           │   ├── BookServiceTest.java
│           │   └── UserServiceTest.java
│           ├── controllers/
│           │   ├── BookControllerTest.java
│           │   └── UserControllerTest.java
│           └── integration/
│               └── BookIntegrationTest.java
├── pom.xml
└── README.md
```

---

## 🤝 Como Contribuir

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Autor

**Mateus Ribeiro de Campos**

- GitHub: [@seu-usuario](https://github.com/seu-usuario)
- LinkedIn: [seu-perfil](https://linkedin.com/in/seu-perfil)
- Email: seu-email@example.com

---

## 🙏 Agradecimentos

- [Spring Boot](https://spring.io/projects/spring-boot) - Framework maravilhoso
- [Baeldung](https://www.baeldung.com/) - Tutoriais excelentes
- [Vlad Mihalcea](https://vladmihalcea.com/) - Expert em Hibernate/JPA

---

## 📚 Recursos Úteis

### Documentação Oficial
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Hibernate Documentation](https://hibernate.org/orm/documentation/)
- [Flyway Documentation](https://flywaydb.org/documentation/)

### Tutoriais
- [REST API Best Practices](https://restfulapi.net/)
- [JPA Many-to-Many Relationship](https://www.baeldung.com/jpa-many-to-many)
- [Testing in Spring Boot](https://www.baeldung.com/spring-boot-testing)

---

## 📞 Suporte

Se você tiver alguma dúvida ou problema, sinta-se à vontade para:

1. Abrir uma [Issue](https://github.com/seu-usuario/testcontainer/issues)
2. Enviar um email para: seu-email@example.com
3. Consultar a [Documentação de Estudo](#-documentação-de-estudo)

---

<div align="center">

**⭐ Se este projeto te ajudou, dê uma estrela! ⭐**

Made with ❤️ by [Mateus Ribeiro de Campos](https://github.com/seu-usuario)

</div>
