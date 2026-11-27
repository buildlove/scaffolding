package com.androidscaffoldingtemplate.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.androidscaffoldingtemplate.ui.screens.AboutScreen
import com.androidscaffoldingtemplate.ui.screens.DemoScreen
import com.androidscaffoldingtemplate.ui.screens.HomeScreen
import com.androidscaffoldingtemplate.ui.screens.ItemDetailScreen
import com.androidscaffoldingtemplate.ui.viewmodel.DemoViewModel

/**
 * 应用根组件
 *
 * 结构：
 *   Scaffold（底部导航 + 内容区）
 *     └── NavHost（页面路由）
 *
 * 状态管理：
 *   ViewModel 在此创建并向下传递，首页 / 演示页共享同一份状态。
 *
 * @param demoViewModel 演示 ViewModel，默认由 Compose 自动创建并跟随 Activity 生命周期
 */
@Composable
fun ScaffoldApp(demoViewModel: DemoViewModel = viewModel()) {
    // 订阅 ViewModel 状态：页面不可见时自动停止收集，避免无效刷新
    val uiState by demoViewModel.uiState.collectAsStateWithLifecycle()

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    // 仅在一级页面显示底部导航；详情页进入后隐藏，让内容更聚焦
    val showBottomBar = AppDestination.topLevelDestinations.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    AppDestination.topLevelDestinations.forEach { destination ->
                        NavigationBarItem(
                            selected = currentRoute == destination.route,
                            onClick = {
                                navController.navigate(destination.route) {
                                    // 回到起始页并保存页面状态，切 Tab 不重复堆栈
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // 同一路由不重复入栈
                                    launchSingleTop = true
                                    // 恢复上次离开该 Tab 时的状态
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.label,
                                )
                            },
                            label = { Text(text = destination.label) },
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            // ---------- 首页 ----------
            composable(AppDestination.Home.route) {
                HomeScreen(
                    uiState = uiState,
                    onIncrease = demoViewModel::increase,
                    onDecrease = demoViewModel::decrease,
                    onStepChange = demoViewModel::changeStep,
                    onReset = demoViewModel::reset,
                )
            }

            // ---------- 演示页 ----------
            composable(AppDestination.Demo.route) {
                DemoScreen(
                    uiState = uiState,
                    onPreviousPage = demoViewModel::previousPage,
                    onNextPage = demoViewModel::nextPage,
                    onRequest = demoViewModel::requestRemoteGreeting,
                    onOpenDetail = { itemId ->
                        // 跳转嵌套详情页：把节点 ID 作为路径参数传入
                        navController.navigate(AppDestination.detailRoute(itemId))
                    },
                )
            }

            // ---------- 嵌套详情页（带路径参数） ----------
            composable(
                route = AppDestination.DETAIL_ROUTE,
                arguments = listOf(
                    navArgument(AppDestination.DETAIL_ARG_ITEM_ID) {
                        type = NavType.IntType
                    },
                ),
            ) { entry ->
                val itemId = entry.arguments?.getInt(AppDestination.DETAIL_ARG_ITEM_ID) ?: 0
                ItemDetailScreen(
                    itemId = itemId,
                    onBack = { navController.popBackStack() },
                )
            }

            // ---------- 关于页 ----------
            composable(AppDestination.About.route) {
                AboutScreen()
            }
        }
    }
}
