import { defineConfig } from 'vite';
import { resolve } from 'path';
import uni from '@dcloudio/vite-plugin-uni';

// https://vitejs.dev/config/
// uni-app 多端编译配置：各端差异由 uni 插件统一处理
export default defineConfig({
  plugins: [uni()],
  resolve: {
    alias: {
      // 路径别名：@ 指向 src 目录
      '@': resolve(__dirname, 'src'),
    },
  },
  server: {
    host: true,
    // 开发端口与 vue3 脚手架保持一致
    port: 5005,
  },
});
