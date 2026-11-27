// ============================================================
// Gradle 设置文件
// 作用：
//   1. 声明插件与依赖的下载仓库（Google / Maven Central）
//   2. 声明工程的模块组成（当前仅一个 app 模块）
// 说明：本文件为工程根级配置，所有模块共享这里的仓库设置
// ============================================================

pluginManagement {
    // 插件仓库：AGP 只从 Google 仓库解析，其余插件走 Maven Central / Gradle 插件门户
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    // 禁止在子模块中单独声明仓库，统一由此处管理，避免仓库配置散落
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

// 工程名（Android Studio 打开时显示的工程标识）
rootProject.name = "AndroidScaffoldingTemplate"

// 引入应用主模块
include(":app")
