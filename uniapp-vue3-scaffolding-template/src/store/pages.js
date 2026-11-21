import { defineStore } from 'pinia';

/**
 * 页面路由元信息树仓库（与 src/pages.json 的页面表一一对应）
 * uni-app 通过 pages.json 管理页面路由，没有 vue-router，
 * 这里用与 vue3 脚手架 meta 字段一致的结构（level / name / node）
 * 维护页面节点数据，供首页「路由节点（分页联动）」演示使用。
 */
export const usePagesStore = defineStore('pages', {
  state: () => {
    return {
      // 页面元信息树（level：层级，name：节点名称，node：是否叶子节点）
      pages: [
        {
          path: '/pages/index/index',
          meta: { level: 1, name: '嵌套路由一', node: true },
        },
        {
          path: '/pages/child/child',
          meta: { level: 1, name: '嵌套路由子路由', node: true },
        },
        {
          path: '/pages/request/request',
          meta: { level: 0, name: '模版路由二', node: true },
        },
      ],
    };
  },
  getters: {
    // 获取页面元信息树
    getPages: (state) => state.pages,
  },
  actions: {
    /**
     * 保存页面元信息树
     * @param {Array} pages 页面元信息树
     */
    savePages(pages) {
      this.pages = pages;
    },
  },
});
