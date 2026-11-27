// ============================================================
// 根工程构建脚本
// 说明：
//   这里只声明插件版本（apply false 表示仅下载、不在根工程应用），
//   具体应用在 app/build.gradle.kts 中完成
// ============================================================

plugins {
    // Android 应用插件（AGP 9 起内置 Kotlin 支持，无需再单独应用 kotlin-android）
    alias(libs.plugins.android.application) apply false
    // Kotlin Compose 编译器插件（Kotlin 2.x 起由该插件接管 Compose 编译）
    alias(libs.plugins.kotlin.compose) apply false
    // Kotlin 序列化插件（JSON 解析使用）
    alias(libs.plugins.kotlin.serialization) apply false
}
