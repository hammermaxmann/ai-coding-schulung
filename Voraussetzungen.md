# Trainer Guide - AI Coding Workshop

## Vorbereitung (vor dem Workshop)

### Checkliste pro Teilnehmer-Rechner

- [ ]  JDK 17+ installiert (`java -version`)
- [ ]  Maven 3.8+ installiert (`mvn -version`)
- [ ]  IDE installiert (IntelliJ IDEA)
- [ ]  GIT installiert für (https://git-scm.com/install/)
- [ ]  Github Copilot Zugang vorhanden
- [ ]  Github Copilot Plugin in der IDE installiert & authentifiziert
- [ ]  Internetzugang vorhanden

## Schnelltest

```bash
cd food-facts-service
mvn compile
# Sollte "BUILD SUCCESS" zeigen

# API-Zugriff testen
curl -s "https://world.openfoodfacts.org/api/v2/product/3017620422003.json" | head -c 200
# Sollte JSON mit Nutella-Daten zeigen
```

## Haeufige Probleme & Loesungen

### Setup-Probleme


| Problem                     | Loesung                                                                                   |
| --------------------------- | ----------------------------------------------------------------------------------------- |
| `mvn compile` schlaegt fehl | JDK-Version pruefen (muss 17+ sein).`JAVA_HOME` richtig gesetzt?                          |
| Maven nicht gefunden        | `PATH` pruefen, oder IDE-internen Maven nutzen                                            |
| KI-Plugin nicht aktiv       | Lizenz-Status pruefen, ggf. neu anmelden                                                  |
| Kein Internetzugang         | Open Food Facts API braucht Internet. Notfalls JSON-Beispieldateien offline bereitstellen |
| IntelliJ findet Tests nicht | `src/test/java` muss als Test Sources Root markiert sein (Rechtsklick > Mark Directory as > Test Sources Root) |

### KI-spezifische Probleme


| Problem                                             | Loesung                                                                     |
| --------------------------------------------------- | --------------------------------------------------------------------------- |
| KI generiert JUnit 4 statt JUnit 5                  | Prompt ergaenzen: "Verwende JUnit 5 (Jupiter) Syntax" / "Nutze Context7"    |
| KI verwendet Java-Feldnamen statt @JsonProperty     | Explizit darauf hinweisen: "Verwende die @JsonProperty-Werte als Feldnamen" |
| KI schlaegt Spring Boot vor (wir haben kein Spring) | Spring hinzufügen oder "Ohne Spring Framework, nur mit javax/plain Java"  |
| KI ignoriert JSON-Kompatibilitaet beim Refactoring  | Prompt ergaenzen: "Die JSON-Serialisierung muss identisch bleiben!"         |
| KI erzeugt nicht-kompilierbaren Code                | Fehlermeldung an die KI zurueckgeben - gute Lernmöglichkeit!               |
| KI kennt @JsonUnwrapped nicht                       | Hinweis geben: "Recherchiere die Jackson-Annotation @JsonUnwrapped"         |

---


Hinweise zum Projekt-Design

Die absichtlichen "Fehler" im Code:


| Absichtliches Problem                           | Paedagogischer Zweck            |
| ----------------------------------------------- | ------------------------------- |
| Product.java: 250+ Zeilen, ~50 Felder           | God Class als Refactoring-Ziel  |
| ProductDetailDto ≈ Product (Duplikat)          | Code-Duplikation erkennen       |
| SearchService dupliziert ProductService         | Service-Duplikation erkennen    |
| ProductService: Mapping + Validierung + Caching | Vermischte Verantwortlichkeiten |
| Keine Tests                                     | Testgenerierung ueben           |
| Kein REST-Controller                            | API-Design ueben                |
| NutrientLevels als innere Klasse                | Extract Class Refactoring       |

Diese Probleme sind **nicht offensichtlich schlecht** - sie repraesentieren typische
"technische Schulden" aus gewachsenen Projekten. Das macht die Uebung realistisch.
