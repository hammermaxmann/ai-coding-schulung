# Refactoring-Zielstruktur: Product.java

## Vorher (God Class)

```mermaid
classDiagram
    class Product {
        -String barcode
        -String productName
        -String productNameDe
        -String brands
        -String quantity
        -String packaging
        -String categories
        -List~String~ categoriesTags
        -String labels
        -List~String~ labelsTags
        -String stores
        -String countries
        -List~String~ countriesTags
        -Double energyKcal100g
        -Double energy100g
        -Double fat100g
        -Double saturatedFat100g
        -Double carbohydrates100g
        -Double sugars100g
        -Double fiber100g
        -Double proteins100g
        -Double salt100g
        -Double sodium100g
        -String allergens
        -List~String~ allergensTags
        -String traces
        -List~String~ tracesTags
        -String ingredientsText
        -String ingredientsTextDe
        -String nutriScoreGrade
        -Integer nutriScoreScore
        -Integer novaGroup
        -String ecoScoreGrade
        -Integer ecoScoreScore
        -NutrientLevels nutrientLevels
        -String imageUrl
        -String imageSmallUrl
        -String imageFrontUrl
        -String imageNutritionUrl
        -String imageIngredientsUrl
        -Long createdTimestamp
        -Long lastModifiedTimestamp
        -String creator
        -List~String~ editorsTags
        -Double completeness
    }

    class NutrientLevels {
        <<inner class>>
        -String fat
        -String salt
        -String sugars
        -String saturatedFat
    }

    Product *-- NutrientLevels

    class ProductDetailDto {
        <<DUPLIKAT>>
        ~gleiche Felder wie Product~
    }

    style Product fill:#ff6666
    style ProductDetailDto fill:#ff9999
```

**Probleme:** 40+ Felder, 7 verschiedene Concerns, innere Klasse, Duplikat-DTO

---

## Nachher (aufgeteilte Klassen)

```mermaid
classDiagram
    class Product {
        -String barcode
        -String productName
        -String productNameDe
        -String brands
        -String quantity
        -String packaging
        -String categories
        -List~String~ categoriesTags
        -String labels
        -List~String~ labelsTags
        -String stores
        -String countries
        -List~String~ countriesTags
        -NutritionInfo nutritionInfo
        -AllergenInfo allergenInfo
        -ProductScores scores
        -ProductImages images
        -Long createdTimestamp
        -Long lastModifiedTimestamp
        -String creator
        -Double completeness
    }

    class NutritionInfo {
        -Double energyKcal100g
        -Double energy100g
        -Double fat100g
        -Double saturatedFat100g
        -Double carbohydrates100g
        -Double sugars100g
        -Double fiber100g
        -Double proteins100g
        -Double salt100g
        -Double sodium100g
    }

    class AllergenInfo {
        -String allergens
        -List~String~ allergensTags
        -String traces
        -List~String~ tracesTags
        -String ingredientsText
        -String ingredientsTextDe
    }

    class ProductScores {
        -String nutriScoreGrade
        -Integer nutriScoreScore
        -Integer novaGroup
        -String ecoScoreGrade
        -Integer ecoScoreScore
        -NutrientLevels nutrientLevels
    }

    class NutrientLevels {
        -String fat
        -String salt
        -String sugars
        -String saturatedFat
    }

    class ProductImages {
        -String imageUrl
        -String imageSmallUrl
        -String imageFrontUrl
        -String imageNutritionUrl
        -String imageIngredientsUrl
    }

    Product *-- NutritionInfo : @JsonUnwrapped
    Product *-- AllergenInfo : @JsonUnwrapped
    Product *-- ProductScores : @JsonUnwrapped
    Product *-- ProductImages : @JsonUnwrapped
    ProductScores *-- NutrientLevels

    style Product fill:#66cc66
    style NutritionInfo fill:#99dd99
    style AllergenInfo fill:#99dd99
    style ProductScores fill:#99dd99
    style ProductImages fill:#99dd99
    style NutrientLevels fill:#bbeeaa
```

**Vorteile:**
- Jede Klasse hat eine klare Verantwortlichkeit
- `Product.java` ist von ~250 auf ~80 Zeilen reduziert
- JSON-Struktur bleibt dank `@JsonUnwrapped` identisch
- `ProductDetailDto` ist ueberflüssig und geloescht
- Naehrwerte, Allergene, Scores und Bilder sind einzeln testbar und wiederverwendbar
