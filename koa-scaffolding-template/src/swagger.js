/**
 * Swagger（OpenAPI 3.0）文档生成配置
 * swagger-jsdoc 扫描路由文件中的 @openapi JSDoc 注释，自动聚合出接口描述对象；
 * 对外暴露两个地址：
 *   - /api/docs         在线可视化文档页面
 *   - /api/swagger.json 原始 OpenAPI JSON（可导入 Apifox / Postman）
 */
import swaggerJsdoc from 'swagger-jsdoc';

/** swagger-jsdoc 扫描与生成配置 */
const options = {
  definition: {
    openapi: '3.0.3',
    info: {
      title: 'Koa 脚手架模板 API',
      version: '1.0.0',
      description: 'Node.js + Koa 3 起步模板的演示接口文档（统一响应结构：{ code, message, data }）',
    },
    // 服务器地址：留空表示与文档同源
    servers: [{ url: '/', description: '当前服务' }],
    components: {
      schemas: {
        /** 统一响应结构 */
        Result: {
          type: 'object',
          properties: {
            code: { type: 'integer', description: '业务错误码，0 表示成功' },
            message: { type: 'string', description: '提示语' },
            data: { description: '响应数据，失败时为 null' },
          },
        },
        /** 演示数据模型 */
        Demo: {
          type: 'object',
          properties: {
            id: { type: 'integer', description: '主键' },
            title: { type: 'string', description: '标题' },
            content: { type: 'string', description: '内容' },
            createdAt: { type: 'string', description: '创建时间' },
          },
        },
      },
    },
  },
  // JSDoc 注释扫描范围（相对进程运行目录）
  apis: ['./src/router/*.js'],
};

/** 生成好的 OpenAPI 描述对象 */
export const swaggerSpec = swaggerJsdoc(options);
