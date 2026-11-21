/**
 * 首页演示屏渲染测试
 * 验证 HomeScreen（含 Zustand 仓库接线）可正常渲染与状态流转；
 * 运行方式：pnpm test（执行目录：项目根目录）。
 */
import React from 'react';
import ReactTestRenderer from 'react-test-renderer';
import { Text } from 'react-native';
import HomeScreen from '../src/screens/HomeScreen';
import { useDemoStore } from '../src/store/demo';

test('首页演示屏可正常渲染', async () => {
  let tree: ReactTestRenderer.ReactTestRenderer | undefined;
  // act 包裹确保异步渲染与状态更新完整执行
  await ReactTestRenderer.act(() => {
    tree = ReactTestRenderer.create(<HomeScreen />);
  });
  // 有文本节点渲染出来即视为渲染成功（横幅 / 卡片 / 计数器）
  expect(tree!.root.findAllByType(Text).length).toBeGreaterThan(0);
  // 测试结束卸载组件，避免后续状态更新触发 act 警告
  await ReactTestRenderer.act(() => {
    tree!.unmount();
  });
});

test('计数器仓库状态流转正确', async () => {
  // 纯仓库逻辑测试：直接驱动 actions 并断言 state / 派生值
  useDemoStore.getState().reset();
  useDemoStore.getState().increment();
  useDemoStore.getState().increment();
  // +1 两次应为 2
  expect(useDemoStore.getState().count).toBe(2);
  // 派生值 doubleCount 应为 count 的两倍
  expect(useDemoStore.getState().doubleCount()).toBe(4);
  // 步长切换后递增应按步长累加
  useDemoStore.getState().setStep(5);
  useDemoStore.getState().increment();
  expect(useDemoStore.getState().count).toBe(7);
});
