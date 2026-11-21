/**
 * 路由汇总模块
 * 统一注册各业务子路由（统一 /api 前缀），并提供健康检查接口；
 * 新增业务模块时在本文件追加一行挂载即可。
 */
import express from 'express';
import demoRouter from './demo.js';

const router = express.Router();

/**
 * 健康检查接口（运维探活 / 负载均衡检查）
 * GET /api/health
 */
router.get('/health', (req, res) => {
  res.ok({ status: 'up', time: new Date().toLocaleString() }, '服务正常');
});

// 演示模块路由（/api/demo 增删改查）
router.use(demoRouter);

export default router;
