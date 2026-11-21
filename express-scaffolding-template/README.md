# Node + Express 脚手架模板

开箱即用的 Node.js 后端起步模板（Express 5 + ESM），覆盖分层架构、Swagger 文档、统一响应 / 错误处理等常用能力，可直接作为新后端项目的起步脚手架。

> 启动后访问 `/` 查看引导页，`/api/docs` 查看在线 Swagger 文档。

## 核心版本

| 依赖 | 版本 | 说明 |
| --- | --- | --- |
| express | 5.3.0 | Web 框架（原生支持 async 错误捕获） |
| cors | 2.8.6 | 跨域中间件 |
| dotenv | 18.0.10 | .env 环境变量加载 |
| swagger-jsdoc | 6.3.0 | 从 JSDoc 注释生成 OpenAPI 描述 |
| swagger-ui-express | 5.0.1 | Swagger 在线可视化文档 |
| node:test / node:assert | 内置 | 零依赖接口测试 |

## 环境要求

- Node.js >= 18（推荐 20 / 22，Express 5 与 `node --watch` 的最低要求）
- 包管理器推荐 [pnpm](https://pnpm.io/)（项目自带 pnpm-lock.yaml）

## 快速开始

```bash
# 1. 安装依赖（执行目录：项目根目录）
pnpm install

# 2. 启动开发服务（默认端口 7002，文件变更自动重启）
pnpm run dev

# 3. 浏览器访问
# 引导页:     http://localhost:7002/
# Swagger:   http://localhost:7002/api/docs
# 健康检查:   http://localhost:7002/api/health
```

## 常用命令

| 命令 | 说明 |
| --- | --- |
| `pnpm run dev` | 开发模式启动（node --watch 热重启，端口 7002） |
| `pnpm start` | 生产模式启动 |
| `pnpm test` | 运行接口测试（node:test） |

## 目录结构

```
├── package.json             # 依赖与脚本（ESM：type=module）
├── pnpm-workspace.yaml      # pnpm 12 构建脚本放行配置
├── .env.example             # 环境变量模板（复制为 .env 使用）
├── public/                  # 静态资源（根路径直接访问）
│   └── index.html           # 服务引导页（家族同款渐变风格）
├── test/
│   └── demo.test.js         # 接口测试（node:test + fetch）
└── src/
    ├── server.js            # 启动入口（监听端口 + 优雅关闭）
    ├── app.js               # 应用组装（中间件 / 路由 / 文档 / 错误处理）
    ├── swagger.js           # OpenAPI 文档生成配置
    ├── config/index.js      # 环境变量统一读取
    ├── middleware/
    │   ├── response.js      # 统一响应（res.ok / res.fail）
    │   ├── request-logger.js# 请求日志（方法 / 路径 / 状态码 / 耗时）
    │   └── error-handler.js # 404 兜底 + 全局异常处理
    ├── router/
    │   ├── index.js         # 路由汇总（/api 前缀 + 健康检查）
    │   └── demo.js          # 演示 CRUD 路由（含 Swagger 注释）
    ├── controller/demo.js   # 控制器（参数校验 + 调用 service）
    └── service/demo.js      # 服务层（内存数据 CRUD，可替换为数据库）
```

## 基本引入的东西

- **分层架构**：router（路由 + Swagger 注释）→ controller（参数校验）→ service（业务逻辑），职责清晰
- **统一响应**：`res.ok(data)` / `res.fail(message, code, statusCode)`，全接口返回 `{ code, message, data }`
- **全局错误处理**：404 兜底 + 异常统一捕获（Express 5 自动转发 async 异常，无需手动 try/catch）
- **Swagger 文档**：`/api/docs` 可视化调试，`/api/swagger.json` 可导入 Apifox / Postman
- **请求日志**：控制台输出方法、路径、状态码、耗时
- **跨域与 body 解析**：cors + express.json / urlencoded
- **静态资源**：`public/` 目录挂载根路径
- **优雅关闭**：SIGINT / SIGTERM 等待存量请求处理完再退出

## 演示功能说明

- **健康检查**：`GET /api/health` 服务探活
- **内存 CRUD**：`/api/demo` 完整演示列表（分页）/ 详情 / 新增 / 更新 / 删除
- **Swagger 注释**：`src/router/demo.js` 中的 `@openapi` 注释即文档，改代码即改文档
- **接口测试**：`pnpm test` 覆盖 CRUD 链路、参数校验与 404 结构

## 作为起步模板使用

1. 复制本目录到新项目，执行 `pnpm install` 后 `pnpm run dev` 验证
2. 复制 `.env.example` 为 `.env`，按需修改端口 / 跨域配置
3. 以 `demo` 模块为样板复制出新业务模块（router / controller / service 三件套），在 `src/router/index.js` 挂载
4. 需要数据库时，在 service 层替换为 mysql2 / prisma 等实现，控制器无需改动
5. 删除 `public/index.html`、演示路由与测试，换成自己的业务

## 常用配置

| 配置项 | 文件 | 说明 |
| --- | --- | --- |
| `PORT` | `.env` | 监听端口，默认 7002 |
| `HOST` | `.env` | 监听地址，默认 0.0.0.0（允许局域网访问） |
| `CORS_ORIGIN` | `.env` | 跨域来源，逗号分隔或 `*` |
| `NODE_ENV` | `.env` | 运行环境（development / production） |
| OpenAPI 信息 | `src/swagger.js` | 文档标题、版本、数据模型 |
