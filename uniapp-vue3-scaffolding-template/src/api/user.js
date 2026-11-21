import request from '@/utils/request';

/**
 * 示例接口：获取安全用户信息
 * @param {Object} data 请求参数
 * @returns {Promise} 接口返回数据
 */
export function getSecurityUser(data) {
  return request({
    url: '/role/getSecurityUser',
    method: 'POST',
    data,
  });
}
