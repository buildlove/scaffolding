package com.androidscaffoldingtemplate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidscaffoldingtemplate.data.DemoRepository
import com.androidscaffoldingtemplate.data.model.NodeItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 网络请求状态
 *
 * 使用密封接口（sealed interface）描述互斥的几种状态，
 * 界面侧用 when 分支穷举，避免出现「加载中又显示错误」这类非法组合。
 */
sealed interface RequestState {
    /** 空闲：尚未发起请求 */
    data object Idle : RequestState

    /** 请求中 */
    data object Loading : RequestState

    /** 请求成功 */
    data object Success : RequestState

    /** 请求失败 */
    data object Error : RequestState
}

/**
 * 页面统一状态
 *
 * 使用不可变数据类 + copy 更新，配合 StateFlow 实现「单向数据流」：
 *   ViewModel 持有唯一状态 → 界面订阅渲染 → 用户操作回调 ViewModel 改状态
 *
 * @property count 计数器当前值
 * @property step 计数器步长
 * @property nodes 当前页的节点列表
 * @property page 当前页码
 * @property totalPages 总页数
 * @property totalCount 总条数
 * @property isLoadingPage 分页数据是否加载中
 * @property requestState 网络请求状态
 * @property requestMessage 网络请求结果文案
 */
data class DemoUiState(
    val count: Int = 0,
    val step: Int = 1,
    val nodes: List<NodeItem> = emptyList(),
    val page: Int = 1,
    val totalPages: Int = 1,
    val totalCount: Int = 0,
    val isLoadingPage: Boolean = false,
    val requestState: RequestState = RequestState.Idle,
    val requestMessage: String = "尚未发起请求",
)

/**
 * 演示页 ViewModel
 *
 * 责任：
 *   1. 持有页面状态（StateFlow）
 *   2. 处理用户操作（计数器 / 分页 / 请求）
 *   3. 调用仓库层获取数据，并在页面销毁时自动取消协程
 *
 * @param repository 数据仓库，默认使用本地模拟实现；单元测试可传入自定义实现
 */
class DemoViewModel(
    private val repository: DemoRepository = DemoRepository(),
) : ViewModel() {

    /** 私有可变状态：只在 ViewModel 内部修改 */
    private val _uiState = MutableStateFlow(DemoUiState())

    /** 对外暴露的只读状态：界面只能订阅，不能直接改 */
    val uiState: StateFlow<DemoUiState> = _uiState.asStateFlow()

    /** 分页任务句柄：翻页时取消上一次未完成的请求，避免旧数据覆盖新数据 */
    private var pageJob: Job? = null

    init {
        // 首次进入即加载第一页数据
        loadPage(page = 1)
    }

    // ==================== 计数器演示 ====================

    /** 按当前步长增加计数 */
    fun increase() {
        _uiState.update { state -> state.copy(count = state.count + state.step) }
    }

    /** 按当前步长减少计数 */
    fun decrease() {
        _uiState.update { state -> state.copy(count = state.count - state.step) }
    }

    /** 切换步长（1 / 5 / 10） */
    fun changeStep(step: Int) {
        _uiState.update { state -> state.copy(step = step) }
    }

    /** 重置计数为 0 */
    fun reset() {
        _uiState.update { state -> state.copy(count = 0) }
    }

    // ==================== 分页联动演示 ====================

    /**
     * 加载指定页
     *
     * @param page 目标页码（仓库层会自动收敛到合法范围）
     */
    fun loadPage(page: Int) {
        // 取消上一次仍在进行的分页请求
        pageJob?.cancel()
        pageJob = viewModelScope.launch {
            _uiState.update { state -> state.copy(isLoadingPage = true) }

            val result = repository.fetchNodes(page = page, pageSize = PAGE_SIZE)

            // 使用仓库返回的页码（已收敛），保证末页 / 越界场景显示正确
            _uiState.update { state ->
                state.copy(
                    nodes = result.items,
                    page = result.page,
                    totalPages = result.totalPages,
                    totalCount = result.totalCount,
                    isLoadingPage = false,
                )
            }
        }
    }

    /** 上一页（已在首页时保持不变） */
    fun previousPage() {
        loadPage((_uiState.value.page - 1).coerceAtLeast(1))
    }

    /** 下一页（已在末页时保持不变） */
    fun nextPage() {
        loadPage((_uiState.value.page + 1).coerceAtMost(_uiState.value.totalPages))
    }

    // ==================== 网络请求演示 ====================

    /**
     * 发起一次模拟请求
     *
     * @param shouldFail 是否模拟失败，用于演示错误态
     */
    fun requestRemoteGreeting(shouldFail: Boolean = false) {
        // 请求进行中不允许重复触发，避免状态错乱
        if (_uiState.value.requestState == RequestState.Loading) return

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    requestState = RequestState.Loading,
                    requestMessage = "请求中…",
                )
            }

            // runCatching 统一捕获成功 / 失败，避免未捕获异常导致崩溃
            runCatching { repository.fetchRemoteGreeting(shouldFail = shouldFail) }
                .onSuccess { message ->
                    _uiState.update { state ->
                        state.copy(
                            requestState = RequestState.Success,
                            requestMessage = message,
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { state ->
                        state.copy(
                            requestState = RequestState.Error,
                            requestMessage = error.message ?: "请求失败，请稍后重试",
                        )
                    }
                }
        }
    }

    companion object {
        /** 每页条数（固定值，便于演示分页边界） */
        const val PAGE_SIZE = 6
    }
}
