# Veterinarios por Venezuela — Frontend

Angular 19 frontend for the emergency animal coordination platform.

---

## Requirements

### With Docker (recommended)

- **Docker Desktop** ≥ 20.10 — [https://www.docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop)
  - Windows / Mac: Docker Desktop includes both Docker Engine and Compose
  - Linux: install [Docker Engine](https://docs.docker.com/engine/install/) + the [Compose plugin](https://docs.docker.com/compose/install/) separately

Verify your installation:

```bash
docker --version          # Docker version 20.10+
docker compose version    # Docker Compose version v2+
```

### Without Docker

| Tool        | Version  | Download |
|-------------|----------|----------|
| Node.js     | 20 LTS   | https://nodejs.org |
| npm         | 9+       | bundled with Node |
| Angular CLI | 19       | `npm install -g @angular/cli` |

---

## Running the app

### With Docker

```bash
# From this directory (FE/vetsporvzla)
docker compose up
```

The app will be available at **http://localhost:4200**.

> The first run takes a few minutes — Docker pulls the `node:20` image and runs `npm install` inside the container. Subsequent starts are faster because `node_modules` persists via the volume mount.

Run in the background:

```bash
docker compose up -d

# Stop the container
docker compose down
```

### Without Docker

```bash
# Install dependencies
npm install

# Start the dev server
ng serve
```

The app will be available at **http://localhost:4200**.

---

## Environment

The app connects to the backend API at `http://localhost:8080` by default.
To change it, edit `src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080'
};
```
