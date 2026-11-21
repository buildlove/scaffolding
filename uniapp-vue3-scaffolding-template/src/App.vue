<script>
// 应用级生命周期（全局只触发一次）
export default {
  onLaunch() {
    // 应用启动时触发（可做登录态检查、版本更新等初始化）
  },
  onShow() {
    // 应用从后台切到前台时触发
  },
  onHide() {
    // 应用从前台切到后台时触发
  },
};
</script>

<style lang="less">
/* 全局页面背景色（与 vue3 脚手架浅色主题保持一致） */
page {
  background-color: #f5f7fb;
}
</style>
