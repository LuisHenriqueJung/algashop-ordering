# Ordering Service

Spring Boot microservice responsible for managing orders in the AlgaShop ecosystem. Built with Java 21 and Spring Boot 4.

## Tech Stack
- **Language**: Java 21
- **Framework**: Spring Boot 4.0.1
- **Build**: Gradle (Wrapper)
- **Persistence**: Spring Data JPA (Hibernate)
- **Database (dev/test)**: H2 (file)
- **Utilities**: Lombok, AssertJ, Hypersistence TSID, Java UUID Generator, Commons Validator

## Requirements
- JDK 21 available on PATH
- (Optional) IDE with Gradle support (e.g., IntelliJ IDEA)

## Quickstart
- Run locally (Windows/PowerShell):
  ```powershell
  .\gradlew.bat bootRun
  ```

- Build the project:
  ```powershell
  .\gradlew.bat clean build
  ```

## Testing
- Unit tests (classes ending with `*Test`):
  ```powershell
  .\gradlew.bat test
  ```

- Integration tests (classes ending with `*IT`):
  ```powershell
  .\gradlew.bat integrationTest
  ```

- Full verification (runs both):
  ```powershell
  .\gradlew.bat check
  ```

## Configuration
Application config lives at `src/main/resources/application.yaml`.

Key defaults:
- `spring.datasource.url`: `jdbc:h2:file:~/ordering;DATABASE_TO_LOWER=TRUE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;`
- `spring.datasource.username`: `sa`
- `spring.datasource.password`: `123`
- `spring.h2.console.enabled`: `true` (console at `/h2-console`)
- `spring.jpa.hibernate.ddl-auto`: `update`
- `spring.jpa.show-sql`: `true`

H2 Console (dev):
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:~/ordering`
- User: `sa`
- Password: `123`

> For production, use a managed RDBMS and externalize credentials (env vars, Vault, etc.).

## Project Structure
```
src/
  main/
    java/com/jung/algashop/ordering/
      OrderingApplication.java     # Spring Boot entry point
      domain/                      # Domain model (entities, value objects, aggregates)
      infrastructure/
        persistence/               # Persistence adapters, repositories, mappers
        h2/                        # H2-specific infrastructure (if any)
    resources/
      application.yaml             # Spring configuration
  test/
    ...                            # Unit/Integration tests (*Test, *IT)
```

## Gradle Tasks (common)
- **Run**: `bootRun`
- **Build**: `build`
- **Unit tests**: `test`
- **Integration tests**: `integrationTest`
- **All checks**: `check`

## IDE Notes
- Import as a Gradle project; the wrapper defines toolchain Java 21.
- The legacy Gradle IDEA plugin is not used; modern IDEs handle Gradle projects directly.

## Roadmap
- Expose REST endpoints for order lifecycle (create, fetch, update status).
- Add database migrations (Flyway/Liquibase) when moving beyond H2 for dev.
- Containerization (Docker) and local compose for dependencies as needed.

## License
Part of the AlgaShop meta-repository. Licensing follows the parent repository policies.

