<template>
  <!-- 通用卡片容器（视觉还原 Element Plus 的 el-card，多端通用） -->
  <view class="page-card">
    <!-- 卡片头部（标题 + 标签左右布局） -->
    <view v-if="header" class="page-card-header">
      <slot name="header">{{ header }}</slot>
    </view>
    <!-- 卡片内容区 -->
    <view class="page-card-body">
      <slot />
    </view>
  </view>
</template>

<script setup>
// 卡片头部文案（不传则不渲染头部，也可用 header 插槽自定义）
defineProps({
  header: {
    type: String,
    default: '',
  },
});
</script>

<style lang="less" scoped>
/* 卡片：白色背景 + 圆角 + 轻阴影 */
.page-card {
  background: var(--app-card-bg);
  border-radius: var(--app-radius);
  box-shadow: var(--app-shadow);
  overflow: hidden;
}

/* 卡片头部：底部描边分隔 */
.page-card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 16px 20px;
  border-bottom: 1px solid #f0f2f5;
  font-weight: 600;
  font-size: 15px;
}

/* 卡片内容区 */
.page-card-body {
  padding: 20px;
}
</style>
