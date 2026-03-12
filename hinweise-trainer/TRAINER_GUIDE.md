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
| Eclipse findet Tests nicht  | `src/test/java` muss als Source Folder markiert sein                                      |

### KI-spezifische Probleme


| Problem                                             | Loesung                                                                     |
| --------------------------------------------------- | --------------------------------------------------------------------------- |
| KI generiert JUnit 4 statt JUnit 5                  | Prompt ergaenzen: "Verwende JUnit 5 (Jupiter) Syntax"                       |
| KI verwendet Java-Feldnamen statt @JsonProperty     | Explizit darauf hinweisen: "Verwende die @JsonProperty-Werte als Feldnamen" |
| KI schlaegt Spring Boot vor (wir haben kein Spring) | Prompt praezisieren: "Ohne Spring Framework, nur mit javax/plain Java"      |
| KI ignoriert JSON-Kompatibilitaet beim Refactoring  | Prompt ergaenzen: "Die JSON-Serialisierung muss identisch bleiben!"         |
| KI erzeugt nicht-kompilierbaren Code                | Fehlermeldung an die KI zurueckgeben - gute Lernmoeglichkeit!               |
| KI kennt @JsonUnwrapped nicht                       | Hinweis geben: "Recherchiere die Jackson-Annotation @JsonUnwrapped"         |

---

## Gespraechspunkte nach jedem Use Case

### Nach Use Case 1 (OpenAPI)

- "Hat jemand bemerkt, dass die KI falsche Feldnamen verwendet hat?"
- "Wie viele Iterationen habt ihr gebraucht?"
- "Wuerde man in einem echten Projekt die Spec so generieren oder lieber von Hand pflegen?"
- **Kernbotschaft:** KI ist gut fuer den Startpunkt, aber das Ergebnis muss immer geprueft werden.

### Nach Use Case 2 (Tests)

- "Waren die generierten Tests wirklich aussagekraeftig, oder nur Alibi-Tests?"
- "Hat die KI Randfaelle beruecksichtigt, an die ihr selbst nicht gedacht haettet?"
- "JUnit 4 vs. 5 - wie wichtig ist es, den richtigen Standard vorzugeben?"
- **Kernbotschaft:** KI nimmt die Fleissarbeit ab, aber Testdesign bleibt menschliche Aufgabe.

### Nach Use Case 3 (Refactoring)

- "Hat die KI den Code besser verstanden als erwartet?"
- "Wie wichtig war der Hinweis zur JSON-Kompatibilitaet?"
- "Wuerdet ihr in einem echten Projekt ein KI-Refactoring ohne Code Review mergen?"
- **Kernbotschaft:** KI kann technische Schulden abbauen, aber Architektur-Entscheidungen bleiben beim Entwickler.

---

## Allgemeine Diskussionspunkte (Wrap-up)

1. **Kontext ist entscheidend** - Je mehr relevante Dateien der Assistent sieht, desto besser. Das ist der Hauptvorteil gegenueber einem normalen Chatbot.
2. **Iteratives Arbeiten** - Selten ist der erste Prompt perfekt. Verfeinern und nachfragen ist normal und erwuenscht.
3. **Kritisches Pruefen** - KI-generierter Code muss immer reviewed werden. Die KI macht plausible, aber manchmal falsche Annahmen.
4. **Prompt-Engineering** - Praezise Prompts liefern bessere Ergebnisse. Einschraenkungen (wie JSON-Kompatibilitaet) muessen explizit genannt werden.
5. **Wann KI nutzen, wann nicht?**

   - Gut fuer: Boilerplate, Tests, Dokumentation, Code-Erklaerung, erste Entwuerfe
   - Vorsicht bei: Sicherheitskritischem Code, komplexer Geschaeftslogik, Architekturentscheidungen

---

## Fallback-Plan

Falls das KI-Tool nicht verfuegbar ist (Lizenz-Problem, Netzwerk-Ausfall):

1. Die Anleitungen funktionieren auch als **manuelle Uebungen**
2. Die Prompts werden zu **Diskussionsfragen**: "Was wuerdet ihr erwarten, dass die KI hier generiert?"
3. Alternativ: **ChatGPT/Claude im Browser** als Fallback (Code manuell kopieren)
4. Die `beispiel-openapi-auszug.yaml` und `zielstruktur.md` zeigen die erwarteten Ergebnisse

---

## Hinweise zum Projekt-Design

Die absichtlichen "Fehler" im Code:


| Absichtliches Problem                           | Paedagogischer Zweck            |
| ----------------------------------------------- | ------------------------------- |
| Product.java: 250+ Zeilen, 25+ Felder           | God Class als Refactoring-Ziel  |
| ProductDetailDto ≈ Product (Duplikat)          | Code-Duplikation erkennen       |
| SearchService dupliziert ProductService         | Service-Duplikation erkennen    |
| ProductService: Mapping + Validierung + Caching | Vermischte Verantwortlichkeiten |
| Keine Tests                                     | Testgenerierung ueben           |
| Kein REST-Controller                            | API-Design ueben                |
| NutrientLevels als innere Klasse                | Extract Class Refactoring       |

Diese Probleme sind **nicht offensichtlich schlecht** - sie repraesentieren typische
"technische Schulden" aus gewachsenen Projekten. Das macht die Uebung realistisch.
