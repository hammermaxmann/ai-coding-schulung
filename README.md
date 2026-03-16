# Workshop: KI-gestuetzte Softwareentwicklung - Hands-on

## Ueberblick

Dieser Workshop vermittelt den praktischen Einsatz von KI-Coding-Assistenten anhand eines echten Java-Projekts.
Das Projekt ist ein Client fuer die **Open Food Facts API** - eine kostenlose, offene Lebensmittel-Datenbank.

Die Teilnehmer arbeiten mit einem vorbereiteten Java-Projekt, das **absichtlich typische Probleme** aus gewachsenen Codebases enthaelt:
- Keine Tests
- Keine REST-API / OpenAPI-Dokumentation
- Eine ueberladene "God Class" mit ~50 Feldern
- Duplizierter Code zwischen Klassen

## Voraussetzungen

| Was | Details |
|-----|---------|
| **JDK** | Java 17 oder hoeher |
| **Build-Tool** | Maven 3.8+ |
| **IDE** | IntelliJ IDEA |
| **KI-Plugin** | GitHub Copilot, Cursor, oder vergleichbar |
| **Internet** | Fuer Open Food Facts API-Zugriff |

### Projekt einrichten

```bash
# In das Projektverzeichnis wechseln
cd ai-coding-schulung/food-facts-service

# Projekt kompilieren (prueft ob alles korrekt eingerichtet ist)
mvn compile

# Optional: Projekt in der IDE oeffnen
# IntelliJ: File > Open > food-facts-service/pom.xml > "Open as Project"
```

## Das Projekt: Food Facts Service

Ein Java-Client fuer die [Open Food Facts API](https://world.openfoodfacts.org/):

| Paket | Klasse | Beschreibung |
|-------|--------|-------------|
| `client` | `OpenFoodFactsClient` | HTTP-Client fuer die externe API |
| `model` | `Product` | Produktdaten (~50 Felder) |
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

## Die 4 Use Cases

| # | Use Case | Schwierigkeit | IDE |
|---|----------|:------------:|:---:|
| 1 | [REST API & OpenAPI Spezifikation](usecase-1-openapi/ANLEITUNG.md) | Leicht | IntelliJ |
| 2 | [JUnit Tests erstellen](usecase-2-tests/ANLEITUNG.md) | Leicht | IntelliJ |
| 3 | [MCP Database](usecase-3-mcp-database/ANLEITUNG.md) | Fortgeschritten | IntelliJ |
| 4 | [Refactoring der God Class](usecase-4-refactoring/ANLEITUNG.md) | Mittel | IntelliJ |

### Empfohlene Reihenfolge

1. **Use Case 1 (OpenAPI)** - Generiert neue Dateien, kein Risiko fuer bestehenden Code. Baut Verstaendnis auf.
2. **Use Case 2 (Tests)** - Fuegt Tests hinzu, erstellt ein Sicherheitsnetz fuer den naechsten Schritt.
3. **Use Case 3 (MCP Database)** - KI-gestuetzte SQL-Abfragen ueber MCP Server.
4. **Use Case 4 (Refactoring)** - Aendert bestehenden Code. Tests aus UC2 koennen das Ergebnis validieren.

## Zeitplan

| Phase | Inhalt |
|-------|--------|
| Einfuehrung | Projekt oeffen, kurze Orientierung |
| Use Case 1 | REST Controller + OpenAPI Spec |
| Use Case 2 | JUnit Tests mit Mockito |
| Use Case 3 | KI-gestuetzte SQL-Abfragen via MCP Server |
| Use Case 4 | Code-Analyse & Refactoring |
| Diskussion | Erfahrungen, Fragen, Best Practices |

## Wichtige Hinweise

- **KI ist ein Werkzeug, kein Orakel** - Generierte Ergebnisse immer kritisch pruefen!
- **Iterativ arbeiten** - Der erste Prompt liefert selten das perfekte Ergebnis. Nachfragen und verfeinern ist Teil des Prozesses.
- **Kontext ist alles** - Je mehr relevante Dateien der Assistent "sieht", desto bessere Ergebnisse liefert er.
- **Die Fehler im Code sind absichtlich** - Wir lernen daraus, wie KI damit umgeht!
