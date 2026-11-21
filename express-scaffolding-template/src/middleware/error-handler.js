/**
 * 全局错误处理中间件
 * 提供 404 兜底与统一异常响应两个处理函数。
 * 注意：Express 5 会自动把 async 控制器抛出的异常转发给错误中间件，
 * 无需再手动 try/catch 包裹异步路由。
 */

/**
 * 404 兜底处理：走到这里说明没有任何路由命中
 * @type {import('express').RequestHandler}
 */
export const notFound = (req, res) => {
  res.fail(`接口不存在: ${req.method} ${req.originalUrl}`, 404, 404);
};

/**
 * 全局异常处理（必须 4 个参数，Express 以此识别为错误中间件）
 * @param {Error} err 错误对象
 * @param {import('express').Request} req 请求对象
 * @param {import('express').Response} res 响应对象
 * @param {import('express').NextFunction} next 放行函数
 * @type {import('express').ErrorRequestHandler}
 */
export const errorHandler = (err, req, res, next) => {
  // 打印错误堆栈，便于排查（生产环境可接入日志服务）
  console.error('[express-scaffold] 未捕获异常:', err);

  // 优先使用错误对象自带的 HTTP 状态码，否则按 500 处理
  const statusCode = err.statusCode || err.status || 500;
  res.fail(err.message || '服务器内部错误', statusCode, statusCode);
};
