# Use Case 1: REST API & OpenAPI Spezifikation generieren

## Lernziele

- KI nutzen, um neuen Code (REST Controller) zu generieren
- Eine OpenAPI 3.0 Spezifikation aus bestehendem Code ableiten lassen
- Ergebnisse kritisch pruefen und iterativ verbessern

## Szenario

Das Projekt hat Services und Models, aber **keinen REST-Controller und keine API-Dokumentation**.
Eure Aufgabe: Lasst den KI-Assistenten beides generieren.

## Relevante Dateien

Oeffnet diese Dateien in der IDE, damit der KI-Assistent sie als Kontext nutzen kann:

- `src/main/java/com/workshop/foodfacts/service/ProductService.java` - Produktzugriff
- `src/main/java/com/workshop/foodfacts/service/NutritionService.java` - Naehrwert-Analyse
- `src/main/java/com/workshop/foodfacts/model/Product.java` - Produktmodell (~50 Felder)
- `src/main/java/com/workshop/foodfacts/model/SearchResponse.java` - Suchergebnis
- `src/main/java/com/workshop/foodfacts/dto/NutritionSummaryDto.java` - Naehrwert-DTO

---

## Schritt 1: Projekt kennenlernen (3 Min)

Schaut euch die Klassen kurz an. Wichtige Fragen:

- Welche Operationen bietet der `ProductService`?
- Welche Daten liefert das `Product`-Modell?
- Was berechnet der `NutritionService`?

> **Tipp:** Ihr koennt den KI-Assistenten auch direkt fragen:
> *"Gib mir eine kurze Uebersicht ueber die Struktur dieses Projekts."*

---

## Schritt 2: REST Controller generieren (7 Min)

### Prompt 1 - Controller erstellen:

```
Erstelle einen REST-Controller "ProductController.java" im Paket
com.workshop.foodfacts.controller mit folgenden Endpunkten:

1. GET /api/products/{barcode} - Produkt per Barcode abrufen (nutzt ProductService)
2. GET /api/products/search?query=...&page=1&pageSize=20 - Produktsuche mit Paginierung
3. GET /api/products/{barcode}/nutrition - Naehrwert-Zusammenfassung eines Produkts

Verwende javax/jakarta Annotationen (@Path, @GET, @Produces etc.) oder
Spring-Annotationen (@RestController, @GetMapping etc.).
Fuege sinnvolle HTTP-Statuscodes hinzu (200, 404 wenn Produkt nicht gefunden).
```

### Was pruefen?

- [ ]  Werden `ProductService` und `NutritionService` korrekt injiziert?
- [ ]  Gibt der `/api/products/{barcode}` Endpunkt 404 zurueck wenn das Produkt nicht existiert?
- [ ]  Sind die Query-Parameter fuer die Suche korrekt (query, page, pageSize)?
- [ ]  Stimmen die Return-Typen (Product, SearchResponse, NutritionSummaryDto)?

---

## Schritt 3: OpenAPI Spezifikation generieren (5 Min)

### Prompt 2 - OpenAPI YAML:

```
Erstelle eine OpenAPI 3.0 Spezifikation im YAML-Format fuer den ProductController.
Beruecksichtige dabei:
- Alle Felder aus Product.java als Schema-Definition
- NutritionSummaryDto als eigenes Schema
- SearchResponse mit Paginierung
- Die API-Basis-URL ist http://localhost:8080
- Fuege fuer jeden Endpunkt eine kurze Beschreibung auf Deutsch hinzu
```

### Was pruefen?

- [ ]  Sind alle 3 Endpunkte enthalten?
- [ ]  Stimmen die Schema-Feldnamen mit den `@JsonProperty`-Werten ueberein?
  (z.B. `product_name` statt `productName`)
- [ ]  Ist `NutrientLevels` als verschachteltes Schema definiert?
- [ ]  Sind die Datentypen korrekt? (`Double` → `number`, `List<String>` → `array of string`)

---

## Schritt 4: Iterativ verbessern (5 Min)

### Prompt 3 - Beispieldaten:

```
Fuege realistische Beispielwerte (example) fuer das Product-Schema hinzu.
Verwende Nutella als Beispielprodukt:
- Barcode: 3017620422003
- Marke: Ferrero
- Nutri-Score: E
- Kalorien: 539 kcal/100g
```

### Prompt 4 - Fehler-Responses:

```
Ergaenze die OpenAPI-Spec um Fehler-Responses:
- 404 Not Found: Wenn ein Produkt nicht existiert
- 400 Bad Request: Wenn der Suchbegriff fehlt
- 500 Internal Server Error: Bei API-Fehlern
Erstelle ein ErrorResponse-Schema mit den Feldern: status, message, timestamp.
```

---

## Ergebnis validieren

1. Kopiert die fertige YAML-Datei
2. Oeffnet den [Swagger Editor](https://editor.swagger.io/)
3. Fuegt die YAML ein und prueft auf Fehler
4. Testet die "Try it out"-Funktion (funktioniert nur mit laufendem Server)
