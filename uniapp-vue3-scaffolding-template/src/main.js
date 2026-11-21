import { createSSRApp } from 'vue';
import App from './App.vue';
import store from './store';
import './style.css';

/**
 * uni-app 要求导出 createApp 工厂函数
 * 各端编译器（H5 / 小程序 / App）统一调用它创建应用实例
 */
export function createApp() {
  const app = createSSRApp(App);
  // 挂载 Pinia 状态管理
  app.use(store);
  return { app };
}
