/**
 * 统一响应格式中间件
 * 给 ctx 挂载 ok / fail 两个响应方法，全项目统一返回结构：
 * 成功：{ code: 0, message: 'success', data: ... }
 * 失败：{ code: 非0, message: '错误描述', data: null }
 */

/**
 * 创建统一响应中间件
 * @returns {import('koa').Middleware} Koa 中间件
 */
const response = () => async (ctx, next) => {
  /**
   * 成功响应
   * @param {*} data 响应数据
   * @param {string} message 提示语
   */
  ctx.ok = (data = null, message = 'success') => {
    ctx.status = 200;
    ctx.body = { code: 0, message, data };
  };

  /**
   * 失败响应
   * @param {string} message 错误提示语
   * @param {number} code 业务错误码（默认 1）
   * @param {number} statusCode HTTP 状态码（默认 200，语义化错误可传 4xx/5xx）
   * @param {*} data 附带数据（默认 null）
   */
  ctx.fail = (message = 'error', code = 1, statusCode = 200, data = null) => {
    ctx.status = statusCode;
    ctx.body = { code, message, data };
  };

  await next();
};

export default response;
