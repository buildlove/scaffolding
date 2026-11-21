/**
 * 请求日志中间件
 * 在控制台输出每个请求的「方法 / 路径 / 状态码 / 耗时」，便于本地调试；
 * 生产环境可替换为 winston / pino 等日志库。
 */

/**
 * 创建请求日志中间件
 * @returns {import('koa').Middleware} Koa 中间件
 */
const requestLogger = () => async (ctx, next) => {
  // 记录请求开始时间
  const start = Date.now();
  // 先放行下游处理，拿到最终响应状态
  await next();
  const costMs = Date.now() - start;
  console.log(
    `[${new Date().toLocaleString()}] ${ctx.method} ${ctx.originalUrl} ${ctx.status} - ${costMs}ms`,
  );
};

export default requestLogger;
