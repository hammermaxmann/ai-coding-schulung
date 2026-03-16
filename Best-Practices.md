# Best Practices: KI-gestuetztes Coding

## Den richtigen Modus waehlen

- **Agenten-Modus fuer komplexe Aufgaben** — Agenten (z.B. Claude Code, Cursor Agent, Copilot Agent) koennen selbststaendig mehrere Dateien lesen, editieren und Terminal-Befehle ausfuehren. Ideal fuer Refactoring, Feature-Implementierung oder Debugging ueber mehrere Klassen hinweg.
- **Chat-Modus fuer gezielte Fragen** — Fuer Code-Erklaerungen, Design-Entscheidungen oder einzelne Methoden reicht der Chat. Weniger Token-Verbrauch, schnellere Antworten.
- **Inline-Completion fuer Boilerplate** — Tab-Completion (Copilot, Supermaven) eignet sich fuer Getter/Setter, bekannte Patterns und repetitiven Code. Nicht fuer komplexe Logik. Ein Kommentar vor den nächsten Zeilen gibt der Code-Completion ebenfalls mehr Kontext.

### Planungs-Modus vor der Umsetzung

- **Erst planen, dann coden** — Viele Assistenten bieten einen Planungs-Modus (z.B. `/plan` in Copilot/Claude Code, oder Keyword "Plan" im Prompt). Der Assistent analysiert die Aufgabe, liest relevante Dateien und erstellt einen Umsetzungsplan — ohne sofort Code zu aendern.
- **Komplexe Aufgaben immer mit Plan starten** — Bei Refactorings, neuen Features oder unbekanntem Code: Ein Plan kann einfacher überprüft, gespeichert oder geändert werden als viele Einzelschritte.

## MCP Server nutzen

**Datenbank-Zugriff via MCP** — Ein MCP Server fuer die Datenbank erlaubt dem Assistenten, Schemas zu lesen und SQL direkt auszufuehren. So kann er Queries schreiben, die zum tatsaechlichen Schema passen — statt zu raten.

**Dokumentation via MCP** — Tools wie Context7 liefern aktuelle Library-Dokumentation direkt in den Kontext. Der Assistent arbeitet mit der richtigen API-Version statt mit veraltetem Trainingswissen.

**Eigene Tools bereitstellen** — MCP Server koennen auch projektspezifische Tools anbieten (z.B. Deployment-Status, Testlaeufe). Je mehr der Assistent selbst pruefen kann, desto besser das Ergebnis.

## Kontext gezielt aufbauen

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

## MCP Server konfigurieren

MCP Server werden **nicht** im Projekt-Verzeichnis konfiguriert — die Konfiguration ist tool- und IDE-spezifisch:


| Tool                    | Konfigurationspfad                                         | Root-Key       |
| ----------------------- | ---------------------------------------------------------- | -------------- |
| **Copilot in IntelliJ** | `~/.config/github-copilot/intellij/mcp.json`               | `"servers"`    |
| **Copilot CLI**         | `~/.copilot/mcp-config.json`                               | `"mcpServers"` |
| **VS Code**             | `~/.vscode/mcp.json` oder projekt-lokal `.vscode/mcp.json` | `"mcpServers"` |
| **Claude Code**         | `~/.claude/mcp.json` oder projekt-lokal `.claude/mcp.json` | `"mcpServers"` |

> **Hinweis:** Das Copilot-Plugin in IntelliJ und die Copilot CLI unterstuetzen aktuell keine projekt-lokalen MCP-Konfigurationen. Die `mcp.json` muss im jeweiligen globalen Pfad im Home-Verzeichnis abgelegt werden.

### Beispiel (IntelliJ / Copilot)

```json
{
  "servers": {
    "context7": {
      "type": "http",
      "url": "https://mcp.context7.com/mcp"
    },
    "food-facts-db": {
      "type": "local",
      "command": "npx",
      "args": ["-y", "@modelcontextprotocol/server-postgres",
               "postgresql://workshop:workshop@127.0.0.1:5432/foodfacts"]
    }
  }
}
```

Eine Beispiel-Konfiguration liegt im Projekt-Root unter `mcp.example.json` — diese kann als Vorlage in den jeweiligen Konfigurationspfad kopiert werden.
