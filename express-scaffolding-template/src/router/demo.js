/**
 * 演示模块路由（/api/demo）
 * 本文件同时承担 Swagger 注释职责：swagger-jsdoc 扫描 @openapi 注释生成接口文档，
 * 接口有变动时只需维护本文件的 JSDoc，文档自动同步。
 */
import express from 'express';
import * as demoController from '../controller/demo.js';

const router = express.Router();

/**
 * @openapi
 * /api/demo:
 *   get:
 *     tags: [演示模块]
 *     summary: 获取演示数据列表（分页）
 *     parameters:
 *       - in: query
 *         name: page
 *         schema: { type: integer, default: 1 }
 *         description: 页码（从 1 开始）
 *       - in: query
 *         name: pageSize
 *         schema: { type: integer, default: 10 }
 *         description: 每页条数
 *     responses:
 *       200:
 *         description: 分页数据
 *         content:
 *           application/json:
 *             schema:
 *               allOf:
 *                 - $ref: '#/components/schemas/Result'
 *                 - type: object
 *                   properties:
 *                     data:
 *                       type: object
 *                       properties:
 *                         total: { type: integer, description: 总条数 }
 *                         page: { type: integer, description: 当前页码 }
 *                         pageSize: { type: integer, description: 每页条数 }
 *                         records:
 *                           type: array
 *                           items: { $ref: '#/components/schemas/Demo' }
 */
router.get('/demo', demoController.list);

/**
 * @openapi
 * /api/demo/{id}:
 *   get:
 *     tags: [演示模块]
 *     summary: 获取单条演示数据
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema: { type: integer }
 *         description: 数据主键
 *     responses:
 *       200:
 *         description: 数据详情
 *         content:
 *           application/json:
 *             schema:
 *               allOf:
 *                 - $ref: '#/components/schemas/Result'
 *                 - type: object
 *                   properties:
 *                     data: { $ref: '#/components/schemas/Demo' }
 *       404:
 *         description: 数据不存在
 */
router.get('/demo/:id', demoController.detail);

/**
 * @openapi
 * /api/demo:
 *   post:
 *     tags: [演示模块]
 *     summary: 新增演示数据
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             required: [title]
 *             properties:
 *               title: { type: string, description: 标题（必填） }
 *               content: { type: string, description: 内容 }
 *     responses:
 *       200:
 *         description: 创建成功
 *       400:
 *         description: 参数校验失败
 */
router.post('/demo', demoController.create);

/**
 * @openapi
 * /api/demo/{id}:
 *   put:
 *     tags: [演示模块]
 *     summary: 更新演示数据
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema: { type: integer }
 *         description: 数据主键
 *     requestBody:
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               title: { type: string, description: 标题 }
 *               content: { type: string, description: 内容 }
 *     responses:
 *       200:
 *         description: 更新成功
 *       404:
 *         description: 数据不存在
 */
router.put('/demo/:id', demoController.update);

/**
 * @openapi
 * /api/demo/{id}:
 *   delete:
 *     tags: [演示模块]
 *     summary: 删除演示数据
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema: { type: integer }
 *         description: 数据主键
 *     responses:
 *       200:
 *         description: 删除成功
 *       404:
 *         description: 数据不存在
 */
router.delete('/demo/:id', demoController.remove);

export default router;
