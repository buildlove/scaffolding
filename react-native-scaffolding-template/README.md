# React Native 脚手架模板

开箱即用的 React Native 起步模板（RN 0.88 + React 19 + TypeScript），基于官方最新脚手架生成，补充导航、状态管理与家族同款首页演示，可直接作为新 App 项目的起步工程。

> iOS 需要 macOS + Xcode，Android 需要 Android Studio；首次运行 iOS 前需执行 `pod install`。

## 核心版本

| 依赖 | 版本 | 说明 |
| --- | --- | --- |
| react-native | 0.88.1 | 跨端框架（官方最新稳定版） |
| react | 19.3.0 | 核心库 |
| typescript | 6.x | 类型系统（官方 TS 模板） |
| @react-navigation/native | 7.x | 导航容器 |
| @react-navigation/native-stack | 7.x | 原生导航栈（系统级转场） |
| react-native-screens | 4.x | 原生屏容器（导航底层依赖） |
| react-native-safe-area-context | 5.x | 安全区适配（刘海屏 / 底部指示条） |
| zustand | 5.x | 轻量状态管理（对齐 Vue 版 Pinia 演示） |

## 环境要求

- Node.js >= 22.11（RN 0.88 官方要求）
- iOS：macOS + Xcode 15+ + CocoaPods（`gem install bundler` 后经 Gemfile 安装）
- Android：Android Studio + JDK 17 + Android SDK
- 包管理器推荐 [pnpm](https://pnpm.io/)（模板已配 `.npmrc` 扁平化 node_modules）

## 快速开始

```bash
# 1. 安装依赖（执行目录：项目根目录）
pnpm install

# 2. iOS 首次准备（执行目录：项目根目录/ios）
bundle install
bundle exec pod install

# 3. 启动（执行目录：项目根目录，二选一）
pnpm ios       # 启动 iOS 模拟器
pnpm android   # 启动 Android 模拟器

# Metro 开发服务默认端口 8081，模拟器自动连接
```

## 常用命令

| 命令 | 说明 |
| --- | --- |
| `pnpm start` | 启动 Metro 开发服务（端口 8081） |
| `pnpm ios` | 编译并运行到 iOS 模拟器 / 真机 |
| `pnpm android` | 编译并运行到 Android 模拟器 / 真机 |
| `pnpm test` | 运行 Jest 测试（渲染 + 状态流转） |
| `pnpm lint` | ESLint 代码检查 |

## 目录结构

```
├── App.tsx                  # 应用根组件（SafeArea + 原生导航栈）
├── index.js                 # 注册应用入口
├── app.json                 # 应用名 / 显示名配置
├── .npmrc                   # pnpm 扁平化 node_modules（Metro 兼容）
├── jest.config.js           # Jest 测试配置
├── __tests__/App.test.tsx   # 渲染与状态仓库测试
├── android/                 # Android 原生工程
├── ios/                     # iOS 原生工程（含 Gemfile / Podfile）
└── src/
    ├── screens/
    │   ├── HomeScreen.tsx   # 首页演示（横幅 / 技术栈 / 功能演示 / 计数器）
    │   └── AboutScreen.tsx  # 关于页（导航演示第二屏）
    └── store/demo.ts        # Zustand 演示仓库（state / 派生值 / actions）
```

## 基本引入的东西

- **原生导航栈**：React Navigation 7（native-stack），系统级转场与手势返回
- **状态管理**：Zustand 5，计数器演示 state / 派生值 / actions 标准写法
- **安全区适配**：react-native-safe-area-context（刘海屏 / 底部指示条）
- **TypeScript**：官方 TS 模板 + React Navigation 类型化参数表
- **测试**：Jest + react-test-renderer（组件渲染 + 仓库状态流转）
- **工程化**：ESLint（@react-native 配置）+ Prettier + Babel 预设

## 演示功能说明

- **首页演示**：欢迎横幅 + 技术栈标签 + 功能清单 + Zustand 计数器（步长切换 / 重置 / 派生值）
- **导航演示**：首页 → 关于（原生导航栈，返回手势可用）
- **状态演示**：Zustand 仓库 actions 修改状态，界面自动重渲染

## 作为起步模板使用

1. 复制本目录到新项目，`pnpm install` 后 `pnpm ios` / `pnpm android` 验证
2. 修改 `app.json` 中的 `name` / `displayName` 为你的应用名（原生工程名需重新 init 或手动改，建议 init 时定好）
3. 以 `src/screens/HomeScreen.tsx` 为样板开发业务页面，在 `App.tsx` 的导航栈中注册
4. 以 `src/store/demo.ts` 为样板拆分业务仓库（Zustand）
5. 删除关于页与演示内容，换成自己的业务

## 常用配置

| 配置项 | 文件 | 说明 |
| --- | --- | --- |
| 应用名 / 显示名 | `app.json` | `name`（工程名）/ `displayName`（桌面图标名） |
| Metro 端口 | 启动参数 | 默认 8081，冲突时 `pnpm start -- --port 8082` |
| node_modules 扁平化 | `.npmrc` | `node-linker=hoisted`，Metro / 原生工具链兼容所需 |
| Jest 配置 | `jest.config.js` | 测试预设（@react-native/jest-preset） |
| iOS 依赖 | `ios/Podfile` | 原生依赖版本（pod install 时生效） |
