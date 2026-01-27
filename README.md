# RESTCraft

**RESTCraft** is a Minecraft Forge mod that sends player events to a RESTful backend.  
It tracks when players **join**, **leave**, or **die** and reports these events to a configurable HTTP endpoint.

---

## Features

- Sends player join, leave, and death events to a backend server.
- Supports a RESTful backend architecture with separate endpoints:
    - `/join`
    - `/leave`
    - `/death`
- Configurable backend URL via a Forge config file (`serverconfig/restcraft-server.toml`).

---

## Installation

1. **Download or build** the mod JAR file.
2. Place the JAR into your Minecraft `mods` folder.
3. Run a **Forge server** (version 1.20.1 recommended, Forge 47.2.0 tested).
4. Start the server. RESTCraft will automatically create its server config file on the first run.

---

## Configuration

Edit the server config to point RESTCraft to your backend API:

```toml
# serverconfig/restcraft-server.toml
backendUrl = "http://localhost:3000/api"