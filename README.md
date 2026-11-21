# 脚手架模板集合

> 开箱即用的全栈起步模板集合：**Web 前端 ×2 · 多端应用 · 桌面客户端 · 后端服务**，共 5 套脚手架。
> 各模板 clone 后安装依赖即可运行，可直接作为新项目的起步工程；首页风格与 README 结构保持统一。

- 📄 可视化导航页：双击本目录下的 [index.html](./index.html) 即可在浏览器中查看同款介绍页（含一键复制命令）
- 📦 包管理器推荐 **pnpm**，Node.js ≥ 16（推荐 18 / 20 / 22）
- 🌐 国内网络已内置 npmmirror 镜像（electron 二进制 / 打包工具）

## 一、脚手架一览

| 图标 | 脚手架 | 类别 | 技术栈 | 开发端口 | 目录 |
| --- | --- | --- | --- | --- | --- |
| 💚 | [Vue3 脚手架模板](#-vue3-脚手架模板web-前端) | Web 前端 | Vue 3.5 / Vite 4 / Pinia / Element Plus | 5005 | [vue3-scaffolding-template/](./vue3-scaffolding-template/) |
| ⚛️ | [React18 脚手架模板](#️-react18-脚手架模板web-前端) | Web 前端 | React 19 / React Router 6 / Zustand / Ant Design | 3000 | [react18-scaffolding-template/](./react18-scaffolding-template/) |
| 📱 | [uni-app Vue3 脚手架模板](#-uni-app-vue3-脚手架模板多端应用) | 多端应用 | uni-app 3 / Vue 3.5 / Vite 5 / Pinia | 5005 | [uniapp-vue3-scaffolding-template/](./uniapp-vue3-scaffolding-template/) |
| 🖥️ | [Electron 脚手架模板](#️-electron-脚手架模板桌面客户端) | 桌面客户端 | Electron 43 / electron-vite 6 / Vue 3.5 | — | [electron-scaffolding-template/](./electron-scaffolding-template/) |
| 🥚 | [Egg Swagger 后端模板](#-egg-swagger-后端模板后端服务) | 后端服务 | Egg.js 2 / egg-swagger2 / egg-mysql | 7001 | [egg_swagger/](./egg_swagger/) |

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

### ⚛️ React18 脚手架模板（Web 前端）

与 Vue3 版同款演示的 React 起步工程（实际已升级 React 19），风格功能与 vue3-scaffolding-template 保持一致。

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

📄 详细文档：[react18-scaffolding-template/README.md](./react18-scaffolding-template/README.md)

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

## 三、模板家族约定

- 前端三套模板首页风格统一（渐变横幅 + 技术栈卡片 + 功能演示）
- README 结构统一：核心版本 → 快速开始 → 目录结构 → 基本引入 → 常用配置
- 包管理器推荐 pnpm，Node.js ≥ 16（推荐 18 / 20 / 22）
- 开发端口约定：Vue3 / uni-app 5005、React 3000、Egg.js 7001
- 请求域名统一由 `.env` 的 `VITE_TINTMCE_BASE_URL` 注入
- 国内网络已内置 npmmirror 镜像（electron 二进制 / 打包工具）

## 四、新增脚手架指引

1. 以 [vue3-scaffolding-template](./vue3-scaffolding-template/) 为基准复制改造，首页与 README 结构保持家族一致，文案按技术栈适配
2. 在 [index.html](./index.html) 的 `SCAFFOLDS` 数组中追加一项（卡片自动渲染）
3. 在本文件「脚手架一览」表格与「脚手架详情」中追加对应小节
