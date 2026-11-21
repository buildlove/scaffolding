# RuoYi Vue3 前端脚手架模板（若依管理端）

开箱即用的后台管理前端起步模板（若依 RuoYi-Vue3 最新版），Vue 3 + Vite + Element Plus + Pinia，配套 RuoYi Java 后端的完整管理端界面，可直接作为新管理后台前端的起步工程。

> 官方原文已归档至 [doc/README-官方原文.md](./doc/README-官方原文.md)；需先启动 [RuoYi 后端](../ruoyi-scaffolding-template/README.md) 再启动本前端。

## 核心版本

| 依赖 | 版本 | 说明 |
| --- | --- | --- |
| vue | 3.5.26 | 核心框架（组合式 API） |
| vite | 6.4.1 | 构建工具 |
| element-plus | 2.13.1 | 组件库 |
| pinia | 3.0.4 | 状态管理 |
| vue-router | 4.6.4 | 路由（动态路由 + 权限指令） |
| axios | 1.13.2 | HTTP 请求（统一封装） |
| echarts | 5.6.0 | 图表（监控 / 统计页） |

## 环境要求

- Node.js >= 16（推荐 18 / 20 / 22）
- 包管理器推荐 [pnpm](https://pnpm.io/)（模板已配 `pnpm-workspace.yaml` 扁平化 node_modules）
- 需先启动 RuoYi Java 后端（默认代理目标 `http://localhost:8080`）

## 快速开始

```bash
# 1. 安装依赖（执行目录：项目根目录）
pnpm install

# 2. 启动开发服务（执行目录：项目根目录，默认端口 80）
pnpm dev

# 3. 浏览器访问
# 管理后台:  http://localhost:80（vite 配置 open: true 自动打开）
# 默认账号:  admin / admin123
# 接口代理:  /dev-api → http://localhost:8080（vite.config.js 中 baseUrl）
```

## 常用命令

| 命令 | 说明 |
| --- | --- |
| `pnpm dev` | 启动开发服务（端口 80，热更新） |
| `pnpm build:prod` | 生产构建（产物输出到 dist/） |
| `pnpm build:stage` | 测试环境构建（staging 模式） |
| `pnpm preview` | 本地预览构建产物 |

## 目录结构

```
├── vite.config.js           # Vite 配置（端口 80 + /dev-api 后端代理）
├── pnpm-workspace.yaml      # pnpm 12 扁平化 node_modules + 构建脚本放行
├── vite/                    # Vite 插件封装（自动导入 / SVG 图标 / 压缩）
├── public/                  # 静态资源
├── html/                    # 独立 HTML 页面
├── doc/                     # 文档（含官方 README 原文）
└── src/
    ├── api/                 # 接口定义（按业务模块拆分）
    ├── views/               # 管理端页面（系统管理 / 监控 / 工具等）
    ├── components/          # 通用组件（分页 / 表格 / 上传等）
    ├── router/              # 动态路由（后端菜单驱动）
    ├── store/               # Pinia 仓库（用户 / 权限 / 字典）
    ├── utils/               # 工具（请求封装 / 权限指令 / 校验）
    └── layout/              # 管理端整体布局（侧边栏 / 顶栏 / 标签页）
```

## 基本引入的东西

- **完整管理端**：登录 / 用户 / 部门 / 角色 / 菜单 / 字典 / 参数 / 日志 / 定时任务 / 代码生成页面
- **动态路由**：后端菜单驱动路由注册，按钮级权限指令 `v-hasPermi`
- **请求封装**：axios 统一拦截（Token 注入 / 错误提示 / 重复请求取消）
- **组件体系**：Element Plus 全量引入 + 常用业务组件（分页 / 表格 / 图表）
- **主题与布局**：侧边菜单 + 顶栏 + 多标签页，支持主题变量配置

## 演示功能说明

- **登录与鉴权**：验证码登录，Token 持久化，动态菜单按角色渲染
- **系统管理**：若依全套后台管理页面开箱即用
- **监控页面**：在线用户 / 定时任务 / 服务监控 / 缓存监控 / 连接池监视
- **代码生成**：导入表结构生成前后端代码，生成的 vue 页面直接落位到 src/views

## 作为起步模板使用

1. 复制本目录到新项目，`pnpm install` 后 `pnpm dev` 验证（需配套后端）
2. 在 `vite.config.js` 修改 `baseUrl` 为你的后端地址（默认 `http://localhost:8080`）
3. 按业务模块在 `src/views` / `src/api` 追加页面与接口，用「代码生成」加速 CRUD
4. 在后端菜单管理中配置菜单，前端动态路由自动生效
5. 构建发布：`pnpm build:prod`，将 `dist/` 部署到 Nginx 等静态服务器

## 常用配置

| 配置项 | 文件 | 说明 |
| --- | --- | --- |
| 开发端口 | `vite.config.js` | `server.port`，默认 80 |
| 后端代理目标 | `vite.config.js` | `baseUrl`，默认 `http://localhost:8080` |
| API 前缀 | `.env.development` | `VITE_APP_BASE_API`，默认 `/dev-api` |
| 构建输出 | `vite.config.js` | `build` 段（输出目录 / 压缩 / 分包） |
| pnpm 配置 | `pnpm-workspace.yaml` | 扁平化 node_modules + 构建脚本放行 |
