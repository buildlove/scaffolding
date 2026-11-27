# Li Yang's Scaffolding Templates

**English** | [简体中文](./README.zh-CN.md)

> A ready-to-use full-stack starter collection: **Web Frontend ×3 · Cross-platform · Mobile App · Desktop Client · Backend Service ×4** — 10 scaffolds in total.
> Each template runs after cloning and installing dependencies, and can serve directly as the foundation of a new project. Home-page style and README structure stay consistent across the family.

- 📄 Visual navigation page: double-click [index.html](./index.html) to view the same introduction in your browser (Chinese / English toggle, English by default; one-click command copy)
- 📦 Recommended package manager: **pnpm**; Node.js ≥ 16 (18 / 20 / 22 recommended; the RN template needs ≥ 22.11)
- 🌐 npmmirror mirrors are preconfigured for networks in China (electron binaries / packaging tools)

## 1. Scaffold Collection

| Icon | Scaffold | Category | Tech Stack | Dev Port | Directory |
| --- | --- | --- | --- | --- | --- |
| 💚 | [Vue3 Scaffolding Template](./vue3-scaffolding-template/README.md) | Web Frontend | Vue 3.5 / Vite 4 / Pinia / Element Plus | 5005 | [vue3-scaffolding-template/](./vue3-scaffolding-template/) |
| ⚛️ | [React19 Scaffolding Template](./react19-scaffolding-template/README.md) | Web Frontend | React 19 / React Router 6 / Zustand / Ant Design | 3000 | [react19-scaffolding-template/](./react19-scaffolding-template/) |
| 📱 | [uni-app Vue3 Scaffolding Template](./uniapp-vue3-scaffolding-template/README.md) | Cross-platform | uni-app 3 / Vue 3.5 / Vite 5 / Pinia | 5005 | [uniapp-vue3-scaffolding-template/](./uniapp-vue3-scaffolding-template/) |
| 📲 | [React Native Scaffolding Template](./react-native-scaffolding-template/README.md) | Mobile App | React Native 0.88 / React 19 / TypeScript / RN Navigation 7 | Metro 8081 | [react-native-scaffolding-template/](./react-native-scaffolding-template/) |
| 🖥️ | [Electron Scaffolding Template](./electron-scaffolding-template/README.md) | Desktop Client | Electron 43 / electron-vite 6 / Vue 3.5 | — | [electron-scaffolding-template/](./electron-scaffolding-template/) |
| 🥚 | [Egg Swagger Backend Template](./egg_swagger/README.md) | Backend Service | Egg.js 2 / egg-swagger2 / egg-mysql | 7001 | [egg_swagger/](./egg_swagger/) |
| 🟩 | [Express Backend Scaffolding Template](./express-scaffolding-template/README.md) | Backend Service | Node.js / Express 5 / Swagger / ESM | 7002 | [express-scaffolding-template/](./express-scaffolding-template/) |
| 🐢 | [Koa Backend Scaffolding Template](./koa-scaffolding-template/README.md) | Backend Service | Node.js / Koa 3 / Swagger / ESM | 7003 | [koa-scaffolding-template/](./koa-scaffolding-template/) |
| 🏯 | [RuoYi Java Backend Scaffolding Template](./ruoyi-scaffolding-template/README.md) | Backend Service | Java 8 / Spring Boot 2 / MyBatis / Spring Security | 80 (8080 recommended) | [ruoyi-scaffolding-template/](./ruoyi-scaffolding-template/) |
| 🎏 | [RuoYi Vue3 Frontend Scaffolding Template](./ruoyi-vue3-scaffolding-template/README.md) | Web Frontend | Vue 3 / Vite / Element Plus / Pinia | 80 | [ruoyi-vue3-scaffolding-template/](./ruoyi-vue3-scaffolding-template/) |

---

## 2. Scaffold Details

### 💚 Vue3 Scaffolding Template (Web Frontend)

A Vue 3.5 + Vite starter covering nested routing, state management and request encapsulation — ready to use as the foundation of a new web project.

**Tech stack:** `Vue 3.5` `Vite 4` `Pinia` `Vue Router 4` `Element Plus` `Axios`

**Highlights:**

- Home demo: Pinia counter / route-node pagination in sync
- Nested routes + secondary side menu + dynamic browser title
- Unified Axios wrapper (interceptors + unified error prompts)
- Element Plus on-demand auto import (Chinese locale)

**Quick start:**

```bash
pnpm install
pnpm run dev   # port 5005
```

📄 Detailed docs: [vue3-scaffolding-template/README.md](./vue3-scaffolding-template/README.md)

---

### ⚛️ React19 Scaffolding Template (Web Frontend)

A React starter with the same demos as the Vue3 version (upgraded to React 19), consistent in style and features with vue3-scaffolding-template.

**Tech stack:** `React 19` `React Router 6` `Zustand` `Ant Design` `CRA + rewired`

**Highlights:**

- Home demo aligned item by item with the Vue3 version (banner / tech stack / demos)
- Hash nested routing + precise menu highlight sync
- Zustand state management (state / derived values / actions examples)
- Unified Axios wrapper + Ant Design Chinese locale

**Quick start:**

```bash
pnpm install
pnpm start   # port 3000
```

📄 Detailed docs: [react19-scaffolding-template/README.md](./react19-scaffolding-template/README.md)

---

### 📱 uni-app Vue3 Scaffolding Template (Cross-platform)

uni-app 3 + Vue 3.5 multi-platform starter: one codebase publishes to H5, WeChat Mini Program and App, with the same home-page look as the Vue3 version.

**Tech stack:** `uni-app 3` `Vue 3.5` `Vite 5` `Pinia` `Less`

**Highlights:**

- One codebase, multiple platforms (H5 / WeChat Mini Program / App)
- Page routing (pages.json) + Pinia + request wrapper, same capabilities
- easycom auto-import for shared components (top nav / card container)
- Flattened node_modules config compatible with pinia 4 dependency resolution

**Quick start:**

```bash
pnpm install
pnpm run dev:h5          # H5, port 5005
pnpm run dev:mp-weixin   # WeChat Mini Program
```

📄 Detailed docs: [uniapp-vue3-scaffolding-template/README.md](./uniapp-vue3-scaffolding-template/README.md)

---

### 📲 React Native Scaffolding Template (Mobile App)

React Native 0.88 + React 19 + TypeScript starter generated from the official latest template, with a native navigation stack and Zustand state management; one codebase for iOS / Android.

**Tech stack:** `React Native 0.88` `React 19` `TypeScript` `React Navigation 7` `Zustand 5`

**Highlights:**

- Native stack navigation + safe-area handling (notch / home indicator)
- Zustand store (matching the Pinia counter demo: state / derived values / actions)
- Family-style home demo (welcome banner / tech stack / feature demos)
- Jest tests + verified Metro bundling, with pnpm flattened node_modules compatibility

**Quick start:**

```bash
pnpm install
pnpm ios       # iOS (requires macOS + Xcode; run cd ios && bundle exec pod install first)
pnpm android   # Android (requires Android Studio)
pnpm start     # Metro dev server on port 8081
```

📄 Detailed docs: [react-native-scaffolding-template/README.md](./react-native-scaffolding-template/README.md)

---

### 🖥️ Electron Scaffolding Template (Desktop Client)

Electron + electron-vite three-process starter whose renderer matches the Vue3 version exactly; package macOS / Windows / Linux installers with one command.

**Tech stack:** `Electron 43` `electron-vite 6` `Vue 3.5` `Pinia` `Element Plus`

**Highlights:**

- Main / preload / renderer three-part structure (secure bridge example)
- Renderer reuses every page from the Vue3 template (identical home page)
- electron-builder packaging (dmg / nsis / AppImage)
- Built-in npmmirror mirrors for networks in China

**Quick start:**

```bash
pnpm install
pnpm run dev         # launch the desktop window
pnpm run build:mac   # build the dmg
```

📄 Detailed docs: [electron-scaffolding-template/README.md](./electron-scaffolding-template/README.md)

---

### 🥚 Egg Swagger Backend Template (Backend Service)

Egg.js 2 + Swagger starter with MySQL, CORS and scheduled tasks built in; generates online API documentation automatically.

**Tech stack:** `Egg.js 2` `egg-swagger2` `egg-mysql` `egg-cors` `mysql_help`

**Highlights:**

- Auto-generated Swagger API docs (`/api/swagger/index.html`)
- controller / service / router layers + definitions models
- egg-mysql + mysql_help database access wrapper
- Scheduled tasks (schedule) and middleware examples

**Quick start:**

```bash
npm i
npm run dev   # port 7001
# Swagger docs: http://127.0.0.1:7001/api/swagger/index.html
```

📄 Detailed docs: [egg_swagger/README.md](./egg_swagger/README.md)

---

### 🟩 Express Backend Scaffolding Template (Backend Service)

Node.js + Express 5 starter (ESM) with layered architecture, Swagger docs and unified responses / error handling, plus an in-memory CRUD demo and API tests.

**Tech stack:** `Node.js 18+` `Express 5` `swagger-jsdoc` `swagger-ui-express` `node:test`

**Highlights:**

- router / controller / service three-layer architecture (clear responsibilities)
- Swagger docs (`/api/docs`) + OpenAPI JSON export
- Unified response `{ code, message, data }` + global error handling (Express 5 catches async errors automatically)
- Request logging / CORS / static files / graceful shutdown / zero-dependency API tests

**Quick start:**

```bash
pnpm install
pnpm run dev   # port 7002
pnpm test      # API tests (node:test)
```

📄 Detailed docs: [express-scaffolding-template/README.md](./express-scaffolding-template/README.md)

---

### 🐢 Koa Backend Scaffolding Template (Backend Service)

Node.js + Koa 3 starter (ESM) with onion-model middleware, layered architecture and Swagger docs — same capabilities and demos as the Express version.

**Tech stack:** `Node.js 18+` `Koa 3` `@koa/router` `koa2-swagger-ui` `node:test`

**Highlights:**

- Onion model: the outermost errorHandler catches downstream errors
- router / controller / service three-layer architecture (clear responsibilities)
- Swagger docs (`/api/docs`) + OpenAPI JSON export
- Request logging / CORS / static files / graceful shutdown / zero-dependency API tests

**Quick start:**

```bash
pnpm install
pnpm run dev   # port 7003
pnpm test      # API tests (node:test)
```

📄 Detailed docs: [koa-scaffolding-template/README.md](./koa-scaffolding-template/README.md)

---

### 🏯 RuoYi Java Backend Scaffolding Template (Backend Service)

RuoYi latest Java backend (Spring Boot 2 + MyBatis + Spring Security) with the full admin feature set: users / permissions / menus / dictionaries / scheduled tasks / code generation.

**Tech stack:** `Java 8` `Spring Boot 2` `MyBatis` `Spring Security` `Quartz` `Druid` `MySQL` `Redis`

**Highlights:**

- RBAC permission system (users / departments / positions / roles / menus / data scope)
- Code generator: one-click controller / service / mapper / vue code for business tables
- Quartz scheduled jobs + Druid connection pool monitoring
- SQL scripts ready to run (MySQL database `ry` + Redis; default account admin / admin123)

**Quick start:**

```bash
# 1. Create the MySQL database ry (utf8mb4) and import sql/ry_20181008.sql and sql/quartz.sql
# 2. Edit the datasource in ruoyi-admin/src/main/resources/application-druid.yml
mvn clean install -DskipTests
mvn spring-boot:run -pl ruoyi-admin   # default port 80 (8080 recommended to match the frontend proxy)
```

📄 Detailed docs: [ruoyi-scaffolding-template/README.md](./ruoyi-scaffolding-template/README.md)

---

### 🎏 RuoYi Vue3 Frontend Scaffolding Template (Web Frontend)

RuoYi-Vue3 latest admin frontend (Vue3 + Element Plus + Vite), the complete admin UI for the RuoYi backend.

**Tech stack:** `Vue 3` `Vite` `Element Plus` `Pinia` `Axios`

**Highlights:**

- Complete login / permission / menu / dictionary / scheduled-job pages
- Dynamic routes + button-level permission directive (v-hasPermi)
- Works with the RuoYi backend out of the box (proxy to the backend)
- Pages generated by the code generator drop right in

**Quick start:**

```bash
pnpm install
pnpm dev   # port 80; /dev-api proxies to http://localhost:8080 (start the RuoYi backend first)
```

📄 Detailed docs: [ruoyi-vue3-scaffolding-template/README.md](./ruoyi-vue3-scaffolding-template/README.md)

---

## 3. Template Family Conventions

- Frontend templates share the same home-page style (gradient banner + tech-stack cards + feature demos)
- READMEs follow a unified structure: core versions → quick start → directory structure → key imports → common config
- Recommended package manager is pnpm, Node.js ≥ 16 (18 / 20 / 22 recommended; the RN template needs ≥ 22.11)
- Dev port convention: Vue3 / uni-app 5005, React 3000, Egg.js 7001, Express 7002, Koa 7003, RN Metro 8081, RuoYi frontend 80 / backend 8080 (backend default 80; 8080 recommended)
- Frontend request base URLs are injected via `VITE_TINTMCE_BASE_URL` in `.env`; backends share the `{ code, message, data }` response shape
- npmmirror mirrors are preconfigured for networks in China (electron binaries / packaging tools)

## 4. Adding a New Scaffold

1. Copy [vue3-scaffolding-template](./vue3-scaffolding-template/) as the base (use express / koa as references for backends); keep the home page and README structure consistent with the family and adapt the copy to the tech stack.
2. Add an entry to the `SCAFFOLDS` array in [index.html](./index.html) (cards render automatically); the array is bilingual (`en` / `zh`).
3. Add the corresponding row to the "Scaffold Collection" table and a section to "Scaffold Details" in this file.
4. Update the Chinese version in [README.zh-CN.md](./README.zh-CN.md) as well.
