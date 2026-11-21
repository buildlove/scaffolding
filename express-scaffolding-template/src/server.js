/**
 * 服务启动入口
 * 职责：读取配置 → 创建应用 → 启动监听 → 注册优雅关闭；
 * 只有本文件涉及进程与端口，其余模块保持纯粹以便测试。
 */
import config from './config/index.js';
import { createApp } from './app.js';

// 创建 Express 应用
const app = createApp();

// 启动 HTTP 服务监听
const server = app.listen(config.port, config.host, () => {
  console.log('[express-scaffold] 服务已启动');
  console.log(`[express-scaffold] 接口地址: http://127.0.0.1:${config.port}/api`);
  console.log(`[express-scaffold] Swagger 文档: http://127.0.0.1:${config.port}/api/docs`);
});

/**
 * 优雅关闭：停止接收新请求，等存量请求处理完再退出进程
 * @param {string} signal 触发关闭的信号名
 */
const gracefulShutdown = (signal) => {
  console.log(`[express-scaffold] 收到 ${signal} 信号，开始优雅关闭...`);
  server.close(() => {
    console.log('[express-scaffold] 服务已关闭');
    process.exit(0);
  });
  // 兜底：10 秒仍未关闭则强制退出，避免进程悬挂
  setTimeout(() => process.exit(1), 10000).unref();
};

// 监听常见退出信号（Ctrl+C / 进程管理器下发的终止信号）
process.on('SIGINT', () => gracefulShutdown('SIGINT'));
process.on('SIGTERM', () => gracefulShutdown('SIGTERM'));
