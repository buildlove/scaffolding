<template>
  <!-- 首页：顶部导航 + 欢迎横幅 + 技术栈介绍 + 功能演示（与 vue3 脚手架首页保持一致） -->
  <view class="home">
    <!-- 顶部导航（品牌 + 菜单 + 实时时钟） -->
    <top-nav current="/pages/index/index" />

    <view class="home-body">
      <!-- 欢迎横幅 -->
      <view class="home-hero">
        <view class="home-hero-title">👋 欢迎使用 uni-app 脚手架</view>
        <view class="home-hero-desc">
          开箱即用的 uni-app 3 + Vue3.5 + Vite + Pinia 演示工程，
          覆盖页面路由、状态管理、请求封装等常用能力，一套代码可发布到 H5、微信小程序与 App。
        </view>
        <view class="home-hero-tags">
          <text v-for="tag in heroTags" :key="tag" class="home-hero-tag">{{ tag }}</text>
        </view>
      </view>

      <!-- 技术栈卡片 -->
      <view class="home-section">
        <view class="home-section-title">技术栈</view>
        <view class="home-stack">
          <view v-for="tech in techList" :key="tech.name" class="home-stack-card">
            <view class="home-stack-icon">{{ tech.icon }}</view>
            <view class="home-stack-name">{{ tech.name }}</view>
            <view class="home-stack-desc">{{ tech.desc }}</view>
          </view>
        </view>
      </view>

      <!-- 功能演示 -->
      <view class="home-section">
        <view class="home-section-title">功能演示</view>
        <view class="home-demo">
          <!-- Pinia 计数器演示 -->
          <page-card class="home-demo-card">
            <template #header>
              <view class="home-demo-header">
                <text>🍍 Pinia 状态管理</text>
                <text class="home-demo-tag is-success">实时联动</text>
              </view>
            </template>
            <view class="home-counter">
              <!-- 计数值：修改后所有引用处自动更新 -->
              <view class="home-counter-num">{{ demoStore.count }}</view>
              <!-- getter 派生值 -->
              <view class="home-counter-tip">双倍值（getters）：{{ demoStore.doubleCount }}</view>
              <view class="home-counter-btns">
                <view class="home-btn is-primary" @tap="demoStore.increment()">+ 1</view>
                <view class="home-btn" @tap="demoStore.decrement()">- 1</view>
                <view class="home-btn is-warning" @tap="demoStore.reset()">重置</view>
              </view>
            </view>
          </page-card>

          <!-- 路由节点 + 分页联动演示 -->
          <page-card class="home-demo-card">
            <template #header>
              <view class="home-demo-header">
                <text>🧭 路由节点（分页联动）</text>
                <text class="home-demo-tag">共 {{ nodeList.length }} 个节点</text>
              </view>
            </template>
            <!-- 当前页节点列表 -->
            <view class="home-nodes">
              <view v-for="item in pagedNodes" :key="item.path" class="home-node-item">
                <text class="home-node-tag" :class="{ 'is-node': item.meta?.node }">
                  层级 {{ item.meta?.level ?? '-' }}
                </text>
                <text class="home-node-name">{{ item.meta?.name || '未命名页面' }}</text>
              </view>
              <view v-if="!pagedNodes.length" class="home-empty">暂无节点</view>
            </view>
            <!-- 分页器：与上方节点列表真实联动 -->
            <view class="home-pagination">
              <view class="home-pagination-info">
                共 {{ nodeList.length }} 条 · 每页 {{ pageSize }} 条
              </view>
              <view class="home-pagination-btns">
                <view
                  class="home-btn is-small"
                  :class="{ 'is-disabled': page <= 1 }"
                  @tap="handleChangePage(page - 1)"
                >
                  上一页
                </view>
                <view
                  v-for="p in pageCount"
                  :key="p"
                  class="home-btn is-small"
                  :class="{ 'is-primary': p === page }"
                  @tap="handleChangePage(p)"
                >
                  {{ p }}
                </view>
                <view
                  class="home-btn is-small"
                  :class="{ 'is-disabled': page >= pageCount }"
                  @tap="handleChangePage(page + 1)"
                >
                  下一页
                </view>
              </view>
            </view>
          </page-card>
        </view>
      </view>
    </view>
  </view>
</template>

<script setup>
import { computed, ref } from 'vue';
import { usePagesStore } from '@/store/pages';
import { useDemoStore } from '@/store/demo';

// 欢迎横幅标签列表
const heroTags = ['Vue 3.5', 'Vite 5', 'Pinia', 'uni-app 3', '多端发布', 'Less'];

// 技术栈介绍数据（图标使用 emoji，避免额外图标依赖）
const techList = [
  { icon: '⚡', name: 'Vue 3.5', desc: '组合式 API 与最新响应式特性' },
  { icon: '📱', name: 'uni-app 3', desc: '一套代码多端发布（H5 / 小程序 / App）' },
  { icon: '🚀', name: 'Vite 5', desc: '极速开发服务器与生产构建' },
  { icon: '🍍', name: 'Pinia', desc: '轻量直观的状态管理方案' },
  { icon: '🌐', name: 'uni.request', desc: '统一请求封装与拦截器' },
  { icon: '🎨', name: 'Less', desc: '样式预处理与全局 CSS 变量主题' },
];

// Pinia 演示仓库（state / getters / actions 写法见 src/store/demo.js）
const demoStore = useDemoStore();

// 页面元信息树仓库（与 src/pages.json 的页面表对应）
const pagesStore = usePagesStore();

// 带 meta.name 的路由节点列表（演示 store 读取）
const nodeList = computed(() =>
  pagesStore.pages.filter((item) => item?.meta?.name)
);

// 分页参数：当前页码与每页条数
const page = ref(1);
const pageSize = ref(2);

// 总页数
const pageCount = computed(() =>
  Math.max(1, Math.ceil(nodeList.value.length / pageSize.value))
);

// 当前页展示的节点（分页与列表真实联动）
const pagedNodes = computed(() => {
  const start = (page.value - 1) * pageSize.value;
  return nodeList.value.slice(start, start + pageSize.value);
});

/**
 * 切换页码（自动修正越界页码）
 * @param {number} target 目标页码
 */
const handleChangePage = (target) => {
  if (target < 1 || target > pageCount.value) return;
  page.value = target;
};
</script>

<style lang="less" scoped>
/* 页面整体：顶部导航 + 纵向卡片流 */
.home {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
}

/* 主体内容区：纵向卡片流 */
.home-body {
  display: flex;
  flex-direction: column;
  gap: 20px;
  padding: 20px;
}

/* 欢迎横幅：渐变背景 + 投影 */
.home-hero {
  padding: 36px 40px;
  border-radius: 16px;
  background: linear-gradient(135deg, #4f46e5 0%, #3b82f6 55%, #06b6d4 100%);
  box-shadow: 0 12px 32px rgba(79, 70, 229, 0.25);
  color: #fff;
}

.home-hero-title {
  margin: 0 0 12px;
  font-size: 28px;
  font-weight: 700;
}

.home-hero-desc {
  margin: 0 0 20px;
  max-width: 720px;
  font-size: 15px;
  line-height: 1.7;
  color: rgba(255, 255, 255, 0.85);
}

/* 横幅标签组 */
.home-hero-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.home-hero-tag {
  padding: 4px 12px;
  border: 1px solid rgba(255, 255, 255, 0.35);
  border-radius: 999px;
  background: rgba(255, 255, 255, 0.15);
  font-size: 12px;
}

/* 区块标题 */
.home-section-title {
  margin: 0 0 12px;
  font-size: 18px;
  color: #1f2937;
  font-weight: 600;
}

/* 技术栈卡片网格：自适应列数 */
.home-stack {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 16px;
}

/* 技术栈卡片 */
.home-stack-card {
  padding: 20px;
  border-radius: var(--app-radius);
  background: var(--app-card-bg);
  box-shadow: var(--app-shadow);
}

.home-stack-icon {
  font-size: 28px;
}

.home-stack-name {
  margin: 10px 0 6px;
  font-size: 16px;
  font-weight: 600;
  color: #1f2937;
}

.home-stack-desc {
  font-size: 13px;
  line-height: 1.6;
  color: var(--app-text-secondary);
}

/* 功能演示双栏网格 */
.home-demo {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(360px, 1fr));
  gap: 16px;
  align-items: start;
}

/* 演示卡片头部 */
.home-demo-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

/* 演示卡片头部标签 */
.home-demo-tag {
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

/* 计数器演示区 */
.home-counter {
  text-align: center;
}

.home-counter-num {
  font-size: 42px;
  font-weight: 700;
  color: #4f46e5;
  font-variant-numeric: tabular-nums;
}

.home-counter-tip {
  margin: 4px 0 16px;
  font-size: 13px;
  color: var(--app-text-secondary);
}

/* 按钮组横向排列 */
.home-counter-btns {
  display: flex;
  justify-content: center;
  gap: 10px;
}

/* 通用按钮（还原 el-button 视觉，多端通用） */
.home-btn {
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

  &.is-warning {
    background: #fdf6ec;
    border-color: #faecd8;
    color: #e6a23c;
  }

  &.is-small {
    padding: 4px 10px;
    font-size: 12px;
  }

  &.is-disabled {
    opacity: 0.5;
    pointer-events: none;
  }
}

/* 路由节点列表 */
.home-nodes {
  display: flex;
  flex-direction: column;
  gap: 10px;
  min-height: 180px;
}

.home-node-item {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 8px 12px;
  border-radius: 8px;
  background: #f7f9fc;
}

/* 节点层级标签 */
.home-node-tag {
  padding: 2px 8px;
  border-radius: 4px;
  background: #f0f2f5;
  color: #909399;
  font-size: 12px;
  white-space: nowrap;

  &.is-node {
    background: #ecf5ff;
    color: #409eff;
  }
}

.home-node-name {
  font-size: 14px;
  color: var(--app-text);
}

/* 空状态提示 */
.home-empty {
  padding: 40px 0;
  text-align: center;
  font-size: 13px;
  color: var(--app-text-secondary);
}

/* 分页器 */
.home-pagination {
  margin-top: 16px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  flex-wrap: wrap;
}

.home-pagination-info {
  font-size: 12px;
  color: var(--app-text-secondary);
}

.home-pagination-btns {
  display: flex;
  align-items: center;
  gap: 6px;
}
</style>
