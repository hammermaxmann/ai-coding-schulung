# MCP Server Konfiguration für Context7

## 1. Copilot CLI

Pfad: `~/.copilot/mcp-config.json`

```json
{
  "mcpServers": {
    "context7": {
      "type": "http",
      "url": "https://mcp.context7.com/mcp"
    }
  }
}
```

## 2. Copilot Plugin fuer IntelliJ

Pfad: `~/.config/github-copilot/intellij/mcp.json`

```json
{
  "servers": {
    "context7": {
      "type": "http",
      "url": "https://mcp.context7.com/mcp"
    }
  }
}
```

## Context7

Context7 liefert aktuelle, versionsspezifische Dokumentation von Libraries direkt als Kontext an den AI-Assistenten und sollte als `"type": "http"` konfiguriert werden, wenn kein npx vorhanden ist.
