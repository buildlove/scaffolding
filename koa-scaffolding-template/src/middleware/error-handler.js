/**
 * 全局错误处理中间件
 * 提供 404 兜底与统一异常捕获两个处理函数。
 * Koa 的洋葱模型中，把下游 next() 包在 try/catch 里即可捕获所有业务异常。
 */

/**
 * 全局异常捕获中间件（放在中间件链最外层）
 * @returns {import('koa').Middleware} Koa 中间件
 */
export const errorHandler = () => async (ctx, next) => {
  try {
    await next();
  } catch (err) {
    // 打印错误堆栈，便于排查（生产环境可接入日志服务）
    console.error('[koa-scaffold] 未捕获异常:', err);
    // 优先使用错误对象自带的 HTTP 状态码，否则按 500 处理
    const statusCode = err.statusCode || err.status || 500;
    ctx.fail(err.message || '服务器内部错误', statusCode, statusCode);
  }
};

/**
 * 404 兜底中间件（放在路由之后）：
 * 走到这里仍无响应内容，说明没有任何路由命中
 * @returns {import('koa').Middleware} Koa 中间件
 */
export const notFound = () => async (ctx, next) => {
  if (ctx.status === 404 && ctx.body === undefined) {
    ctx.fail(`接口不存在: ${ctx.method} ${ctx.originalUrl}`, 404, 404);
    return;
  }
  await next();
};
