// ==================== Electron 主进程入口 ====================
// 负责应用生命周期管理与窗口创建（Node 环境，无 DOM）
const { app, BrowserWindow } = require('electron');
const { join } = require('path');

/**
 * 创建应用主窗口
 * 开发模式加载 electron-vite dev 服务地址，生产模式加载构建产物
 */
function createWindow() {
  const win = new BrowserWindow({
    width: 1280,
    height: 840,
    minWidth: 960,
    minHeight: 640,
    show: false,
    // 隐藏默认菜单栏（桌面应用更简洁，可按需移除）
    autoHideMenuBar: true,
    webPreferences: {
      // 预加载脚本：通过 contextBridge 向渲染进程安全暴露有限 API
      preload: join(__dirname, '../preload/index.js'),
      // 安全默认：开启上下文隔离、禁用 Node 集成
      contextIsolation: true,
      nodeIntegration: false,
    },
  });

  // 窗口就绪后再显示，避免白屏闪烁
  win.once('ready-to-show', () => win.show());

  // 开发模式：electron-vite dev 注入的渲染进程地址
  if (process.env.ELECTRON_RENDERER_URL) {
    win.loadURL(process.env.ELECTRON_RENDERER_URL);
  } else {
    // 生产模式：加载渲染进程构建产物
    win.loadFile(join(__dirname, '../renderer/index.html'));
  }
}

// 应用就绪后创建窗口
app.whenReady().then(() => {
  createWindow();

  // macOS 惯例：点击 Dock 图标时重新创建窗口
  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) createWindow();
  });
});

// Windows / Linux 惯例：所有窗口关闭时退出应用（macOS 保活）
app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') app.quit();
});
