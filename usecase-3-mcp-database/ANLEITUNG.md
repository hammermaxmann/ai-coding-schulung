# Use Case 3: Datenbank-Zugriff mit MCP-Server

## Ziel

In diesem Use Case lernt ihr, wie ein AI-Coding-Assistent direkt auf eine SQL-Datenbank zugreifen kann. Dazu verwenden wir eine PostgreSQL-Datenbank mit Lebensmitteldaten und den **Google GenAI Toolbox** als MCP-Server (Model Context Protocol).

## Voraussetzungen

- Docker Desktop installiert und gestartet
- IntelliJ IDEA mit GitHub Copilot Plugin
- Terminal / Kommandozeile

---

## Schritt 1: Datenbank starten (5 Min)

### 1.1 Docker Container starten

Oeffnet ein Terminal im Ordner `usecase-4-mcp-database` und startet die Datenbank:

```bash
docker compose up -d
```

### 1.2 Pruefen ob die Datenbank laeuft

```bash
docker compose ps
```

Ihr solltet einen Container `foodfacts-db` mit Status `healthy` sehen.

### 1.3 Optional: Datenbank manuell pruefen

```bash
docker compose exec postgres psql -U workshop -d foodfacts -c '\dt'
```

Erwartete Ausgabe: 5 Tabellen (`produkte`, `naehrwerte`, `kategorien`, `produkt_kategorien`, `naehrstoff_levels`).

```bash
docker compose exec postgres psql -U workshop -d foodfacts -c 'SELECT produkt_name, marke FROM produkte'
```

Erwartete Ausgabe: 5 Produkte (Nutella, Haribo, Milka, Fritz-Kola, Ritter Sport).

---

## Schritt 2: MCP-Server einrichten (5 Min)

Der MCP-Server ermoeglicht es GitHub Copilot, direkt SQL-Abfragen auf der Datenbank auszufuehren.

### 2.1 GenAI Toolbox installieren

**Option A: Via npx (empfohlen)**

Keine Installation noetig - wird automatisch beim Start geladen.

**Option B: Via Homebrew (macOS)**

```bash
brew install mcp-toolbox
```

### 2.2 MCP-Server in IntelliJ konfigurieren

Die MCP-Konfiguration muss in den Copilot-Einstellungen hinterlegt werden. Oeffnet das Projekt in IntelliJ und konfiguriert den MCP-Server unter `Settings > Tools > GitHub Copilot > MCP Servers`.

> **Hinweis:** Falls der MCP-Server nicht automatisch erkannt wird, IntelliJ neustarten und die Copilot-Einstellungen pruefen.

### 2.3 Verbindung testen

Oeffnet den Copilot Chat in IntelliJ und fragt:

```
Welche Tabellen gibt es in der foodfacts-Datenbank?
```

Copilot sollte die 5 Tabellen auflisten koennen.

---

## Schritt 3: Datenbank mit AI erkunden (10 Min)

Jetzt wird es spannend! Nutzt den Copilot Chat, um die Datenbank zu erkunden. Stellt verschiedene Fragen:

### Einfache Abfragen

Probiert diese Prompts im Copilot Chat:

```
Zeige mir alle Produkte mit ihrem Nutri-Score
```

```
Welches Produkt hat die meisten Kalorien pro 100g?
```

```
Finde alle Produkte der Marke "Milka"
```

### Komplexere Abfragen

```
Vergleiche die Naehrwerte von Nutella und Milka Alpenmilch
```

```
Welche Produkte enthalten Milch als Allergen?
```

```
Erstelle eine Rangliste der Produkte nach Zuckergehalt, absteigend
```

### Schema analysieren

```
Analysiere das Datenbankschema. Welche Tabellen gibt es und wie haengen sie zusammen?
```

```
Findest du Probleme oder Verbesserungsmoeglichkeiten im Datenbankschema?
```

> **Tipp:** Achtet darauf, wie der AI-Assistent mit den absichtlichen Schema-Problemen umgeht!

---

## Schritt 4: Schema-Probleme entdecken (5 Min - Bonus)

Das Datenbankschema enthaelt absichtliche Designprobleme. Koennt ihr sie mit Hilfe des AI-Assistenten finden?

### Prompts zum Ausprobieren

```
Welche Schema-Probleme siehst du in der foodfacts-Datenbank? Erstelle eine Liste mit Verbesserungsvorschlaegen.
```

```
Warum sind die Allergene als kommaseparierter Text gespeichert? Wie wuerde man das besser loesen?
```

```
Generiere ein SQL-Migrations-Script, das die Timestamps von TEXT auf TIMESTAMP konvertiert
```

```
Erstelle einen Vorschlag fuer eine normalisierte Allergene-Tabelle mit den bestehenden Daten
```

### Bekannte Probleme im Schema

<details>
<summary>Klicken zum Aufdecken</summary>

1. **Deutsch/Englisch gemischt**: `nova_gruppe` vs. `eco_score`
2. **Allergene denormalisiert**: Kommaseparierter TEXT statt eigene Tabelle
3. **FK auf barcode statt id**: `naehrwerte.produkt_barcode` verweist auf `produkte.barcode`
4. **Timestamps als TEXT**: `erstellt_am` und `aktualisiert_am` sind TEXT statt TIMESTAMP
5. **Fehlende Indizes**: Junction Table `produkt_kategorien` hat keine separaten Indizes
6. **Kein CASCADE**: Foreign Keys ohne ON DELETE CASCADE
7. **Inkonsistente Spaltennamen**: `kcal` statt `energie_kcal`

</details>

---

## Aufraeumen

Nach dem Workshop die Container stoppen:

```bash
docker compose down
```

Um auch die Daten zu loeschen:

```bash
docker compose down -v
```

---

## Hilfe bei Problemen

| Problem | Loesung |
|---------|---------|
| `docker compose up` schlaegt fehl | Ist Docker Desktop gestartet? |
| Port 5432 belegt | `docker compose down` und erneut starten, oder Port in docker-compose.yml aendern |
| MCP-Server wird nicht erkannt | IntelliJ neustarten, MCP-Server-Konfiguration in Copilot-Einstellungen pruefen |
| Copilot fuehrt keine SQL aus | MCP-Server-Status in den Copilot-Einstellungen pruefen |
