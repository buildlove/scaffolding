package com.androidscaffoldingtemplate.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

/**
 * 亮色配色方案
 * 主色 / 辅助色与脚手架家族首页渐变保持一致
 */
private val LightColorScheme = lightColorScheme(
    primary = Indigo600,
    onPrimary = Color.White,
    primaryContainer = IndigoContainerLight,
    onPrimaryContainer = IndigoOnContainerLight,
    secondary = Blue500,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFDBEAFE),
    onSecondaryContainer = Color(0xFF172554),
    tertiary = Sky500,
    onTertiary = Color.White,
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = Color.White,
    onSurface = TextPrimaryLight,
    surfaceVariant = Color(0xFFEEF2F7),
    onSurfaceVariant = TextSecondaryLight,
    outline = Color(0xFFD6DEE8),
    error = ErrorRed,
    onError = Color.White,
)

/**
 * 暗色配色方案
 * 采用浅色主色 + 深色背景，保证夜间可读性
 */
private val DarkColorScheme = darkColorScheme(
    primary = Indigo300,
    onPrimary = Color(0xFF1E1B4B),
    primaryContainer = IndigoContainerDark,
    onPrimaryContainer = IndigoContainerLight,
    secondary = Color(0xFF93C5FD),
    onSecondary = Color(0xFF172554),
    secondaryContainer = Color(0xFF1E3A8A),
    onSecondaryContainer = Color(0xFFDBEAFE),
    tertiary = Color(0xFF7DD3FC),
    onTertiary = Color(0xFF082F49),
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = Color(0xFF273449),
    onSurfaceVariant = TextSecondaryDark,
    outline = Color(0xFF475569),
    error = Color(0xFFFCA5A5),
    onError = Color(0xFF450A0A),
)

/**
 * 应用主题
 *
 * @param darkTheme 是否暗色模式，默认跟随系统
 * @param dynamicColor 是否启用 Android 12+ 动态取色（默认关闭，保持家族统一视觉）
 * @param content 页面内容
 */
@Composable
fun AndroidScaffoldingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    // 选择配色：动态取色 > 暗色 > 亮色
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content,
    )
}
