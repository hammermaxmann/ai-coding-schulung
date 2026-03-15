# Best Practices: KI-gestuetztes Coding

## Kontext ist alles

- **agents.md / CLAUDE.md anlegen** — Eine kurze Datei im Projekt-Root, die Techstack, Konventionen und Projektstruktur beschreibt. Wenige Zeilen reichen. So versteht der Assistent das Projekt sofort, ohne dass ihr es jedes Mal erklaeren muesst.
- **Relevante Dateien oeffnen** — Der Assistent arbeitet besser, wenn er die betroffenen Klassen, Interfaces und Tests sieht. Oeffnet vor dem Prompt die 2-3 wichtigsten Dateien.
- **Token sparsam einsetzen** — Haltet Kontext-Dateien (agents.md, Rules) kurz und praezise. Jedes ueberfluessige Wort kostet Tokens und verwuessert den Fokus.

## Prompting

- **Kleine Schritte statt Mammut-Prompts** — Ein Prompt pro Aufgabe. Erst den Controller generieren, dann die Tests, dann das Refactoring. So bleibt das Ergebnis pruefbar.
- **Iterativ verfeinern** — Der erste Output ist selten perfekt. Gebt gezieltes Feedback: "Nutze Constructor Injection statt @Autowired" statt "Mach es besser".
- **Beispiele mitgeben** — Zeigt dem Assistenten ein bestehendes Muster (z.B. einen vorhandenen Test), damit er den Stil uebernimmt.

## Qualitaet sichern

- **Generiertes Review wie fremden Code** — Nie blind uebernehmen. Prueft Logik, Sicherheit und Lesbarkeit.
- **Tests zuerst generieren** — Tests vor dem Refactoring erstellen. So habt ihr ein Sicherheitsnetz, das Regressionen sofort aufdeckt.
- **Keine Secrets committen** — API-Keys, Tokens und Passwoerter gehoeren in Umgebungsvariablen oder `.env`-Dateien (mit `.gitignore`), nie in den Code.
