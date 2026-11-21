<template>
  <!-- 子路由页面（与 vue3 脚手架 template-child.vue 保持一致） -->
  <view class="page">
    <!-- 顶部导航（品牌 + 菜单 + 实时时钟） -->
    <top-nav current="/pages/child/child" />

    <view class="page-body">
      <page-card>
        <template #header>
          <view class="page-header">
            <text>🧩 嵌套路由子路由</text>
            <text class="page-tag is-success">子路由</text>
          </view>
        </template>
        <text class="page-desc">
          当前页面演示页面路由跳转能力（uni-app 通过 pages.json 注册页面，使用 uni.navigateTo 跳转）。
        </text>
        <view class="page-btn is-primary is-plain" @tap="handleBack">返回首页</view>
      </page-card>
    </view>
  </view>
</template>

<script setup>
/**
 * 返回首页（uni.reLaunch 关闭当前页面栈并跳转，保证首页导航高亮正确）
 */
const handleBack = () => {
  uni.reLaunch({ url: '/pages/index/index' });
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

  &.is-success {
    background: #f0f9eb;
    color: #67c23a;
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

    &.is-plain {
      background: #eef2ff;
      border-color: #c7d2fe;
      color: #4f46e5;
    }
  }
}
</style>
