package com.androidscaffoldingtemplate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.androidscaffoldingtemplate.ui.components.BulletRow
import com.androidscaffoldingtemplate.ui.components.CodeBlock
import com.androidscaffoldingtemplate.ui.components.GradientBanner
import com.androidscaffoldingtemplate.ui.components.SectionCard
import com.androidscaffoldingtemplate.ui.viewmodel.DemoUiState

/**
 * 首页
 *
 * 内容与脚手架家族其他模板的首页对齐：
 *   1. 渐变欢迎横幅 + 技术栈标签
 *   2. 状态演示（计数器）
 *   3. 核心能力清单
 *   4. 快速开始命令
 *
 * @param uiState 页面状态
 * @param onIncrease 增加计数
 * @param onDecrease 减少计数
 * @param onStepChange 切换步长
 * @param onReset 重置计数
 * @param modifier 外部修饰符
 */
@Composable
fun HomeScreen(
    uiState: DemoUiState,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onStepChange: (Int) -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // ----- 欢迎横幅 -----
        item {
            GradientBanner(
                title = "你好，原生 Android 👋",
                description = "Kotlin + Jetpack Compose 起步工程，内置底部导航、状态管理与网络层，可直接作为新 App 的起点。",
                tags = listOf(
                    "Kotlin 2.2",
                    "Compose",
                    "Material 3",
                    "Navigation",
                    "StateFlow",
                    "Retrofit 3",
                ),
            )
        }

        // ----- 计数器演示 -----
        item {
            CounterCard(
                uiState = uiState,
                onIncrease = onIncrease,
                onDecrease = onDecrease,
                onStepChange = onStepChange,
                onReset = onReset,
            )
        }

        // ----- 核心能力 -----
        item { CapabilityCard() }

        // ----- 快速开始 -----
        item { QuickStartCard() }
    }
}

/**
 * 计数器演示卡片
 *
 * 演示「ViewModel 持有状态 + StateFlow 驱动界面」的标准写法。
 */
@Composable
private fun CounterCard(
    uiState: DemoUiState,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onStepChange: (Int) -> Unit,
    onReset: () -> Unit,
) {
    SectionCard(title = "状态演示（ViewModel + StateFlow）") {
        // 当前计数：主题主色，突出展示
        Text(
            text = "当前计数：${uiState.count}",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "当前步长：${uiState.step}，切换后按新步长增减",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        // 操作按钮：增加 / 减少 / 重置
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(onClick = onIncrease, modifier = Modifier.weight(1f)) {
                Text(text = "增加")
            }
            OutlinedButton(onClick = onDecrease, modifier = Modifier.weight(1f)) {
                Text(text = "减少")
            }
            TextButton(onClick = onReset) {
                Text(text = "重置")
            }
        }

        // 步长切换：单选 Chip
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf(1, 5, 10).forEach { step ->
                FilterChip(
                    selected = uiState.step == step,
                    onClick = { onStepChange(step) },
                    label = { Text(text = "步长 $step") },
                )
            }
        }
    }
}

/**
 * 核心能力卡片
 */
@Composable
private fun CapabilityCard() {
    SectionCard(title = "核心能力") {
        BulletRow(text = "首页演示：渐变横幅 / 技术栈标签 / 计数器")
        BulletRow(text = "底部导航 + 嵌套路由（列表 → 详情，带路径参数）")
        BulletRow(text = "ViewModel + StateFlow 单向数据流")
        BulletRow(text = "Retrofit 3 + OkHttp 5 + kotlinx.serialization 网络层")
        BulletRow(text = "Material 3 亮 / 暗色主题，默认关闭动态取色")
    }
}

/**
 * 快速开始卡片
 */
@Composable
private fun QuickStartCard() {
    SectionCard(title = "快速开始") {
        CodeBlock(text = "./gradlew assembleDebug   # 构建 Debug APK\n./gradlew installDebug    # 安装到已连接设备")
        Text(
            text = "也可以用 Android Studio 打开本目录，等待 Gradle 同步完成后点击运行按钮。",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
