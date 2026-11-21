/**
 * 环境变量配置模块
 * 统一读取 .env 与进程环境变量，供全项目引用；
 * 业务代码不允许直接读 process.env，一律从本模块取值。
 */
import dotenv from 'dotenv';

// 加载项目根目录 .env 文件（已存在的真实环境变量不会被覆盖）
dotenv.config();

/** 应用配置对象 */
const config = {
  /** 运行环境：development / production */
  env: process.env.NODE_ENV || 'development',
  /** 服务监听端口 */
  port: Number(process.env.PORT) || 7003,
  /** 服务监听地址 */
  host: process.env.HOST || '0.0.0.0',
  /** 跨域来源，英文逗号分隔字符串或 * */
  corsOrigin: process.env.CORS_ORIGIN || '*',
};

export default config;
