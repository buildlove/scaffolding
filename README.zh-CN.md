# 脚手架模板集合

[English](./README.md) | **简体中文**

> 开箱即用的全栈起步模板集合：**Web 前端 ×3 · 多端应用 · 移动应用 · 桌面客户端 · 后端服务 ×4**，共 10 套脚手架。
> 各模板 clone 后安装依赖即可运行，可直接作为新项目的起步工程；首页风格与 README 结构保持统一。

- 📄 可视化导航页：双击本目录下的 [index.html](./index.html) 即可在浏览器中查看同款介绍页（含一键复制命令）
- 📦 包管理器推荐 **pnpm**，Node.js ≥ 16（推荐 18 / 20 / 22；RN 模板需 ≥ 22.11）
- 🌐 国内网络已内置 npmmirror 镜像（electron 二进制 / 打包工具）

## 一、脚手架一览

| 图标 | 脚手架 | 类别 | 技术栈 | 开发端口 | 目录 |
| --- | --- | --- | --- | --- | --- |
| 💚 | [Vue3 脚手架模板](#-vue3-脚手架模板web-前端) | Web 前端 | Vue 3.5 / Vite 4 / Pinia / Element Plus | 5005 | [vue3-scaffolding-template/](./vue3-scaffolding-template/) |
| ⚛️ | [React19 脚手架模板](#️-react19-脚手架模板web-前端) | Web 前端 | React 19 / React Router 6 / Zustand / Ant Design | 3000 | [react19-scaffolding-template/](./react19-scaffolding-template/) |
| 📱 | [uni-app Vue3 脚手架模板](#-uni-app-vue3-脚手架模板多端应用) | 多端应用 | uni-app 3 / Vue 3.5 / Vite 5 / Pinia | 5005 | [uniapp-vue3-scaffolding-template/](./uniapp-vue3-scaffolding-template/) |
| 📲 | [React Native 脚手架模板](#-react-native-脚手架模板移动应用) | 移动应用 | React Native 0.88 / React 19 / TypeScript / RN Navigation 7 | Metro 8081 | [react-native-scaffolding-template/](./react-native-scaffolding-template/) |
| 🖥️ | [Electron 脚手架模板](#️-electron-脚手架模板桌面客户端) | 桌面客户端 | Electron 43 / electron-vite 6 / Vue 3.5 | — | [electron-scaffolding-template/](./electron-scaffolding-template/) |
| 🥚 | [Egg Swagger 后端模板](#-egg-swagger-后端模板后端服务) | 后端服务 | Egg.js 2 / egg-swagger2 / egg-mysql | 7001 | [egg_swagger/](./egg_swagger/) |
| 🟩 | [Express 后端脚手架模板](#-express-后端脚手架模板后端服务) | 后端服务 | Node.js / Express 5 / Swagger / ESM | 7002 | [express-scaffolding-template/](./express-scaffolding-template/) |
| 🐢 | [Koa 后端脚手架模板](#-koa-后端脚手架模板后端服务) | 后端服务 | Node.js / Koa 3 / Swagger / ESM | 7003 | [koa-scaffolding-template/](./koa-scaffolding-template/) |
| 🏯 | [RuoYi Java 后端脚手架模板](#-ruoyi-java-后端脚手架模板后端服务) | 后端服务 | Java 8 / Spring Boot 2 / MyBatis / Spring Security | 80（建议 8080） | [ruoyi-scaffolding-template/](./ruoyi-scaffolding-template/) |
| 🎏 | [RuoYi Vue3 前端脚手架模板](#-ruoyi-vue3-前端脚手架模板web-前端) | Web 前端 | Vue 3 / Vite / Element Plus / Pinia | 80 | [ruoyi-vue3-scaffolding-template/](./ruoyi-vue3-scaffolding-template/) |

---

## 二、脚手架详情

### 💚 Vue3 脚手架模板（Web 前端）

Vue3.5 + Vite 起步工程，覆盖嵌套路由、状态管理、请求封装等常用能力，可直接作为新 Web 项目的起步模板。

**技术栈：** `Vue 3.5` `Vite 4` `Pinia` `Vue Router 4` `Element Plus` `Axios`

**核心能力：**

- 首页演示：Pinia 计数器 / 路由节点分页联动
- 嵌套路由 + 二级侧边菜单 + 动态浏览器标题
- Axios 统一封装（拦截器 + 统一错误提示）
- Element Plus 按需自动引入（中文语言包）

**快速开始：**

```bash
pnpm install
pnpm run dev   # 端口 5005
```

📄 详细文档：[vue3-scaffolding-template/README.md](./vue3-scaffolding-template/README.md)

---

### ⚛️ React19 脚手架模板（Web 前端）

与 Vue3 版同款演示的 React 起步工程（已升级 React 19），风格功能与 vue3-scaffolding-template 保持一致。

**技术栈：** `React 19` `React Router 6` `Zustand` `Ant Design` `CRA + rewired`

**核心能力：**

- 首页演示与 Vue3 版逐项对齐（横幅 / 技术栈 / 功能演示）
- Hash 嵌套路由 + 菜单精确联动高亮
- Zustand 状态管理（state / 派生值 / actions 范例）
- Axios 统一封装 + Ant Design 中文语言包

**快速开始：**

```bash
pnpm install
pnpm start   # 端口 3000
```

📄 详细文档：[react19-scaffolding-template/README.md](./react19-scaffolding-template/README.md)

---

### 📱 uni-app Vue3 脚手架模板（多端应用）

uni-app 3 + Vue3.5 多端起步工程，一套代码可发布到 H5、微信小程序与 App，首页视觉与 Vue3 版保持一致。

**技术栈：** `uni-app 3` `Vue 3.5` `Vite 5` `Pinia` `Less`

**核心能力：**

- 一套代码多端发布（H5 / 微信小程序 / App）
- 页面路由（pages.json）+ Pinia + 请求封装同款能力
- 公共组件 easycom 自动引入（顶部导航 / 卡片容器）
- 扁平化 node_modules 配置，兼容 pinia 4 依赖解析

**快速开始：**

```bash
pnpm install
pnpm run dev:h5          # H5，端口 5005
pnpm run dev:mp-weixin   # 微信小程序
```

📄 详细文档：[uniapp-vue3-scaffolding-template/README.md](./uniapp-vue3-scaffolding-template/README.md)

---

### 📲 React Native 脚手架模板（移动应用）

React Native 0.88 + React 19 + TypeScript 起步工程，基于官方最新脚手架生成，内置原生导航栈与 Zustand 状态管理，一套代码发布 iOS / Android。

**技术栈：** `React Native 0.88` `React 19` `TypeScript` `React Navigation 7` `Zustand 5`

**核心能力：**

- 原生导航栈（native-stack）+ 安全区适配（刘海屏 / 底部指示条）
- Zustand 状态管理（对齐 Pinia 计数器演示：state / 派生值 / actions）
- 家族同款首页演示（欢迎横幅 / 技术栈 / 功能演示）
- Jest 测试 + Metro 打包验证通过，pnpm 扁平化 node_modules 兼容配置

**快速开始：**

```bash
pnpm install
pnpm ios       # iOS（需 macOS + Xcode，首次先 cd ios && bundle exec pod install）
pnpm android   # Android（需 Android Studio）
pnpm start     # Metro 开发服务，端口 8081
```

📄 详细文档：[react-native-scaffolding-template/README.md](./react-native-scaffolding-template/README.md)

---

### 🖥️ Electron 脚手架模板（桌面客户端）

Electron + electron-vite 三进程起步工程，渲染层与 Vue3 版完全同款，一键打包 macOS / Windows / Linux 安装包。

**技术栈：** `Electron 43` `electron-vite 6` `Vue 3.5` `Pinia` `Element Plus`

**核心能力：**

- 主进程 / 预加载 / 渲染进程三段式结构（安全桥接示例）
- 渲染层复用 Vue3 模板全部页面（首页完全同款）
- electron-builder 打包（dmg / nsis / AppImage）
- 内置 npmmirror 镜像，国内网络开箱即用

**快速开始：**

```bash
pnpm install
pnpm run dev         # 启动桌面窗口
pnpm run build:mac   # 打包 dmg
```

📄 详细文档：[electron-scaffolding-template/README.md](./electron-scaffolding-template/README.md)

---

### 🥚 Egg Swagger 后端模板（后端服务）

Egg.js 2 + Swagger 起步工程，内置 MySQL、跨域与定时任务能力，自动生成在线 API 文档。

**技术栈：** `Egg.js 2` `egg-swagger2` `egg-mysql` `egg-cors` `mysql_help`

**核心能力：**

- Swagger 在线 API 文档自动生成（`/api/swagger/index.html`）
- controller / service / router 分层 + definitions 模型定义
- egg-mysql + mysql_help 数据库访问封装
- 定时任务（schedule）与中间件（middleware）示例

**快速开始：**

```bash
npm i
npm run dev   # 端口 7001
# Swagger 文档: http://127.0.0.1:7001/api/swagger/index.html
```

📄 详细文档：[egg_swagger/README.md](./egg_swagger/README.md)

---

### 🟩 Express 后端脚手架模板（后端服务）

Node.js + Express 5 起步工程（ESM），分层架构 + Swagger 文档 + 统一响应 / 错误处理，内置内存 CRUD 演示与接口测试。

**技术栈：** `Node.js 18+` `Express 5` `swagger-jsdoc` `swagger-ui-express` `node:test`

**核心能力：**

- router / controller / service 三层架构（职责清晰）
- Swagger 在线文档（`/api/docs`）+ OpenAPI JSON 导出
- 统一响应 `{ code, message, data }` + 全局错误处理（Express 5 自动捕获 async 异常）
- 请求日志 / 跨域 / 静态资源 / 优雅关闭 / 零依赖接口测试

**快速开始：**

```bash
pnpm install
pnpm run dev   # 端口 7002
pnpm test      # 接口测试（node:test）
```

📄 详细文档：[express-scaffolding-template/README.md](./express-scaffolding-template/README.md)

---

### 🐢 Koa 后端脚手架模板（后端服务）

Node.js + Koa 3 起步工程（ESM），洋葱模型中间件 + 分层架构 + Swagger 文档，与 Express 版同款能力与演示。

**技术栈：** `Node.js 18+` `Koa 3` `@koa/router` `koa2-swagger-ui` `node:test`

**核心能力：**

- 洋葱模型：errorHandler 最外层统一捕获下游异常
- router / controller / service 三层架构（职责清晰）
- Swagger 在线文档（`/api/docs`）+ OpenAPI JSON 导出
- 请求日志 / 跨域 / 静态资源 / 优雅关闭 / 零依赖接口测试

**快速开始：**

```bash
pnpm install
pnpm run dev   # 端口 7003
pnpm test      # 接口测试（node:test）
```

📄 详细文档：[koa-scaffolding-template/README.md](./koa-scaffolding-template/README.md)

---

### 🏯 RuoYi Java 后端脚手架模板（后端服务）

若依 RuoYi 最新版 Java 后端（Spring Boot 2 + MyBatis + Spring Security），内置用户 / 权限 / 菜单 / 字典 / 定时任务 / 代码生成等全套后台能力。

**技术栈：** `Java 8` `Spring Boot 2` `MyBatis` `Spring Security` `Quartz` `Druid` `MySQL` `Redis`

**核心能力：**

- RBAC 权限体系（用户 / 部门 / 岗位 / 角色 / 菜单 / 数据权限）
- 代码生成：业务表一键生成 controller / service / mapper / vue 代码
- Quartz 定时任务 + Druid 连接池监控
- sql 脚本开箱即用（MySQL 库 `ry` + Redis，默认账号 admin / admin123）

**快速开始：**

```bash
# 1. 新建 MySQL 库 ry（utf8mb4），导入 sql/ry_20181008.sql、sql/quartz.sql
# 2. 修改 ruoyi-admin/src/main/resources/application-druid.yml 数据源配置
mvn clean install -DskipTests
mvn spring-boot:run -pl ruoyi-admin   # 默认端口 80（建议改 8080，与前端代理一致）
```

📄 详细文档：[ruoyi-scaffolding-template/README.md](./ruoyi-scaffolding-template/README.md)

---

### 🎏 RuoYi Vue3 前端脚手架模板（Web 前端）

若依 RuoYi-Vue3 最新版管理端前端（Vue3 + Element Plus + Vite），配套 RuoYi 后端的完整后台管理界面。

**技术栈：** `Vue 3` `Vite` `Element Plus` `Pinia` `Axios`

**核心能力：**

- 登录 / 权限 / 菜单 / 字典 / 定时任务等完整管理端页面
- 动态路由 + 按钮级权限指令（v-hasPermi）
- 与 RuoYi 后端开箱联调（代理直连后端）
- 代码生成器生成的 vue 页面直接落位

**快速开始：**

```bash
pnpm install
pnpm dev   # 端口 80，/dev-api 代理 → http://localhost:8080（需先启动 RuoYi 后端）
```

📄 详细文档：[ruoyi-vue3-scaffolding-template/README.md](./ruoyi-vue3-scaffolding-template/README.md)

---

## 三、模板家族约定

- 前端模板首页风格统一（渐变横幅 + 技术栈卡片 + 功能演示）
- README 结构统一：核心版本 → 快速开始 → 目录结构 → 基本引入 → 常用配置
- 包管理器推荐 pnpm，Node.js ≥ 16（推荐 18 / 20 / 22，RN 模板需 ≥ 22.11）
- 开发端口约定：Vue3 / uni-app 5005、React 3000、Egg.js 7001、Express 7002、Koa 7003、RN Metro 8081、RuoYi 前端 80 / 后端 8080（后端默认 80，建议改 8080）
- 前端请求域名统一由 `.env` 的 `VITE_TINTMCE_BASE_URL` 注入；后端统一响应 `{ code, message, data }`
- 国内网络已内置 npmmirror 镜像（electron 二进制 / 打包工具）

## 四、新增脚手架指引

1. 以 [vue3-scaffolding-template](./vue3-scaffolding-template/) 为基准复制改造（后端可参照 express / koa 模板），首页与 README 结构保持家族一致，文案按技术栈适配
2. 在 [index.html](./index.html) 的 `SCAFFOLDS` 数组中追加一项（卡片自动渲染）
3. 在本文件「脚手架一览」表格与「脚手架详情」中追加对应小节
