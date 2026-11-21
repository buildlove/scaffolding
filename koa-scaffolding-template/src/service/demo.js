/**
 * 演示模块服务层（内存数据实现）
 * 职责：封装业务数据的增删改查逻辑，不关心 HTTP 细节；
 * 当前使用内存数组模拟数据表，真实项目可替换为 mysql2 / prisma 等数据库访问。
 */

/** 内存演示数据表（进程重启后重置） */
let list = [
  { id: 1, title: '欢迎使用 Koa 脚手架', content: '这是一条内存演示数据', createdAt: '2026-01-01 10:00:00' },
  { id: 2, title: '分层架构', content: 'router / controller / service 各司其职', createdAt: '2026-01-01 10:00:01' },
  { id: 3, title: 'Swagger 文档', content: '访问 /api/docs 查看在线接口文档', createdAt: '2026-01-01 10:00:02' },
];

/** 自增主键游标（取当前最大 id + 1） */
let nextId = list.length + 1;

/**
 * 分页查询演示数据
 * @param {Object} [query] 查询参数
 * @param {number} [query.page=1] 页码（从 1 开始）
 * @param {number} [query.pageSize=10] 每页条数
 * @returns {{ total: number, page: number, pageSize: number, records: Array }}
 */
export const findAll = ({ page = 1, pageSize = 10 } = {}) => {
  // 计算分页截取的起止下标
  const start = (page - 1) * pageSize;
  const records = list.slice(start, start + pageSize);
  return { total: list.length, page, pageSize, records };
};

/**
 * 按 id 查询单条数据
 * @param {number} id 数据主键
 * @returns {Object|undefined} 命中的数据，不存在返回 undefined
 */
export const findById = (id) => list.find((item) => item.id === id);

/**
 * 新增一条演示数据
 * @param {Object} payload 新增内容
 * @param {string} payload.title 标题（必填）
 * @param {string} [payload.content] 内容
 * @returns {Object} 新增后的完整数据
 */
export const create = ({ title, content = '' }) => {
  const item = {
    id: nextId,
    title,
    content,
    // 生成本地时间字符串作为创建时间
    createdAt: new Date().toLocaleString(),
  };
  nextId += 1;
  list.push(item);
  return item;
};

/**
 * 更新一条演示数据
 * @param {number} id 数据主键
 * @param {Object} payload 更新内容（title / content 可选）
 * @returns {Object|null} 更新后的完整数据，不存在返回 null
 */
export const update = (id, { title, content } = {}) => {
  const item = findById(id);
  if (!item) return null;
  // 仅覆盖传入的字段，未传字段保持原值
  if (title !== undefined) item.title = title;
  if (content !== undefined) item.content = content;
  return item;
};

/**
 * 删除一条演示数据
 * @param {number} id 数据主键
 * @returns {boolean} 是否删除成功
 */
export const remove = (id) => {
  const before = list.length;
  list = list.filter((item) => item.id !== id);
  return list.length < before;
};
