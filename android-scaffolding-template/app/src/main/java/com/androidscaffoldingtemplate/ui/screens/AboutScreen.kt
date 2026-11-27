package com.androidscaffoldingtemplate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidscaffoldingtemplate.BuildConfig
import com.androidscaffoldingtemplate.ui.components.BulletRow
import com.androidscaffoldingtemplate.ui.components.GradientBanner
import com.androidscaffoldingtemplate.ui.components.InfoRow
import com.androidscaffoldingtemplate.ui.components.SectionCard

/**
 * 关于页
 *
 * 展示模板版本、技术栈与改造指引，作为新项目的「说明书」。
 *
 * @param modifier 外部修饰符
 */
@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // ----- 顶部横幅 -----
        item {
            GradientBanner(
                title = "关于模板",
                description = "Android 原生起步工程，单 Activity + Compose 架构，开箱即用。",
                tags = listOf("Kotlin", "Compose", "Material 3", "Android 8.0+"),
            )
        }

        // ----- 版本信息 -----
        item {
            SectionCard(title = "版本信息") {
                InfoRow(label = "应用版本", value = BuildConfig.VERSION_NAME)
                InfoRow(label = "包名", value = BuildConfig.APPLICATION_ID)
                InfoRow(label = "最低系统", value = "Android 8.0（API 26）")
                InfoRow(label = "接口地址", value = BuildConfig.API_BASE_URL)
            }
        }

        // ----- 技术栈 -----
        item {
            SectionCard(title = "技术栈") {
                InfoRow(label = "语言", value = "Kotlin 2.2")
                InfoRow(label = "UI", value = "Jetpack Compose + Material 3")
                InfoRow(label = "导航", value = "Navigation Compose 2.10")
                InfoRow(label = "状态", value = "ViewModel + StateFlow")
                InfoRow(label = "网络", value = "Retrofit 3 / OkHttp 5")
                InfoRow(label = "序列化", value = "kotlinx.serialization")
                InfoRow(label = "构建", value = "AGP 9.5 / Gradle 9.6")
            }
        }

        // ----- 改造指引 -----
        item {
            SectionCard(title = "作为起步模板使用") {
                BulletRow(text = "改包名：Android Studio 中 Refactor → Rename，或全局替换 com.androidscaffoldingtemplate")
                BulletRow(text = "改应用名：res/values/strings.xml 的 app_name")
                BulletRow(text = "改接口地址：gradle.properties 的 API_BASE_URL")
                BulletRow(text = "加页面：ui/screens 新建 Compose 页面，在 AppDestination 与 ScaffoldApp 注册")
                BulletRow(text = "接后端：用 network/ApiClient 的 ApiService 替换 DemoRepository 的模拟数据")
                BulletRow(text = "删演示：移除首页 / 演示页的演示区块，保留主题、导航与网络层")
            }
        }

        // ----- 页脚 -----
        item {
            Text(
                text = "脚手架模板集合 · 与家族其他模板首页 / README 风格保持一致",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}
