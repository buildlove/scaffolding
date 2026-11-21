// ==================== 预加载脚本 ====================
// 运行在渲染进程与主进程之间的受限环境，
// 通过 contextBridge 安全暴露有限能力，避免渲染进程直接访问 Node API
const { contextBridge } = require('electron');

// 向渲染进程暴露 window.electronAPI（示例：版本信息）
contextBridge.exposeInMainWorld('electronAPI', {
  /**
   * 获取运行时版本信息（演示预加载脚本的安全桥接能力）
   * @returns {Object} node / chrome / electron 版本号
   */
  getVersions: () => ({
    node: process.versions.node,
    chrome: process.versions.chrome,
    electron: process.versions.electron,
  }),
});
