// ============================================================
// app 模块构建脚本
// 作用：
//   配置 Android 应用（包名 / 版本 / 编译参数 / Compose）与依赖
// ============================================================

import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    // 说明：AGP 9 起 Kotlin 支持已内置，不能再应用 org.jetbrains.kotlin.android
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

// 接口基础地址：默认取 gradle.properties 中的 API_BASE_URL，可在命令行用 -PAPI_BASE_URL=xxx 覆盖
val apiBaseUrl: String = providers.gradleProperty("API_BASE_URL")
    .orElse("https://jsonplaceholder.typicode.com/")
    .get()

android {
    // 应用命名空间（R 类所在包名，与源码目录保持一致）
    namespace = "com.androidscaffoldingtemplate"
    // 编译使用的 Android SDK 版本（Android 17 / API 37.2，需 AGP 9.1+）
    compileSdk = 37
    // 次版本号：Compose 1.13 起要求 compileSdk 至少 37.1，这里取 37.2
    compileSdkMinor = 2

    defaultConfig {
        // 应用唯一标识（安装到设备上的包名）
        applicationId = "com.androidscaffoldingtemplate"
        // 最低支持 Android 8.0（api 26）：可直接使用自适应图标，覆盖绝大多数在用机型
        minSdk = 26
        // 目标 SDK：跟随最新稳定版本
        targetSdk = 37
        // 版本号与版本名
        versionCode = 1
        versionName = "1.0.0"

        // 仪器化测试运行器（Compose UI 测试需要）
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        // 兼容旧版 VectorDrawable 解析方式
        vectorDrawables { useSupportLibrary = true }

        // 将接口地址注入 BuildConfig，供网络层直接读取
        buildConfigField("String", "API_BASE_URL", "\"$apiBaseUrl\"")
    }

    buildTypes {
        // Debug：默认即可，供日常调试
        debug {
            // Debug 包名追加后缀，可与 Release 包同时安装
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isMinifyEnabled = false
        }
        // Release：开启混淆配置（模板默认不开启压缩，便于起步阶段排查问题）
        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        // Java 编译目标：17（AGP 8.x 推荐值）
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        // 开启 Compose 支持
        compose = true
        // 开启 BuildConfig（注入接口地址需要）
        buildConfig = true
    }

    packaging {
        // 打包时排除重复的许可证文件，避免冲突
        resources.excludes += setOf(
            "/META-INF/{AL2.0,LGPL2.1}",
            "META-INF/DEPENDENCIES",
        )
    }
}

kotlin {
    compilerOptions {
        // Kotlin 编译目标与 Java 保持一致
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    // ---------- Compose 版本清单：统一管理所有 Compose 依赖版本 ----------
    implementation(platform(libs.androidx.compose.bom))

    // ---------- AndroidX 基础 ----------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)

    // ---------- Compose UI / Material 3 ----------
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.core)

    // ---------- Kotlin 生态 ----------
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    // ---------- 网络 ----------
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)

    // ---------- 单元测试（JVM，无需真机） ----------
    testImplementation(libs.junit)
    testImplementation(libs.kotlinx.coroutines.test)

    // ---------- 仪器化测试（真机 / 模拟器） ----------
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // ---------- 调试期工具 ----------
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
