// electron-vite 配置：主进程 / 预加载脚本 / 渲染进程 三段式构建
import { fileURLToPath, URL } from 'node:url';
import { defineConfig, externalizeDepsPlugin } from 'electron-vite';
import vue from '@vitejs/plugin-vue';
import vueJsx from '@vitejs/plugin-vue-jsx';
import AutoImport from 'unplugin-auto-import/vite';
import Components from 'unplugin-vue-components/vite';
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers';

export default defineConfig({
  // 主进程（Node 环境）：外部化依赖，交给 Electron 运行时加载
  main: {
    plugins: [externalizeDepsPlugin()],
    build: {
      rollupOptions: {
        // 显式声明主进程入口（产物输出到 out/main/index.js）
        input: { index: fileURLToPath(new URL('./electron/main.js', import.meta.url)) },
      },
    },
  },
  // 预加载脚本（受限 Node 环境）：外部化依赖
  preload: {
    plugins: [externalizeDepsPlugin()],
    build: {
      rollupOptions: {
        // 显式声明预加载脚本入口（产物输出到 out/preload/index.js）
        input: { index: fileURLToPath(new URL('./electron/preload.js', import.meta.url)) },
      },
    },
  },
  // 渲染进程（浏览器环境）：Vue3 应用，配置与 vue3 脚手架保持一致
  renderer: {
    // 渲染进程根目录设为项目根（index.html 与 src/ 的结构和 vue3 脚手架完全一致）
    root: fileURLToPath(new URL('.', import.meta.url)),
    plugins: [
      vue(),
      vueJsx(),
      AutoImport({
        resolvers: [ElementPlusResolver()],
      }),
      Components({
        resolvers: [ElementPlusResolver()],
      }),
    ],
    resolve: {
      alias: {
        // 路径别名：@ 指向 src 目录
        '@': fileURLToPath(new URL('./src', import.meta.url)),
      },
      extensions: ['.js', '.ts', '.tsx', '.jsx'],
    },
    // 相对路径基址：生产模式下 Electron 通过 file:// 加载构建产物
    base: './',
    build: {
      rolldownOptions: {
        // 显式声明渲染进程入口 HTML
        // （electron-vite 默认约定入口在 src/renderer/index.html，本模板为对齐 vue3 脚手架结构放在项目根目录）
        input: fileURLToPath(new URL('./index.html', import.meta.url)),
      },
    },
    server: {
      // 开发端口与 vue3 脚手架保持一致
      port: 5005,
    },
  },
});
