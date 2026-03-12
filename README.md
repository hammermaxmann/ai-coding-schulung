# Workshop: KI-gestuetzte Softwareentwicklung - Hands-on

## Ueberblick

Dieser Workshop vermittelt den praktischen Einsatz von KI-Coding-Assistenten anhand eines echten Java-Projekts.
Das Projekt ist ein Client fuer die **Open Food Facts API** - eine kostenlose, offene Lebensmittel-Datenbank.

Die Teilnehmer arbeiten mit einem vorbereiteten Java-Projekt, das **absichtlich typische Probleme** aus gewachsenen Codebases enthaelt:
- Keine Tests
- Keine REST-API / OpenAPI-Dokumentation
- Eine ueberladene "God Class" mit 25+ Feldern
- Duplizierter Code zwischen Klassen

## Voraussetzungen

| Was | Details |
|-----|---------|
| **JDK** | Java 17 oder hoeher |
| **Build-Tool** | Maven 3.8+ |
| **IDE** | IntelliJ IDEA oder Eclipse |
| **KI-Plugin** | GitHub Copilot, Cursor, oder vergleichbar |
| **Internet** | Fuer Open Food Facts API-Zugriff |

### Projekt einrichten

```bash
# In das Projektverzeichnis wechseln
cd ai-coding-workshop/food-facts-service

# Projekt kompilieren (prueft ob alles korrekt eingerichtet ist)
mvn compile

# Optional: Projekt in der IDE oeffnen
# IntelliJ: File > Open > food-facts-service/pom.xml > "Open as Project"
# Eclipse: File > Import > Maven > Existing Maven Projects
```

## Das Projekt: Food Facts Service

Ein Java-Client fuer die [Open Food Facts API](https://world.openfoodfacts.org/):

| Paket | Klasse | Beschreibung |
|-------|--------|-------------|
| `client` | `OpenFoodFactsClient` | HTTP-Client fuer die externe API |
| `model` | `Product` | Produktdaten (25+ Felder) |
| `model` | `SearchRequest` | Suchparameter |
| `model` | `SearchResponse` | Suchergebnis mit Paginierung |
| `service` | `ProductService` | Produktzugriff, Caching, Mapping |
| `service` | `NutritionService` | Naehrwert-Analyse & Bewertungen |
| `service` | `SearchService` | Produktsuche (Duplikat!) |
| `dto` | `ProductDetailDto` | Produktdetails DTO (Duplikat!) |
| `dto` | `NutritionSummaryDto` | Naehrwert-Zusammenfassung |

### Open Food Facts API - Kurzreferenz

```
Kein API-Key noetig!

Produkt per Barcode:
  GET https://world.openfoodfacts.org/api/v2/product/3017620422003.json
  (3017620422003 = Nutella)

Produktsuche:
  GET https://world.openfoodfacts.org/cgi/search.pl?search_terms=nutella&json=1&page_size=5
```

## Die 3 Use Cases

| # | Use Case | Schwierigkeit | Dauer | IDE |
|---|----------|:------------:|:-----:|:---:|
| 1 | [REST API & OpenAPI Spezifikation](usecase-1-openapi/ANLEITUNG.md) | Leicht | ~20 Min | IntelliJ |
| 2 | [JUnit Tests erstellen](usecase-2-tests/ANLEITUNG.md) | Leicht | ~20 Min | IntelliJ / Eclipse |
| 3 | [Refactoring der God Class](usecase-3-refactoring/ANLEITUNG.md) | Mittel | ~20 Min | Eclipse |

### Empfohlene Reihenfolge

1. **Use Case 1 (OpenAPI)** - Generiert neue Dateien, kein Risiko fuer bestehenden Code. Baut Verstaendnis auf.
2. **Use Case 2 (Tests)** - Fuegt Tests hinzu, erstellt ein Sicherheitsnetz fuer den naechsten Schritt.
3. **Use Case 3 (Refactoring)** - Aendert bestehenden Code. Tests aus UC2 koennen das Ergebnis validieren.

## Zeitplan

| Phase | Dauer | Inhalt |
|-------|:-----:|--------|
| Einfuehrung | 5 Min | Projekt oeffen, kurze Orientierung |
| Use Case 1 | 20 Min | REST Controller + OpenAPI Spec |
| Use Case 2 | 20 Min | JUnit Tests mit Mockito |
| Use Case 3 | 20 Min | Code-Analyse & Refactoring |
| Diskussion | 10 Min | Erfahrungen, Fragen, Best Practices |

## Wichtige Hinweise

- **KI ist ein Werkzeug, kein Orakel** - Generierte Ergebnisse immer kritisch pruefen!
- **Iterativ arbeiten** - Der erste Prompt liefert selten das perfekte Ergebnis. Nachfragen und verfeinern ist Teil des Prozesses.
- **Kontext ist alles** - Je mehr relevante Dateien der Assistent "sieht", desto bessere Ergebnisse liefert er.
- **Die Fehler im Code sind absichtlich** - Wir lernen daraus, wie KI damit umgeht!
