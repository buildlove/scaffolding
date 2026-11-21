/**
 * 演示状态仓库（Zustand）
 * 对齐 vue3-scaffolding-template 的 Pinia 计数器演示：
 * state（状态）/ getters（派生值）/ actions（动作）标准写法。
 */
import { create } from 'zustand';

/** 仓库状态与动作的类型定义 */
interface DemoState {
  /** 计数器当前值（state） */
  count: number;
  /** 步长（state） */
  step: number;
  /** 计数器描述文本（派生值示例：由 count 计算而来） */
  doubleCount: () => number;
  /** 计数 +1（action） */
  increment: () => void;
  /** 计数 -1（action） */
  decrement: () => void;
  /** 重置为 0（action） */
  reset: () => void;
  /** 修改步长（action） */
  setStep: (step: number) => void;
}

/**
 * 创建演示仓库
 * Zustand 的 create 直接返回 hook，组件中 useDemoStore() 即可订阅状态
 */
export const useDemoStore = create<DemoState>((set, get) => ({
  // ===== state =====
  count: 0,
  step: 1,

  // ===== getters（派生值：每次调用实时计算） =====
  doubleCount: () => get().count * 2,

  // ===== actions =====
  increment: () => set((state) => ({ count: state.count + state.step })),
  decrement: () => set((state) => ({ count: state.count - state.step })),
  reset: () => set({ count: 0 }),
  setStep: (step: number) => set({ step }),
}));
