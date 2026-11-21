# RuoYi Java 后端脚手架模板（若依）

开箱即用的 Java 后端管理系统起步模板（若依 RuoYi 最新版），基于 Spring Boot + MyBatis + Spring Security，内置用户 / 权限 / 菜单 / 字典 / 定时任务 / 代码生成等全套后台能力，可直接作为新管理后台的起步工程。

> 官方原文已归档至 [doc/README-官方原文.md](./doc/README-官方原文.md)；在线体验：<http://www.ruoyi.vip>（admin / admin123）。

## 核心版本

| 依赖 | 版本 | 说明 |
| --- | --- | --- |
| JDK | 1.8 | Java 运行环境 |
| Spring Boot | 2.0.5.RELEASE | 核心框架 |
| MyBatis | 3.x | ORM 框架（tk / 通用 Mapper 风格封装） |
| Spring Security | 5.x | 认证与权限（RBAC） |
| Druid | 1.x | 数据库连接池（含监控页面） |
| MySQL | 5.7+ / 8.x | 主数据库（库名默认 `ry`） |
| Redis | 5+ | 缓存 / 会话 / 字典 |
| Maven | 3.6+ | 构建工具（多模块工程） |

## 环境要求

- JDK 1.8 + Maven 3.6+
- MySQL 5.7+（或 8.x，8.x 需将驱动换为 `com.mysql.cj.jdbc.Driver`）
- Redis 5+（默认 localhost:6379，无密码）
- 后端默认端口 **80**（无 root 权限建议改为 8080，见「常用配置」）

## 快速开始

```bash
# 1. 初始化数据库（执行目录：项目根目录）
#    新建数据库 ry（utf8mb4），依次导入 sql/ry_20181008.sql、sql/quartz.sql

# 2. 修改数据库连接（执行目录：项目根目录）
#    文件：ruoyi-admin/src/main/resources/application-druid.yml
#    修改 master 数据源 url / username / password

# 3. 启动 Redis（本机已有则跳过）
redis-server

# 4. 编译并启动（执行目录：项目根目录）
mvn clean install -DskipTests
mvn spring-boot:run -pl ruoyi-admin

# 5. 浏览器访问
# 管理后台:   http://localhost:80（端口未改时）/ http://localhost:8080
# 默认账号:   admin / admin123
# Druid 监控: /monitor/druid/
```

## 常用命令

| 命令 | 说明 |
| --- | --- |
| `mvn clean install -DskipTests` | 全模块编译安装（跳过测试） |
| `mvn spring-boot:run -pl ruoyi-admin` | 启动主模块（ruoyi-admin） |
| `mvn package -DskipTests -pl ruoyi-admin -am` | 打包可执行 jar |
| `java -jar ruoyi-admin/target/ruoyi-admin.jar` | 运行打包产物 |

## 目录结构

```
├── pom.xml                  # Maven 父工程（统一依赖与版本）
├── sql/                     # 数据库脚本（ry_20181008.sql + quartz.sql）
├── doc/                     # 文档（含官方 README 原文）
├── ruoyi-admin/             # 启动模块（后台管理主工程，含配置文件）
├── ruoyi-framework/         # 框架核心（安全 / 拦截器 / 数据源 / 切面）
├── ruoyi-system/            # 系统模块（用户 / 角色 / 菜单 / 字典等业务）
├── ruoyi-common/            # 通用工具（工具类 / 常量 / 注解 / 异常）
├── ruoyi-quartz/            # 定时任务模块（调度 + 执行日志）
└── ruoyi-generator/         # 代码生成模块（CRUD 代码一键生成）
```

## 基本引入的东西

- **RBAC 权限体系**：用户 / 部门 / 岗位 / 角色 / 菜单，数据权限按机构划分
- **安全框架**：Spring Security + Token 认证（前后端分离友好）
- **数据层**：MyBatis + Druid 连接池（含 SQL 监控页面 `/monitor/druid/`）
- **定时任务**：Quartz 集群调度，在线管理任务与执行日志
- **代码生成**：表结构一键生成 controller / service / mapper / vue 代码
- **系统接口**：自动生成 API 文档（系统管理 → 系统接口）
- **日志与审计**：操作日志、登录日志、在线用户监控

## 演示功能说明

- **内置功能**：用户 / 部门 / 岗位 / 菜单 / 角色 / 字典 / 参数 / 通知公告 / 操作日志 / 登录日志 / 在线用户 / 定时任务 / 代码生成 / 系统接口 / 在线构建器 / 连接池监视（详见 [doc/README-官方原文.md](./doc/README-官方原文.md)）
- **默认账号**：admin / admin123
- **配套前端**：与本目录同级的 `ruoyi-vue3-scaffolding-template/`（Vue3 版管理端）

## 作为起步模板使用

1. 复制本目录到新项目，按「快速开始」初始化数据库并启动验证
2. 修改 `ruoyi-admin/src/main/resources/application*.yml`（数据源 / Redis / 端口 / 文件上传路径）
3. 用「代码生成」导入自己的业务表，一键生成 CRUD 代码后按需调整
4. 在菜单管理中配置业务菜单与权限标识，角色管理中授权
5. 建议将 `server.port` 改为 8080 等高位端口，避免 root 权限依赖

## 常用配置

| 配置项 | 文件 | 说明 |
| --- | --- | --- |
| `server.port` | `ruoyi-admin/src/main/resources/application.yml` | 服务端口，默认 80 |
| 数据源 | `ruoyi-admin/src/main/resources/application-druid.yml` | MySQL 连接（库默认 `ry`） |
| Redis | `ruoyi-admin/src/main/resources/application.yml` | 地址 / 密码 / 库号 |
| 文件上传路径 | `ruoyi-admin/src/main/resources/application.yml` | 上传 / 生成路径 |
| Druid 监控账号 | `ruoyi-admin/src/main/resources/application-druid.yml` | `/monitor/druid/` 登录配置 |
