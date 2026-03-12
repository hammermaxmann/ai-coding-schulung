# GitHub Copilot CLI - Wichtige Befehle

## Terminal-Befehle

| Befehl | Beschreibung |
|--------|-------------|
| `copilot` | Startet die interaktive Oberflaeche |
| `copilot init` | Initialisiert Custom Instructions fuer das aktuelle Repository |
| `copilot login` | Authentifizierung via GitHub OAuth |
| `copilot logout` | Abmelden und gespeicherte Credentials entfernen |
| `copilot update` | Neueste Version herunterladen und installieren |
| `copilot version` | Versionsinformationen anzeigen |
| `copilot help [topic]` | Hilfe anzeigen (topics: config, commands, environment, logging, permissions) |
| `copilot plugin` | Plugins und Plugin-Marketplaces verwalten |

## Slash-Befehle (in der interaktiven Oberflaeche)

### Wichtigste Befehle

| Befehl | Beschreibung |
|--------|-------------|
| `/help` | Hilfe zu allen interaktiven Befehlen anzeigen |
| `/model [MODEL]` | KI-Modell auswaehlen (z.B. GPT-4o, Claude) |
| `/plan [PROMPT]` | Implementierungsplan erstellen, bevor Code geschrieben wird |
| `/clear` | Konversationsverlauf loeschen und neu starten |
| `/compact` | Kontext-Fenster durch Zusammenfassung der History minimieren |
| `/context` | Token-Verbrauch und Kontext-Auslastung anzeigen |
| `/diff` | Aenderungen im aktuellen Verzeichnis reviewen |
| `/review [PROMPT]` | Code-Review Agent starten, der Aenderungen analysiert |
| `/exit` | CLI beenden |

### Kontext & Arbeitsverzeichnis

| Befehl | Beschreibung |
|--------|-------------|
| `/cwd [PATH]` | Arbeitsverzeichnis anzeigen oder wechseln |
| `/add-dir PATH` | Verzeichnis zur erlaubten Liste fuer Dateizugriff hinzufuegen |
| `/list-dirs` | Erlaubte Verzeichnisse anzeigen |
| `/init` | Custom Instructions und agentische Features initialisieren |

### Agentische Features

| Befehl | Beschreibung |
|--------|-------------|
| `/agent` | Verfuegbare Agenten durchsuchen und auswaehlen |
| `/fleet [PROMPT]` | Parallele Subagent-Ausfuehrung fuer groessere Aufgaben |
| `/delegate [PROMPT]` | KI-gesteuerten Pull Request fuer Remote-Aenderungen erstellen |
| `/allow-all` | Alle Berechtigungen aktivieren (Tools, Pfade, URLs) |
| `/reset-allowed-tools` | Tool-Berechtigungen zuruecksetzen |

### MCP & Plugins

| Befehl | Beschreibung |
|--------|-------------|
| `/mcp show` | Konfigurierte MCP-Server anzeigen |
| `/mcp add` | Neuen MCP-Server hinzufuegen |
| `/mcp edit` | MCP-Server-Konfiguration bearbeiten |
| `/mcp enable/disable` | MCP-Server aktivieren/deaktivieren |
| `/plugin list` | Installierte Plugins auflisten |
| `/plugin install` | Plugin installieren |

### Session-Verwaltung

| Befehl | Beschreibung |
|--------|-------------|
| `/session` | Session-Informationen anzeigen |
| `/resume [SESSION-ID]` | Zu einer anderen Session wechseln |
| `/rename NAME` | Aktuelle Session umbenennen |
| `/share file [PATH]` | Session als Markdown exportieren |
| `/share gist` | Session als GitHub Gist teilen |
| `/usage` | Session-Nutzungsstatistiken anzeigen |

### Sonstige

| Befehl | Beschreibung |
|--------|-------------|
| `/experimental [on/off]` | Experimentelle Features ein-/ausschalten |
| `/lsp show` | Language Server Protocol Konfiguration anzeigen |
| `/skills list` | Verfuegbare Skills auflisten |
| `/theme set` | Terminal-Theme aendern |
| `/terminal-setup` | Multiline-Input konfigurieren |
| `/feedback` | Feedback an GitHub senden |
