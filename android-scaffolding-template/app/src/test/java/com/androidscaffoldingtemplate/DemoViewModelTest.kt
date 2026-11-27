package com.androidscaffoldingtemplate

import com.androidscaffoldingtemplate.ui.viewmodel.DemoViewModel
import com.androidscaffoldingtemplate.ui.viewmodel.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * DemoViewModel 单元测试
 *
 * 说明：
 *   - 用 StandardTestDispatcher 替换主线程调度器，让 viewModelScope 的协程可被测试控制
 *   - 用 advanceUntilIdle() 推进虚拟时间，跳过仓库层模拟的 delay
 *   - 全部在 JVM 上运行，无需真机 / 模拟器
 */
@OptIn(ExperimentalCoroutinesApi::class)
class DemoViewModelTest {

    /** 测试调度器：手动推进时间 */
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // 把 ViewModel 使用的 Main 调度器替换为测试调度器
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // 测试结束后恢复真实 Main 调度器
        Dispatchers.resetMain()
    }

    /**
     * 计数器：按步长增减，重置归零
     */
    @Test
    fun counterFollowsStepAndReset() = runTest(testDispatcher) {
        val viewModel = DemoViewModel()
        advanceUntilIdle()

        // 默认步长 1：增加一次为 1
        viewModel.increase()
        assertEquals(1, viewModel.uiState.value.count)

        // 切换步长为 5 后再加一次：1 + 5 = 6
        viewModel.changeStep(5)
        viewModel.increase()
        assertEquals(6, viewModel.uiState.value.count)

        // 减一次：6 - 5 = 1
        viewModel.decrease()
        assertEquals(1, viewModel.uiState.value.count)

        // 重置归零
        viewModel.reset()
        assertEquals(0, viewModel.uiState.value.count)
    }

    /**
     * 分页：首页 / 末页边界收敛正确
     */
    @Test
    fun paginationClampsToBoundaries() = runTest(testDispatcher) {
        val viewModel = DemoViewModel()
        advanceUntilIdle()

        // 初始第 1 页，共 42 条 / 每页 6 条 = 7 页
        assertEquals(1, viewModel.uiState.value.page)
        assertEquals(7, viewModel.uiState.value.totalPages)
        assertEquals(6, viewModel.uiState.value.nodes.size)

        // 下一页 → 第 2 页
        viewModel.nextPage()
        advanceUntilIdle()
        assertEquals(2, viewModel.uiState.value.page)

        // 越界请求 99 页 → 收敛到末页 7
        viewModel.loadPage(99)
        advanceUntilIdle()
        assertEquals(7, viewModel.uiState.value.page)

        // 末页继续下一页 → 仍为 7
        viewModel.nextPage()
        advanceUntilIdle()
        assertEquals(7, viewModel.uiState.value.page)

        // 上一页 → 6
        viewModel.previousPage()
        advanceUntilIdle()
        assertEquals(6, viewModel.uiState.value.page)
    }

    /**
     * 网络请求：成功与失败状态切换正确
     */
    @Test
    fun requestSuccessThenFailure() = runTest(testDispatcher) {
        val viewModel = DemoViewModel()
        advanceUntilIdle()

        // 成功分支
        viewModel.requestRemoteGreeting(shouldFail = false)
        advanceUntilIdle()
        assertEquals(RequestState.Success, viewModel.uiState.value.requestState)

        // 失败分支
        viewModel.requestRemoteGreeting(shouldFail = true)
        advanceUntilIdle()
        assertEquals(RequestState.Error, viewModel.uiState.value.requestState)
    }
}
