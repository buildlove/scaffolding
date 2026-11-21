<template>
  <!-- 顶部导航栏：品牌 + 菜单 + 实时时钟（视觉与 vue3 脚手架 layout.vue 保持一致） -->
  <view class="top-nav">
    <!-- 品牌区：Logo 与项目名称 -->
    <view class="top-nav-brand">
      <view class="top-nav-logo">U3</view>
      <view class="top-nav-name">
        <view class="top-nav-title">uni-app 脚手架演示</view>
        <view class="top-nav-subtitle">uni-app 3 · Vue3.5 · Vite · Pinia</view>
      </view>
    </view>

    <!-- 主导航：与页面路由联动，自动高亮当前页面 -->
    <view class="top-nav-menu">
      <view
        v-for="item in navList"
        :key="item.path"
        class="top-nav-item"
        :class="{ 'is-active': item.path === current }"
        @tap="handleNav(item)"
      >
        {{ item.label }}
      </view>
    </view>

    <!-- 右侧实时时钟：演示定时器与组件卸载清理 -->
    <view class="top-nav-clock">🕐 {{ currentTime }}</view>
  </view>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

// 当前页面路径（由使用方传入，用于菜单高亮）
const props = defineProps({
  current: {
    type: String,
    default: '',
  },
});

// 顶部导航配置：path 为页面路由完整路径（与 pages.json 对应）
const navList = [
  { label: '嵌套路由', path: '/pages/index/index' },
  { label: '二级子路由', path: '/pages/child/child' },
  { label: '独立路由', path: '/pages/request/request' },
];

// 当前时间字符串，每秒刷新一次
const currentTime = ref('');
// 定时器 id：组件卸载时清理，避免内存泄漏
let timer = null;

/**
 * 将日期格式化为 YYYY-MM-DD HH:mm:ss
 * @param {Date} date 日期对象
 * @returns {string} 格式化后的时间字符串
 */
const formatTime = (date) => {
  const pad = (n) => String(n).padStart(2, '0');
  return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())} ${pad(date.getHours())}:${pad(date.getMinutes())}:${pad(date.getSeconds())}`;
};

/**
 * 导航点击：跳转到对应页面（重复点击当前页不跳转）
 * 顶部主导航切换使用 uni.reLaunch（重置页面栈，效果等同 vue-router 的路由切换）
 * 页面内下钻跳转请使用 uni.navigateTo，返回使用 uni.navigateBack
 * @param {Object} item 导航配置项
 */
const handleNav = (item) => {
  if (item.path === props.current) return;
  uni.reLaunch({ url: item.path });
};

// 挂载后启动时钟定时器
onMounted(() => {
  currentTime.value = formatTime(new Date());
  timer = setInterval(() => {
    currentTime.value = formatTime(new Date());
  }, 1000);
});

// 卸载时清理定时器
onUnmounted(() => {
  clearInterval(timer);
});
</script>

<style lang="less" scoped>
/* 顶部导航栏：品牌 + 菜单 + 时钟 三段式 */
.top-nav {
  display: flex;
  align-items: center;
  gap: 24px;
  height: 64px;
  padding: 0 24px;
  background: var(--app-card-bg);
  border-bottom: 1px solid #e5eaf3;
}

/* 品牌区 */
.top-nav-brand {
  display: flex;
  align-items: center;
  gap: 12px;
  flex-shrink: 0;
}

/* Logo：渐变色圆角方块 */
.top-nav-logo {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  background: linear-gradient(135deg, #4f46e5, #3b82f6);
  color: #fff;
  font-size: 16px;
  font-weight: 700;
}

/* 项目名称 */
.top-nav-title {
  font-size: 16px;
  font-weight: 600;
  color: #1f2937;
  white-space: nowrap;
}

/* 项目副标题（技术栈） */
.top-nav-subtitle {
  font-size: 12px;
  color: var(--app-text-secondary);
  white-space: nowrap;
}

/* 主导航 */
.top-nav-menu {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-left: 16px;
  flex-shrink: 0;
}

/* 导航项：点击变色，激活态渐变底 */
.top-nav-item {
  padding: 6px 16px;
  border-radius: 8px;
  color: #606266;
  font-size: 14px;
  white-space: nowrap;
  transition: all 0.2s;

  &.is-active {
    color: #fff;
    background: linear-gradient(135deg, #4f46e5, #3b82f6);
  }
}

/* 右侧时钟：等宽数字避免抖动 */
.top-nav-clock {
  margin-left: auto;
  font-size: 13px;
  color: var(--app-text-secondary);
  font-variant-numeric: tabular-nums;
  white-space: nowrap;
}
</style>
