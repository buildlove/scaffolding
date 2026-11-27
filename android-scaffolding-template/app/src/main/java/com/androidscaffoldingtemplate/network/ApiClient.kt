package com.androidscaffoldingtemplate.network

import com.androidscaffoldingtemplate.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络客户端单例
 *
 * 作用：
 *   统一构建 OkHttp + Retrofit，全应用共享同一个实例（线程安全、复用连接池）。
 *
 * 配置说明：
 *   接口基础地址来自 BuildConfig.API_BASE_URL（gradle.properties 的 API_BASE_URL），
 *   更换后端时只改配置，不需要改代码。
 *
 * 真实项目改造建议：
 *   如果项目引入依赖注入（Hilt / Koin），可把这里改成对应模块提供单例。
 */
object ApiClient {

    /** JSON 解析配置：宽容未知字段，便于后端新增字段时客户端不崩溃 */
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        explicitNulls = false
    }

    /** OkHttp 客户端：超时时间 + 调试日志 */
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    // 仅 Debug 包打印请求体，Release 包关闭，避免泄露敏感信息
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                },
            )
            .build()
    }

    /** Retrofit 实例：绑定基础地址、客户端与 JSON 转换器 */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    /** 接口实例：调用方直接使用 ApiClient.service.xxx() */
    val service: ApiService by lazy { retrofit.create(ApiService::class.java) }
}
