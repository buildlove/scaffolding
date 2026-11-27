package com.androidscaffoldingtemplate

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * 首页 Compose UI 测试（仪器化测试，需真机 / 模拟器）
 *
 * 说明：
 *   验证首页计数器在点击「增加」后界面文本同步更新，
 *   用于确认 ViewModel → StateFlow → Compose 的整条数据链路可用。
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    /** Compose 测试规则：直接启动 MainActivity */
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun counterIncrementsWhenClickingIncrease() {
        // 初始计数为 0
        composeRule.onNodeWithText("当前计数：0").assertIsDisplayed()

        // 点击「增加」
        composeRule.onNodeWithText("增加").performClick()

        // 计数变为 1
        composeRule.onNodeWithText("当前计数：1").assertIsDisplayed()
    }
}
