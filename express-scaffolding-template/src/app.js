/**
 * Express 应用组装模块
 * 职责：按顺序挂载中间件、静态资源、Swagger 文档、业务路由与错误处理；
 * 将「应用组装」与「启动监听」拆开，测试中可直接引入 app 而不占用固定端口。
 */
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import express from 'express';
import cors from 'cors';
import swaggerUi from 'swagger-ui-express';
import config from './config/index.js';
import response from './middleware/response.js';
import requestLogger from './middleware/request-logger.js';
import { notFound, errorHandler } from './middleware/error-handler.js';
import apiRouter from './router/index.js';
import { swaggerSpec } from './swagger.js';

// ESM 下没有 __dirname，用 import.meta.url 反推当前目录
const __dirname = path.dirname(fileURLToPath(import.meta.url));

/**
 * 创建并返回 Express 应用实例
 * @returns {import('express').Express} Express 应用
 */
export const createApp = () => {
  const app = express();

  // 1. 基础中间件：跨域 + body 解析（JSON / 表单）
  app.use(
    cors({
      // 配置为 * 时允许全部来源，否则按逗号拆分为来源数组
      origin: config.corsOrigin === '*' ? '*' : config.corsOrigin.split(','),
    }),
  );
  app.use(express.json());
  app.use(express.urlencoded({ extended: true }));

  // 2. 业务中间件：统一响应格式 + 请求日志（顺序在路由之前）
  app.use(response());
  app.use(requestLogger());

  // 3. 静态资源：public/ 目录挂载到根路径（如 /index.html、/hello.txt）
  app.use(express.static(path.join(__dirname, '../public')));

  // 4. Swagger 文档：/api/swagger.json 原始描述 + /api/docs 可视化页面
  app.get('/api/swagger.json', (req, res) => {
    res.json(swaggerSpec);
  });
  app.use('/api/docs', swaggerUi.serve, swaggerUi.setup(swaggerSpec));

  // 5. 业务路由（统一 /api 前缀）
  app.use('/api', apiRouter);

  // 6. 兜底处理：404 与全局错误（必须放在所有路由之后）
  app.use(notFound);
  app.use(errorHandler);

  return app;
};
