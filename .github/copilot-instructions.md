# Copilot Instructions

## Project Overview

This is a **hands-on AI coding workshop** (German: "Workshop: KI-gestützte Softwareentwicklung"). It is an educational project where participants practice using AI coding assistants on a real Java codebase that contains **intentional code smells and anti-patterns**. The anti-patterns exist on purpose — do not "fix" them unless a use case explicitly asks for it.

The main project is `food-facts-service/`, a Java client for the [Open Food Facts API](https://world.openfoodfacts.org/).

## Build & Test Commands

All commands run from `food-facts-service/`:

```bash
mvn compile              # Compile
mvn clean compile        # Clean rebuild
mvn test                 # Run all tests
mvn test -Dtest=ProductTest                          # Single test class
mvn test -Dtest=ProductTest#testJacksonSerialization # Single test method
```

**Prerequisites:** Java 17+, Maven 3.8+

## Architecture

```
food-facts-service/src/main/java/com/workshop/foodfacts/
  client/    # OpenFoodFactsClient — HTTP wrapper around the Open Food Facts REST API
  model/     # Domain objects (Product, SearchResponse) with Jackson @JsonProperty mapping
  service/   # Business logic (ProductService with caching, NutritionService, SearchService)
  dto/       # Data transfer objects (NutritionSummaryDto, ProductDetailDto)
```

`ProductService` wraps `OpenFoodFactsClient` and adds a simple `HashMap`-based cache. `NutritionService` depends on `ProductService` for product lookups.

## Key Conventions

### Jackson Mapping
All model classes use `@JsonIgnoreProperties(ignoreUnknown = true)` and map snake_case API fields to camelCase Java fields via `@JsonProperty`:

```java
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @JsonProperty("product_name")
    private String productName;
}
```

When generating code that produces JSON output, use the `@JsonProperty` field names (e.g., `"product_name"`), not the Java field names (`productName`).

### No Spring Framework
This is plain Java with no Spring, no dependency injection framework, and no application server. Dependencies are injected manually via constructors.

### Testing Stack
- **JUnit 5 (Jupiter)** — always specify "JUnit 5" explicitly to avoid JUnit 4 generation
- **Mockito 5** for mocking service dependencies
- Test imports: `org.junit.jupiter.api.Test`, not `org.junit.Test`

### JSON Backward Compatibility (Use Case 3)
When refactoring `Product.java` into smaller classes, use `@JsonUnwrapped` to keep the JSON output flat and identical to the original. This is a hard constraint.

### Intentional Anti-Patterns (Do Not Fix Unprompted)
- `Product.java` — 337-line God Class with 25+ fields mixing 7 concerns
- `ProductDetailDto.java` — intentional near-duplicate of `Product`
- `SearchService.java` — intentionally duplicates search logic from `ProductService`

Comments like `// HINWEIS: Diese Klasse enthaelt absichtlich viele Felder...` mark these intentional issues.

## Workshop Use Cases

| Use Case | Goal | Key Constraint |
|----------|------|----------------|
| `usecase-1-openapi/` | Add REST controller + OpenAPI 3.0 YAML spec | No Spring; plain Java |
| `usecase-2-tests/` | Write JUnit 5 + Mockito tests for `Product` and `NutritionService` | JUnit 5, not 4 |
| `usecase-3-refactoring/` | Refactor `Product.java` God Class | JSON output must remain identical (`@JsonUnwrapped`) |

Each use case directory has an `ANLEITUNG.md` with step-by-step prompts.
