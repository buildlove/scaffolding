/**
 * 演示模块接口测试
 * 使用 Node.js 内置测试器（node:test）+ 内置 fetch，零测试框架依赖；
 * 运行方式：pnpm test（执行目录：项目根目录）。
 */
import { test, before, after } from 'node:test';
import assert from 'node:assert/strict';
import { createApp } from '../src/app.js';

/** 测试服务器实例与基础地址 */
let server;
let base;

// 启动测试服务器：监听随机端口（0），避免与开发端口冲突
before(async () => {
  const app = createApp();
  server = app.listen(0);
  // 等待端口绑定完成
  await new Promise((resolve) => server.once('listening', resolve));
  base = `http://127.0.0.1:${server.address().port}`;
});

// 关闭测试服务器，释放端口
after(() => new Promise((resolve) => server.close(resolve)));

test('健康检查接口返回 code 0', async () => {
  const res = await fetch(`${base}/api/health`);
  const body = await res.json();
  assert.equal(res.status, 200);
  assert.equal(body.code, 0);
  assert.equal(body.data.status, 'up');
});

test('演示数据列表支持分页', async () => {
  const res = await fetch(`${base}/api/demo?page=1&pageSize=2`);
  const body = await res.json();
  assert.equal(body.code, 0);
  assert.equal(body.data.page, 1);
  assert.equal(body.data.pageSize, 2);
  assert.equal(body.data.records.length, 2);
});

test('新增 / 查询 / 更新 / 删除完整链路', async () => {
  // 1. 新增
  const createRes = await fetch(`${base}/api/demo`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: '测试标题', content: '测试内容' }),
  });
  const created = (await createRes.json()).data;

  // 2. 查询详情
  const detailRes = await fetch(`${base}/api/demo/${created.id}`);
  const detail = (await detailRes.json()).data;
  assert.equal(detail.title, '测试标题');

  // 3. 更新
  const updateRes = await fetch(`${base}/api/demo/${created.id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title: '更新后的标题' }),
  });
  const updated = (await updateRes.json()).data;
  assert.equal(updated.title, '更新后的标题');

  // 4. 删除
  const deleteRes = await fetch(`${base}/api/demo/${created.id}`, { method: 'DELETE' });
  assert.equal((await deleteRes.json()).code, 0);

  // 5. 删除后查询应为 404
  const missingRes = await fetch(`${base}/api/demo/${created.id}`);
  assert.equal(missingRes.status, 404);
});

test('title 缺失时返回 400 参数错误', async () => {
  const res = await fetch(`${base}/api/demo`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ content: '没有标题' }),
  });
  assert.equal(res.status, 400);
  assert.equal((await res.json()).code, 400);
});

test('未匹配路由返回统一 404 结构', async () => {
  const res = await fetch(`${base}/api/not-exist`);
  const body = await res.json();
  assert.equal(res.status, 404);
  assert.equal(body.code, 404);
  assert.equal(body.data, null);
});
