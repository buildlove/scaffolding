package com.androidscaffoldingtemplate.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.androidscaffoldingtemplate.ui.components.BulletRow
import com.androidscaffoldingtemplate.ui.components.SectionCard

/**
 * 节点详情页（嵌套路由）
 *
 * 演示：
 *   1. 携带路径参数（itemId）的路由跳转
 *   2. 页面返回（系统返回键 / 顶部返回按钮均可用）
 *
 * @param itemId 从上一页传入的节点 ID
 * @param onBack 返回上一页
 * @param modifier 外部修饰符
 */
@Composable
fun ItemDetailScreen(
    itemId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // ----- 顶部返回栏 -----
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回",
                )
            }
            Text(
                text = "节点详情",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }

        // ----- 路由参数展示 -----
        SectionCard(title = "路由参数") {
            Text(
                text = "itemId = $itemId",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "该值由上一页通过 Navigation Compose 的路径参数传入，可在 arguments 中按类型读取。",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        // ----- 嵌套路由说明 -----
        SectionCard(title = "嵌套路由说明") {
            BulletRow(text = "路由注册：composable(AppDestination.DETAIL_ROUTE)")
            BulletRow(text = "参数声明：navArgument(\"itemId\") { type = NavType.IntType }")
            BulletRow(text = "跳转方式：navController.navigate(AppDestination.detailRoute(id))")
            BulletRow(text = "返回方式：navController.popBackStack() 或系统返回键")
        }

        // ----- 底部返回按钮 -----
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "返回列表")
        }
    }
}
