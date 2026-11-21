/**
 * uni.request 统一请求封装（多端通用）
 * uni-app 各端（H5 / 微信小程序 / App）网络能力由 uni.request 提供，
 * 不能直接使用 axios（非 H5 端无浏览器 XHR 对象），
 * 这里仿照 vue3 脚手架的 axios 封装，实现拦截器 + 统一错误提示。
 */

// 接口请求域名（由 .env 环境文件注入，见 README 常用配置表）
const BASE_URL = import.meta.env.VITE_TINTMCE_BASE_URL;

// 请求超时时间（毫秒）
const TIMEOUT = 60 * 1000;

// 请求拦截器数组：入参为请求配置（url / method / data / header ...），返回修改后的配置
const requestInterceptors = [
  (config) => {
    // 统一附加请求头（如 token 鉴权），示例：config.header.Authorization = `Bearer ${token}`
    if (!config.header) config.header = {};
    return config;
  },
];

// 响应拦截器数组：入参为接口原始数据，返回处理后的数据（抛错则中断请求）
const responseInterceptors = [
  (res) => {
    // 业务错误统一提示（约定 code === 200 为成功）
    if (res && res.code !== undefined && String(res.code) !== '200') {
      uni.showToast({
        title: res.message || res.msg || '请检查数据返回格式',
        icon: 'none',
        duration: 3000,
      });
      return Promise.reject(res.message || 'error');
    }
    return res;
  },
];

/**
 * 发起请求（统一入口）
 * @param {Object} options 请求参数（url / method / data / header）
 * @returns {Promise} 接口返回数据（已经过响应拦截器处理）
 */
const request = (options) => {
  // 依次执行请求拦截器
  let config = { ...options };
  requestInterceptors.forEach((interceptor) => {
    config = interceptor(config) || config;
  });

  return new Promise((resolve, reject) => {
    uni.request({
      // 拼接完整请求地址
      url: config.url.startsWith('http') ? config.url : BASE_URL + config.url,
      method: config.method || 'GET',
      data: config.data || {},
      header: config.header || {},
      timeout: TIMEOUT,
      success: async (response) => {
        try {
          // 依次执行响应拦截器
          let res = response.data;
          for (const interceptor of responseInterceptors) {
            res = await interceptor(res);
          }
          resolve(res);
        } catch (error) {
          reject(error);
        }
      },
      fail: (error) => {
        // 网络异常统一提示
        uni.showToast({
          title: error?.errMsg || '网络异常，请稍后重试',
          icon: 'none',
          duration: 3000,
        });
        reject(error);
      },
    });
  });
};

// 快捷方法：GET / POST
request.get = (url, data, header) => request({ url, method: 'GET', data, header });
request.post = (url, data, header) => request({ url, method: 'POST', data, header });

export default request;
