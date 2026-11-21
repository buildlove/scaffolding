/**
 * 请求日志中间件
 * 在控制台输出每个请求的「方法 / 路径 / 状态码 / 耗时」，便于本地调试；
 * 生产环境可替换为 winston / pino 等日志库。
 */

/**
 * 创建请求日志中间件
 * @returns {import('express').RequestHandler} Express 中间件
 */
const requestLogger = () => (req, res, next) => {
  // 记录请求开始时间（高精度时间戳，单位毫秒）
  const start = process.hrtime.bigint();

  // 响应结束时输出日志（监听 finish 事件，覆盖正常与异常结束）
  res.on('finish', () => {
    // 计算请求耗时（纳秒转毫秒，保留 1 位小数）
    const costMs = Number(process.hrtime.bigint() - start) / 1e6;
    console.log(
      `[${new Date().toLocaleString()}] ${req.method} ${req.originalUrl} ${res.statusCode} - ${costMs.toFixed(1)}ms`,
    );
  });

  next();
};

export default requestLogger;
