# Use Case 2: JUnit Tests mit KI-Unterstuetzung erstellen

**Schwierigkeit:** Leicht | **Dauer:** ~20 Minuten | **IDE:** IntelliJ oder Eclipse

## Lernziele

- KI fuer die Generierung von Unit-Tests einsetzen
- Mocking mit Mockito verstehen und anwenden
- Testqualitaet bewerten und verbessern

## Szenario

Das Projekt hat **keine einzige Testklasse**. Null Tests, null Coverage.
Eure Aufgabe: Lasst die KI Tests generieren und bewertet die Qualitaet.

## Vorbereitung

Die Test-Dependencies (JUnit 5, Mockito) sind **bereits in der `pom.xml`** enthalten.

Erstellt nur das Test-Verzeichnis, falls es noch nicht existiert:

```bash
mkdir -p src/test/java/com/workshop/foodfacts
```

In IntelliJ: Rechtsklick auf `src` > `New` > `Directory` > `test/java/com/workshop/foodfacts`

In Eclipse: Rechtsklick auf Projekt > `New` > `Source Folder` > `src/test/java`

---

## Schritt 1: Zielklasse auswaehlen (2 Min)

Oeffnet diese Dateien in der IDE:

**Fuer DTO-Tests:**

- `src/main/java/com/workshop/foodfacts/model/Product.java` - Komplex, 25+ Felder, Jackson-Annotationen

**Fuer Logik-Tests:**

- `src/main/java/com/workshop/foodfacts/service/NutritionService.java` - Hat echte Berechnungslogik

---

## Schritt 2: DTO-Tests generieren (7 Min)

### Prompt 1 - Product.java testen:

```
Erstelle eine JUnit 5 Testklasse fuer Product.java. Teste folgende Aspekte:

1) Getter und Setter fuer die wichtigsten Felder (productName, brands, barcode,
   energyKcal100g, nutriScoreGrade, allergensTags)
2) Jackson JSON-Serialisierung: Erstelle ein Product-Objekt, serialisiere es
   zu JSON und pruefe, dass die Feldnamen den @JsonProperty-Annotationen
   entsprechen (z.B. "product_name" statt "productName")
3) Jackson JSON-Deserialisierung: Nimm einen realistischen JSON-String
   (Nutella-Daten) und deserialisiere ihn zu einem Product-Objekt
4) Null-Werte: Teste, dass ein leeres Product-Objekt keine NullPointerException wirft
5) Die innere Klasse NutrientLevels
```

### Was pruefen?

- [ ]  Verwendet die KI `@Test` aus `org.junit.jupiter.api` (JUnit 5), NICHT aus `org.junit` (JUnit 4)?
- [ ]  Wird `ObjectMapper` mit `DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES = false` konfiguriert?
- [ ]  Stimmen die JSON-Feldnamen im Test-String mit den `@JsonProperty`-Werten ueberein?
- [ ]  Sind die Assertions aussagekraeftig (`assertEquals` mit konkreten Werten, nicht nur `assertNotNull`)?

---

## Schritt 3: Service-Tests mit Mockito (8 Min)

### Prompt 2 - NutritionService testen:

```
Erstelle JUnit 5 Tests mit Mockito fuer NutritionService.java. Teste:

1) getNutritionSummary() mit einem vollstaendigen Produkt (alle Naehrwerte gesetzt)
2) getNutritionSummary() mit null als Parameter (soll null zurueckgeben)
3) getNutritionSummary() mit einem Produkt OHNE Naehrwertdaten (alle Werte null)
4) calculateHealthRating(): Teste verschiedene Szenarien:
   - Gesundes Produkt (wenig Zucker, wenig Fett, viel Protein) -> "Sehr gut" oder "Gut"
   - Ungesundes Produkt (viel Zucker, viel Fett, viel Salz) -> "Schlecht" oder "Sehr schlecht"
5) getNovaDescription(): Teste alle NOVA-Gruppen (1-4) und den Fall null
```

### Prompt 3 - Verfeinerung:

```
Die Tests sind zu oberflaechtlich. Verbessere sie:
1) Verwende @ParameterizedTest mit @CsvSource fuer calculateHealthRating()
   mit mindestens 5 verschiedenen Naehrwert-Kombinationen
2) Pruefe die exakten String-Werte der Gesundheitsbewertung
3) Teste den Grenzfall: Was passiert wenn nur EIN Naehrwert gesetzt ist
   und alle anderen null sind?
```

---

## Schritt 4: Tests ausfuehren & verbessern (3 Min)

### Tests ausfuehren

```bash
# Alle Tests ausfuehren
mvn test

# Nur eine bestimmte Testklasse
mvn test -Dtest=ProductTest

# Mit ausfuehrlicher Ausgabe
mvn test -Dtest=NutritionServiceTest -X
```

In der IDE: Rechtsklick auf die Testklasse > `Run Tests`

### Feedback-Schleife

Falls Tests fehlschlagen, kopiert die Fehlermeldung und gebt sie der KI:

```
Der folgende Test schlaegt fehl:
[FEHLERMELDUNG HIER EINFUEGEN]

Analysiere den Fehler und korrigiere den Test.
```

---

## Erwartetes Ergebnis

Nach diesem Use Case solltet ihr haben:

- **`ProductTest.java`** mit ~8-12 Tests:

  - Getter/Setter fuer Hauptfelder
  - Jackson Serialisierung (JSON-Feldnamen pruefen)
  - Jackson Deserialisierung (realistischer JSON-String)
  - Null-Sicherheit
  - NutrientLevels innere Klasse
- **`NutritionServiceTest.java`** mit ~8-10 Tests:

  - getNutritionSummary() Happy Path
  - getNutritionSummary() mit null / leeren Daten
  - calculateHealthRating() mit verschiedenen Szenarien
  - getNovaDescription() fuer alle Gruppen
  - Parametrisierte Tests fuer Grenzfaelle
- Alle Tests **gruen** bei `mvn test`
