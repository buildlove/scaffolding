/**
 * 演示模块控制器
 * 职责：解析 / 校验入参 → 调用服务层 → 通过统一响应方法返回结果；
 * 控制器不写业务逻辑，业务逻辑一律下沉到 service。
 */
import * as demoService from '../service/demo.js';

/**
 * 获取演示数据列表（分页）
 * GET /api/demo?page=1&pageSize=10
 */
export const list = (req, res) => {
  // 从查询字符串取分页参数并转为数字（前端未传时使用默认值）
  const page = Number(req.query.page) || 1;
  const pageSize = Number(req.query.pageSize) || 10;
  res.ok(demoService.findAll({ page, pageSize }));
};

/**
 * 获取单条演示数据
 * GET /api/demo/:id
 */
export const detail = (req, res) => {
  const id = Number(req.params.id);
  const item = demoService.findById(id);
  // 数据不存在时返回 404 语义
  if (!item) {
    res.fail('数据不存在', 404, 404);
    return;
  }
  res.ok(item);
};

/**
 * 新增演示数据
 * POST /api/demo  body: { title, content? }
 */
export const create = (req, res) => {
  const { title, content } = req.body || {};
  // 必填校验：title 不能为空
  if (!title || typeof title !== 'string') {
    res.fail('title 为必填字符串', 400, 400);
    return;
  }
  const item = demoService.create({ title, content });
  res.ok(item, '创建成功');
};

/**
 * 更新演示数据
 * PUT /api/demo/:id  body: { title?, content? }
 */
export const update = (req, res) => {
  const id = Number(req.params.id);
  const { title, content } = req.body || {};
  const item = demoService.update(id, { title, content });
  if (!item) {
    res.fail('数据不存在', 404, 404);
    return;
  }
  res.ok(item, '更新成功');
};

/**
 * 删除演示数据
 * DELETE /api/demo/:id
 */
export const remove = (req, res) => {
  const id = Number(req.params.id);
  const success = demoService.remove(id);
  if (!success) {
    res.fail('数据不存在', 404, 404);
    return;
  }
  res.ok(null, '删除成功');
};
