package com.androidscaffoldingtemplate.data

import com.androidscaffoldingtemplate.data.model.NodeItem
import com.androidscaffoldingtemplate.data.model.NodePage
import kotlinx.coroutines.delay

/**
 * 演示数据仓库
 *
 * 作用：
 *   为首页 / 演示页提供本地模拟数据，保证模板在没有后端的情况下也能完整跑通交互。
 *
 * 真实项目改造建议：
 *   把这里替换成 Retrofit 的 ApiService 调用（见 network/ApiClient.kt），
 *   ViewModel 与界面的代码不需要改动，这就是分层的价值。
 *
 * @param pageDelayMillis 分页请求模拟耗时（毫秒），单元测试中会走虚拟时间
 * @param requestDelayMillis 模拟网络请求耗时（毫秒）
 */
class DemoRepository(
    private val pageDelayMillis: Long = 300L,
    private val requestDelayMillis: Long = 800L,
) {

    /**
     * 分页查询节点列表
     *
     * @param page 请求页码（从 1 开始，越界会被自动收敛）
     * @param pageSize 每页条数
     * @return 收敛后的分页结果
     */
    suspend fun fetchNodes(page: Int, pageSize: Int): NodePage {
        // 模拟网络 / 数据库耗时，避免界面瞬时闪烁
        delay(pageDelayMillis)

        // 固定 42 条演示数据，方便验证分页边界（42 / 6 = 7 页）
        val totalCount = 42
        val totalPages = ((totalCount + pageSize - 1) / pageSize).coerceAtLeast(1)
        // 页码收敛到合法区间，防止负数或超出末页
        val safePage = page.coerceIn(1, totalPages)
        val startIndex = (safePage - 1) * pageSize
        val endIndex = minOf(startIndex + pageSize, totalCount)

        val items = (startIndex until endIndex).map { index ->
            NodeItem(
                id = index + 1,
                title = "路由节点 ${index + 1}",
                description = "第 ${index + 1} 个演示节点，点击可查看详情页路由参数",
            )
        }

        return NodePage(
            items = items,
            page = safePage,
            totalPages = totalPages,
            totalCount = totalCount,
        )
    }

    /**
     * 模拟一次远程接口请求
     *
     * @param shouldFail 为 true 时抛出异常，用于演示错误态处理
     * @return 成功时的返回文案
     */
    suspend fun fetchRemoteGreeting(shouldFail: Boolean = false): String {
        // 模拟接口耗时
        delay(requestDelayMillis)

        // 演示失败分支：真实项目可替换为 HTTP 非 2xx / 网络异常处理
        if (shouldFail) {
            throw IllegalStateException("模拟请求失败：请检查网络或稍后重试")
        }

        return "接口返回：Hello from Native Android（模拟请求成功）"
    }
}
