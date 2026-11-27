package com.androidscaffoldingtemplate.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * 应用路由定义
 *
 * 说明：
 *   底部导航的三个一级页面 + 一个嵌套详情页。
 *   路由集中定义，避免页面字符串散落在各处。
 *
 * @property route 路由路径
 * @property label 底部导航显示名称
 * @property icon 底部导航图标
 */
sealed class AppDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    /** 首页 */
    data object Home : AppDestination(route = "home", label = "首页", icon = Icons.Filled.Home)

    /** 功能演示页 */
    data object Demo : AppDestination(route = "demo", label = "演示", icon = Icons.Filled.PlayArrow)

    /** 关于页 */
    data object About : AppDestination(route = "about", label = "关于", icon = Icons.Filled.Info)

    companion object {
        /** 底部导航展示的一级页面列表 */
        val topLevelDestinations = listOf(Home, Demo, About)

        /** 详情页路由模板（含路径参数占位符） */
        const val DETAIL_ROUTE = "demo/detail/{itemId}"

        /** 详情页路径参数名 */
        const val DETAIL_ARG_ITEM_ID = "itemId"

        /**
         * 生成详情页实际路由
         *
         * @param itemId 节点 ID
         * @return 形如 demo/detail/3 的路由
         */
        fun detailRoute(itemId: Int): String = "demo/detail/$itemId"
    }
}
