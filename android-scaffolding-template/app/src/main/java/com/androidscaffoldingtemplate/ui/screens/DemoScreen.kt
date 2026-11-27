package com.androidscaffoldingtemplate.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.androidscaffoldingtemplate.ui.components.GradientBanner
import com.androidscaffoldingtemplate.ui.components.SectionCard
import com.androidscaffoldingtemplate.ui.theme.SuccessGreen
import com.androidscaffoldingtemplate.ui.viewmodel.DemoUiState
import com.androidscaffoldingtemplate.ui.viewmodel.RequestState

/**
 * 功能演示页
 *
 * 包含三块演示：
 *   1. 分页联动（上一页 / 下一页 + 列表）
 *   2. 列表 → 详情嵌套路由
 *   3. 网络请求状态（加载 / 成功 / 失败）
 *
 * @param uiState 页面状态
 * @param onPreviousPage 上一页
 * @param onNextPage 下一页
 * @param onRequest 发起请求（参数为 true 表示模拟失败）
 * @param onOpenDetail 打开详情页
 * @param modifier 外部修饰符
 */
@Composable
fun DemoScreen(
    uiState: DemoUiState,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit,
    onRequest: (Boolean) -> Unit,
    onOpenDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // ----- 顶部说明横幅 -----
        item {
            GradientBanner(
                title = "功能演示",
                description = "分页联动、嵌套路由、网络请求状态，三种常用交互的最小实现。",
                tags = listOf("Paging", "Navigation", "Retrofit", "StateFlow"),
            )
        }

        // ----- 分页联动 -----
        item {
            SectionCard(title = "路由节点分页联动") {
                Text(
                    text = "第 ${uiState.page} / ${uiState.totalPages} 页 · 共 ${uiState.totalCount} 条",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                // 加载中显示进度条
                if (uiState.isLoadingPage) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                // 当前页节点列表：点击进入详情
                uiState.nodes.forEach { node ->
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenDetail(node.id) },
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(2.dp),
                            ) {
                                Text(
                                    text = node.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                )
                                Text(
                                    text = node.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "查看详情",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        }
                    }
                }

                // 翻页按钮：到达边界自动禁用
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    OutlinedButton(
                        onClick = onPreviousPage,
                        enabled = uiState.page > 1,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(text = "上一页")
                    }
                    Button(
                        onClick = onNextPage,
                        enabled = uiState.page < uiState.totalPages,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(text = "下一页")
                    }
                }
            }
        }

        // ----- 网络请求状态 -----
        item {
            SectionCard(title = "网络请求演示（本地模拟）") {
                Text(
                    text = uiState.requestMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    color = when (uiState.requestState) {
                        RequestState.Success -> SuccessGreen
                        RequestState.Error -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    },
                )

                // 请求中显示不确定进度条
                if (uiState.requestState == RequestState.Loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { onRequest(false) },
                        enabled = uiState.requestState != RequestState.Loading,
                    ) {
                        Text(text = "请求接口")
                    }
                    OutlinedButton(
                        onClick = { onRequest(true) },
                        enabled = uiState.requestState != RequestState.Loading,
                    ) {
                        Text(text = "模拟失败")
                    }
                }

                Text(
                    text = "真实接口调用示例见 network/ApiClient.kt（Retrofit + OkHttp）。",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
