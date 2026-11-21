/**
 * Koa 应用组装模块
 * 职责：按顺序挂载中间件、静态资源、Swagger 文档、业务路由与错误处理；
 * 将「应用组装」与「启动监听」拆开，测试中可直接引入 app 而不占用固定端口。
 * Koa 洋葱模型：外层中间件包裹内层，errorHandler 必须在最外层捕获所有下游异常。
 */
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import Koa from 'koa';
import cors from '@koa/cors';
import bodyParser from 'koa-bodyparser';
import serve from 'koa-static';
import { koaSwagger } from 'koa2-swagger-ui';
import config from './config/index.js';
import response from './middleware/response.js';
import requestLogger from './middleware/request-logger.js';
import { errorHandler, notFound } from './middleware/error-handler.js';
import apiRouter from './router/index.js';

// ESM 下没有 __dirname，用 import.meta.url 反推当前目录
const __dirname = path.dirname(fileURLToPath(import.meta.url));

/**
 * 创建并返回 Koa 应用实例
 * @returns {import('koa')} Koa 应用
 */
export const createApp = () => {
  const app = new Koa();

  // 1. 最外层：全局异常捕获（后续所有中间件抛错都会被它接住）
  app.use(errorHandler());

  // 2. 基础中间件：跨域 + body 解析（JSON / 表单）
  app.use(
    cors({
      // 配置为 * 时允许全部来源，否则按逗号拆分为来源数组
      origin: config.corsOrigin === '*' ? '*' : config.corsOrigin.split(','),
    }),
  );
  app.use(bodyParser());

  // 3. 业务中间件：统一响应格式 + 请求日志
  app.use(response());
  app.use(requestLogger());

  // 4. 静态资源：public/ 目录挂载到根路径（如 /index.html、/hello.txt）
  app.use(serve(path.join(__dirname, '../public')));

  // 5. Swagger 可视化文档页面（原始描述由 /api/swagger.json 提供）
  app.use(
    koaSwagger({
      routePrefix: '/api/docs',
      swaggerOptions: { url: '/api/swagger.json' },
    }),
  );

  // 6. 业务路由（统一 /api 前缀）
  app.use(apiRouter.routes()).use(apiRouter.allowedMethods());

  // 7. 兜底处理：走到这里仍无响应内容说明没有路由命中
  app.use(notFound());

  return app;
};
