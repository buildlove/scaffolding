<template>
  <!-- 独立路由页：演示 uni.request 请求封装（与 vue3 脚手架 template2.vue 保持一致） -->
  <view class="page">
    <!-- 顶部导航（品牌 + 菜单 + 实时时钟） -->
    <top-nav current="/pages/request/request" />

    <view class="page-body">
      <page-card>
        <template #header>
          <view class="page-header">
            <text>🌐 请求演示</text>
            <text class="page-tag is-warning">独立路由</text>
          </view>
        </template>
        <text class="page-desc">
          点击按钮发起请求（统一请求封装见 src/utils/request.js，含拦截器与统一错误提示）。
        </text>
        <view class="page-btn is-primary" :class="{ 'is-disabled': loading }" @tap="handleRequest">
          {{ loading ? '请求中...' : '发送请求' }}
        </view>
        <!-- 请求结果展示区：成功输出 JSON，失败输出错误信息 -->
        <view v-if="resultText" class="page-result">{{ resultText }}</view>
      </page-card>
    </view>
  </view>
</template>

<script setup>
import { ref } from 'vue';
import { getSecurityUser } from '@/api';

// 请求加载状态：控制按钮 loading 效果
const loading = ref(false);
// 请求结果文本：展示接口返回或错误信息
const resultText = ref('');

/**
 * 发送演示请求并展示结果
 * 成功时以 JSON 格式展示返回数据，失败时展示错误信息
 */
const handleRequest = async () => {
  if (loading.value) return;
  loading.value = true;
  resultText.value = '';
  try {
    const res = await getSecurityUser();
    resultText.value = JSON.stringify(res, null, 2);
  } catch (error) {
    // 兼容业务错误（拦截器 reject 字符串）与网络错误
    resultText.value = `请求失败：${error?.message || error?.errMsg || error}`;
  } finally {
    loading.value = false;
  }
};
</script>

<style lang="less" scoped>
/* 页面主体 */
.page-body {
  display: flex;
  flex-direction: column;
  gap: 16px;
  padding: 20px;
}

/* 卡片头部 */
.page-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* 头部标签 */
.page-tag {
  padding: 2px 10px;
  border-radius: 999px;
  background: #f0f2f5;
  color: #606266;
  font-size: 12px;
  font-weight: 400;

  &.is-warning {
    background: #fdf6ec;
    color: #e6a23c;
  }
}

/* 按钮（还原 el-button 视觉） */
.page-btn {
  display: inline-block;
  padding: 8px 18px;
  border-radius: 8px;
  background: #f5f7fb;
  border: 1px solid #dcdfe6;
  color: #606266;
  font-size: 14px;
  transition: all 0.2s;

  &.is-primary {
    background: #4f46e5;
    border-color: #4f46e5;
    color: #fff;
  }

  &.is-disabled {
    opacity: 0.6;
    pointer-events: none;
  }
}

/* 请求结果展示区 */
.page-result {
  margin-top: 16px;
  padding: 12px 16px;
  border-radius: 8px;
  background: #f7f9fc;
  font-family: "SF Mono", Menlo, Consolas, monospace;
  font-size: 13px;
  line-height: 1.6;
  color: #606266;
  white-space: pre-wrap;
  word-break: break-all;
}
</style>
