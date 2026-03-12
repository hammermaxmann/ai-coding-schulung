# Use Case 3: Refactoring der God Class

**Schwierigkeit:** Mittel | **Dauer:** ~20 Minuten | **IDE:** Eclipse

## Lernziele

- KI nutzen, um komplexen Code zu verstehen und zu erklaeren
- Code Smells automatisch erkennen lassen
- Schrittweises, sicheres Refactoring mit KI-Unterstuetzung durchfuehren
- JSON-Rueckwaertskompatibilitaet bei Refactorings bewahren

## Szenario

`Product.java` ist eine **250+ Zeilen "God Class"** mit 25+ Feldern, die verschiedenste
Verantwortlichkeiten vermischt: Stammdaten, Naehrwerte, Allergene, Bewertungen, Bilder, Metadaten.
Zusaetzlich gibt es ein **nahezu identisches Duplikat** in `ProductDetailDto.java`.

Eure Aufgabe: Lasst die KI den Code analysieren und schrittweise refaktorisieren.

## Relevante Dateien

Oeffnet diese Dateien in Eclipse:

- `src/main/java/com/workshop/foodfacts/model/Product.java` - **Die God Class**
- `src/main/java/com/workshop/foodfacts/dto/ProductDetailDto.java` - **Das Duplikat**
- `src/main/java/com/workshop/foodfacts/service/ProductService.java` - Nutzt Product.java
- `src/main/java/com/workshop/foodfacts/service/SearchService.java` - Duplizierte Such-Logik

---

## Teil A: Code-Analyse (7 Min)

### Prompt 1 - Code verstehen:

```
Analysiere die Klasse Product.java ausfuehrlich:
1) Welche verschiedenen Verantwortlichkeiten (Concerns) sind in dieser Klasse vermischt?
2) Welche logischen Gruppen von Feldern erkennst du?
3) Welche Code Smells fallen dir auf?
4) Wie wuerdest du diese Klasse nach dem Single Responsibility Principle aufteilen?

Antworte auf Deutsch und liste die Feldgruppen mit ihren Feldnamen auf.
```

**Erwartete Antwort der KI - mindestens diese Gruppen:**


| Gruppe         | Felder                                                                                                                  | Concern         |
| -------------- | ----------------------------------------------------------------------------------------------------------------------- | --------------- |
| Identifikation | barcode, productName, productNameDe, brands, quantity, packaging                                                        | Stammdaten      |
| Naehrwerte     | energyKcal100g, fat100g, saturatedFat100g, carbohydrates100g, sugars100g, fiber100g, proteins100g, salt100g, sodium100g | Ernaehrung      |
| Allergene      | allergens, allergensTags, traces, tracesTags, ingredientsText, ingredientsTextDe                                        | Inhaltsstoffe   |
| Bewertungen    | nutriScoreGrade, nutriScoreScore, novaGroup, ecoScoreGrade, ecoScoreScore, nutrientLevels                               | Scores          |
| Kategorien     | categories, categoriesTags, labels, labelsTags, stores, countries, countriesTags                                        | Klassifizierung |
| Bilder         | imageUrl, imageSmallUrl, imageFrontUrl, imageNutritionUrl, imageIngredientsUrl                                          | Medien          |
| Metadaten      | createdTimestamp, lastModifiedTimestamp, creator, editorsTags, completeness                                             | Verwaltung      |

### Prompt 2 - Duplikat erkennen:

```
Vergleiche Product.java und ProductDetailDto.java Zeile fuer Zeile.
- Welche Felder sind identisch?
- Welche Felder fehlen in ProductDetailDto?
- Gibt es einen guten Grund fuer die Existenz beider Klassen?
- Schlage eine Loesung vor, um die Duplikation zu beseitigen.
```

---

## Teil B: Refactoring planen (5 Min)

### Prompt 3 - Plan erstellen:

```
Erstelle einen detaillierten Refactoring-Plan fuer Product.java.

Ziel: Aufteilen in fokussierte Klassen.

WICHTIGE EINSCHRAENKUNG: Die JSON-Serialisierung muss identisch bleiben!
Der API-Response darf sich nicht aendern. Alle @JsonProperty-Annotationen
muessen erhalten bleiben.

Schlage konkrete Klassennamen und Pakete vor.
Nummeriere die Schritte in der Reihenfolge, in der sie ausgefuehrt werden sollen.
Beginne mit dem sichersten Schritt (geringestes Risiko).
```

**Erwarteter Plan (ungefaehr):**

1. `NutrientLevels` aus innerer Klasse in eigene Datei extrahieren (sicherstes Refactoring)
2. `NutritionInfo.java` erstellen (Naehrwert-Felder)
3. `AllergenInfo.java` erstellen (Allergen-Felder)
4. `ProductImages.java` erstellen (Bild-URLs)
5. `Product.java` nutzt die neuen Klassen mit `@JsonUnwrapped`
6. `ProductDetailDto.java` eliminieren (durch Product ersetzen)

---

## Teil C: Refactoring umsetzen (8 Min)

### Prompt 4 - Innere Klasse extrahieren (einfachster Schritt):

```
Extrahiere die innere Klasse NutrientLevels aus Product.java in eine
eigene Datei NutrientLevels.java im Paket com.workshop.foodfacts.model.
Product.java soll weiterhin auf NutrientLevels verweisen.
Stelle sicher, dass die JSON-Serialisierung identisch bleibt.
```

> **Tipp fuer Eclipse:** Nach dem Extrahieren `Ctrl+Shift+O` druecken fuer "Organize Imports"

### Prompt 5 - Naehrwerte extrahieren:

```
Erstelle eine neue Klasse NutritionInfo.java im model-Paket mit diesen Feldern
aus Product.java:
- energyKcal100g, energy100g, fat100g, saturatedFat100g
- carbohydrates100g, sugars100g, fiber100g
- proteins100g, salt100g, sodium100g

Product.java soll ein Feld "private NutritionInfo nutritionInfo" verwenden.

WICHTIG: Die JSON-Struktur muss FLACH bleiben (kein verschachteltes
"nutritionInfo"-Objekt im JSON). Verwende @JsonUnwrapped in Product.java.
Alle @JsonProperty-Annotationen muessen in NutritionInfo.java erhalten bleiben.
```

### Prompt 6 - Duplikat beseitigen:

```
ProductDetailDto.java ist ein Duplikat von Product.java.
Loesche ProductDetailDto.java und ersetze alle Verwendungen durch Product.
Zeige mir, welche Dateien angepasst werden muessen.
```

---

## Ergebnis pruefen

Nach dem Refactoring sollte das Projekt weiterhin kompilieren:

```bash
mvn compile
```

Falls ihr Use Case 2 bereits gemacht habt, koennt ihr auch die Tests ausfuehren:

```bash
mvn test
```

### Checkliste

- [ ]  `Product.java` ist deutlich kuerzer (unter 150 Zeilen)
- [ ]  `NutrientLevels.java` existiert als eigene Datei
- [ ]  `NutritionInfo.java` existiert mit allen Naehrwert-Feldern
- [ ]  `@JsonUnwrapped` wird in Product.java verwendet
- [ ]  `ProductDetailDto.java` ist geloescht oder deutlich reduziert
- [ ]  `mvn compile` ist erfolgreich
- [ ]  Die JSON-Ausgabe ist identisch zum Originalzustand

1. nfo.java mit den Feldern: allergens, allergensTags,
   traces, tracesTags, ingredientsText, ingredientsTextDe.
   Verwende @JsonUnwrapped in Product.java.

   2. **Service-Duplikation beseitigen:**

      ```
      SearchService.java dupliziert die Such-Logik aus ProductService.searchProducts().
      Entferne die Duplikation: Entweder SearchService loeschen und nur ProductService
      verwenden, oder die gemeinsame Logik in eine Hilfsklasse extrahieren.
      ```
   3. **Klassendiagramm:**

      ```
      Erstelle ein Mermaid-Klassendiagramm, das die Struktur von Product.java
      VORHER (God Class) und NACHHER (aufgeteilte Klassen) zeigt.
      ```
   4. **Records verwenden (Java 17):**

      ```
      Koennte NutritionInfo.java als Java Record implementiert werden?
      Was sind die Vor- und Nachteile gegenueber einer normalen Klasse
      mit Jackson-Annotationen?
      ```

---

## Erkenntnisse & Diskussion

- Hat die KI die Code Smells korrekt identifiziert?
- Wie gut hat die KI die `@JsonUnwrapped`-Loesung vorgeschlagen (oder musstet ihr nachhelfen)?
- Hat die KI an die JSON-Rueckwaertskompatibilitaet gedacht, ohne dass ihr sie daran erinnern musstet?
- Wie wuerdet ihr das Refactoring in einem echten Projekt absichern (Tests, Code Review)?
- War der schrittweise Ansatz (erst analysieren, dann planen, dann umsetzen) hilfreich?
