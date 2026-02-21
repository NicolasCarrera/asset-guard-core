# AGENTS.md — asset-guard-core

## Project overview

**asset-guard-core** is a Java/Spring Boot REST API for Fixed Asset Management (EAM/ITAM).
It handles serialized assets (vehicles, machinery, IT hardware), custody workflows,
lifecycle tracking, maintenance records, and depreciation calculations.

- **Language / platform:** Java 25, Spring Boot 4.x (target 4.0.3), Maven
- **Base package:** `com.assetguard.core`
- **Entrypoint:** `com.assetguard.core.Application`

---

## Build and test commands

```bash
# Build
mvn clean package -DskipTests

# Run tests (unit + integration)
mvn verify

# Run only unit tests
mvn test

# Run integration tests (requires Docker for Testcontainers)
mvn failsafe:integration-test
```

---

## Architecture

Follows **Clean Architecture / lightweight DDD**. Every change must respect layer boundaries.

```
com.assetguard.core
├── domain/          # Business rules only — no Spring, no JPA
├── application/     # Use cases, application services, command/query DTOs
├── infrastructure/  # JPA entities, repositories, external adapters
└── interfaces/      # REST controllers, request/response DTOs, MapStruct mappers
```

**Layer rules:**
- `domain` must not depend on any other layer or framework.
- `application` depends only on `domain`.
- `infrastructure` and `interfaces` depend on `application` and `domain`.
- Never place business logic in controllers. Controllers delegate to use cases only.

---

## Code organization rules

> **Critical:** Flat, oversized packages are forbidden. Use sub-packages to keep each
> directory under ~8 files. Never let a single package grow into a dumping ground.

### Domain layer — split by aggregate

Each aggregate lives in its own sub-package:

```
domain/
├── asset/           # Asset, AssetStatus, AssetTag (value object), AssetService
├── component/       # AssetComponent, ComponentService
├── custody/         # Custody, CustodyStatus, CustodyService
├── maintenance/     # MaintenanceRecord, Intervention, MaintenanceService
├── depreciation/    # DepreciationPolicy, DepreciationCalculator, DepreciationEntry
└── shared/          # Shared value objects, base classes, domain events
```

- One aggregate root per sub-package.
- Value objects and domain services stay in the same sub-package as their aggregate.
- Do not place more than ~8 classes directly inside `domain/`. Add a sub-package instead.

### Application layer — split by use case group

```
application/
├── asset/           # CreateAssetUseCase, UpdateAssetUseCase, AssetDto, AssetCommand
├── custody/         # CheckOutUseCase, CheckInUseCase, CustodyDto
├── maintenance/     # CreateInterventionUseCase, MaintenanceDto
└── depreciation/    # RecalculateDepreciationUseCase, DepreciationDto
```

### Infrastructure layer — split by concern

```
infrastructure/
├── persistence/
│   ├── asset/       # AssetJpaEntity, AssetJpaRepository, AssetRepositoryAdapter
│   ├── custody/     # CustodyJpaEntity, CustodyJpaRepository, ...
│   ├── maintenance/ # ...
│   └── depreciation/# ...
├── flyway/          # SQL migrations only (V__*.sql naming)
└── config/          # JPA config, security beans, Flyway config
```

### Interfaces layer — one controller per resource

```
interfaces/
├── rest/
│   ├── asset/       # AssetController, AssetRequestDto, AssetResponseDto, AssetMapper
│   ├── custody/     # CustodyController, ...
│   ├── maintenance/ # MaintenanceController, ...
│   └── depreciation/# DepreciationController, ...
└── openapi/         # OpenAPI customizations, global error handlers
```

---

## Stack

| Concern | Library |
|---|---|
| Web | Spring Web (REST) |
| Persistence | Spring Data JPA + PostgreSQL |
| Migrations | Flyway |
| DTO mapping | MapStruct |
| Boilerplate | Lombok (use sparingly) |
| Audit history | Hibernate Envers |
| Validation | Jakarta Validation |
| Security | Spring Security |
| Observability | Spring Actuator |
| Testing | JUnit 5, Mockito, Testcontainers (Postgres) |
| API docs | springdoc-openapi |

---

## Database

- Database: **PostgreSQL**
- Migrations: Flyway, files in `infrastructure/flyway/`, named `V<version>__<description>.sql`
- Required environment variables:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/assetguard
SPRING_DATASOURCE_USERNAME=asset
SPRING_DATASOURCE_PASSWORD=secret
SPRING_PROFILES_ACTIVE=local
```

- **Never hard-delete assets.** Use `status` transitions only. Envers provides the immutable audit trail.

---

## Business rules (enforce in domain/application layers)

| ID | Rule |
|---|---|
| RNI-01 | `assetTag` is globally unique — even for voided/decommissioned assets. |
| RNI-02 | A component can have at most one parent asset at a time. |
| RNI-03 | Custody transfers are 1:1 — only one active custody per asset. |
| RNI-04 | Custody acceptance requires explicit confirmation (signature / acceptance flag). |
| RNI-05 | Physical deletion is forbidden. Valid terminal states: `VOIDED`, `DECOMMISSIONED`. |
| RNI-07 | Assets in `MAINTENANCE` or `CRITICAL_FAILURE` state cannot be assigned. |
| RNI-08 | Maintenance interventions must record technician, date, and cost. |
| RNI-09 | Depreciation must never reduce NBV below the residual value. |
| RNI-10 | Capitalizable improvements update NBV without deleting historical depreciation records. |

Write a unit test for every RNI rule. Tag tests with `@Tag("business-rule")`.

---

## Code conventions

- **Money / accounting values:** always `BigDecimal`. Never `double` or `float`.
- **HTTP input:** always use a dedicated DTO. Never bind to domain entities directly.
- **Mapping:** use MapStruct. Do not write manual mapping code.
- **Validation:** Jakarta Validation annotations on DTOs; invariant checks inside domain services.
- **No logic in controllers:** controllers parse HTTP, call one use case, return a response. Nothing else.
- **Formatter:** Google Java Format. Run before committing (`mvn spotless:apply`).
- **Null safety:** prefer `Optional` in domain queries. Avoid returning `null` from public methods.
- **Exceptions:** define domain-specific exceptions (e.g. `AssetTagDuplicateException`) in `domain/shared/`. Map to HTTP status codes in a `@RestControllerAdvice`.

---

## Testing guidelines

- Unit tests for all domain services and use cases (Mockito for dependencies).
- Integration tests for repositories and controllers use **Testcontainers** (`@SpringBootTest` + real Postgres).
- Every RNI rule must have at least one dedicated test.
- Tests must pass before any PR is merged: `mvn verify`.

---

## Security and audit

- All write endpoints require authentication (Spring Security).
- Envers audits every entity change automatically — do not bypass with native queries.
- Never log sensitive fields (passwords, PII) at any log level.
- Secrets are injected via environment variables only. No hardcoded credentials anywhere.
