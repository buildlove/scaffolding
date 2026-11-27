package com.androidscaffoldingtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.androidscaffoldingtemplate.ui.navigation.ScaffoldApp
import com.androidscaffoldingtemplate.ui.theme.AndroidScaffoldingTheme

/**
 * 应用唯一入口 Activity
 *
 * 架构说明：
 *   单 Activity + Jetpack Compose，页面切换全部交给 Navigation Compose 处理。
 *   这样做的好处是：页面状态、转场动画、返回栈都由 Compose 统一管理，无需多 Activity 跳转。
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 开启边到边显示：内容延伸到状态栏 / 导航栏下方，由 Compose 的 Scaffold 统一处理安全区
        enableEdgeToEdge()

        setContent {
            // 应用 Material 3 主题（默认跟随系统亮 / 暗色）
            AndroidScaffoldingTheme {
                // 应用根组件：底部导航 + 路由
                ScaffoldApp()
            }
        }
    }
}
