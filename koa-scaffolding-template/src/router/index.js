/**
 * 路由汇总模块
 * 统一注册各业务子路由（统一 /api 前缀），并提供健康检查与 OpenAPI 描述接口；
 * 新增业务模块时在本文件追加一行挂载即可。
 */
import Router from '@koa/router';
import { swaggerSpec } from '../swagger.js';
import demoRouter from './demo.js';

const router = new Router({ prefix: '/api' });

/**
 * 健康检查接口（运维探活 / 负载均衡检查）
 * GET /api/health
 */
router.get('/health', async (ctx) => {
  ctx.ok({ status: 'up', time: new Date().toLocaleString() }, '服务正常');
});

/**
 * OpenAPI 原始描述（供 /api/docs 页面拉取，也可导入 Apifox / Postman）
 * GET /api/swagger.json
 */
router.get('/swagger.json', async (ctx) => {
  ctx.body = swaggerSpec;
});

// 演示模块路由（/api/demo 增删改查）
router.use(demoRouter.routes());

export default router;
