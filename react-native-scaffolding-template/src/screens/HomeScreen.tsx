/**
 * 首页演示屏（对齐 vue3-scaffolding-template 首页结构）
 * 结构：欢迎横幅 → 技术栈卡片 → 功能演示卡片 → Zustand 计数器演示。
 * 纯展示组件，不依赖导航，方便单测渲染。
 */
import React from 'react';
import { ScrollView, StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import { useDemoStore } from '../store/demo';

/** 技术栈标签列表 */
const TECH_TAGS = [
  'React Native 0.88',
  'React 19',
  'TypeScript',
  'React Navigation 7',
  'Zustand 5',
];

/** 功能演示清单 */
const FEATURES = [
  '嵌套导航：原生导航栈（native-stack）',
  'Zustand 状态管理：计数器 state / 派生值 / actions',
  'SafeArea 安全区适配（刘海屏 / 底部指示条）',
  '家族同款视觉：渐变感横幅 + 卡片布局',
];

const HomeScreen = () => {
  // 订阅 Zustand 演示仓库（状态变化自动重渲染）
  const { count, step, doubleCount, increment, decrement, reset, setStep } = useDemoStore();

  return (
    <ScrollView style={styles.page} contentContainerStyle={styles.pageContent}>
      {/* ===== 欢迎横幅（家族同款配色 #4f46e5 → #3b82f6） ===== */}
      <View style={styles.hero}>
        <Text style={styles.heroTitle}>👋 React Native 脚手架模板</Text>
        <Text style={styles.heroDesc}>
          开箱即用的 React Native 起步工程，覆盖导航、状态管理、
          安全区适配等常用能力，可直接作为新 App 项目的起步模板。
        </Text>
      </View>

      {/* ===== 技术栈卡片 ===== */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>🧩 技术栈</Text>
        <View style={styles.tagWrap}>
          {TECH_TAGS.map((tag) => (
            <View key={tag} style={styles.tag}>
              <Text style={styles.tagText}>{tag}</Text>
            </View>
          ))}
        </View>
      </View>

      {/* ===== 功能演示卡片 ===== */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>✨ 功能演示</Text>
        {FEATURES.map((feature) => (
          <View key={feature} style={styles.featureRow}>
            <Text style={styles.featureCheck}>✓</Text>
            <Text style={styles.featureText}>{feature}</Text>
          </View>
        ))}
      </View>

      {/* ===== Zustand 计数器演示（对齐 Pinia 计数器范例） ===== */}
      <View style={styles.card}>
        <Text style={styles.cardTitle}>🔢 计数器演示（Zustand）</Text>
        <Text style={styles.counterValue}>{count}</Text>
        <Text style={styles.counterTip}>
          当前步长 {step}，派生值 doubleCount = {doubleCount()}
        </Text>
        <View style={styles.buttonRow}>
          <TouchableOpacity style={styles.button} onPress={decrement}>
            <Text style={styles.buttonText}>-1</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.button} onPress={increment}>
            <Text style={styles.buttonText}>+1</Text>
          </TouchableOpacity>
          <TouchableOpacity style={[styles.button, styles.buttonPlain]} onPress={reset}>
            <Text style={styles.buttonText}>重置</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={[styles.button, styles.buttonPlain]}
            onPress={() => setStep(step === 1 ? 5 : 1)}>
            <Text style={styles.buttonText}>步长 {step === 1 ? '→5' : '→1'}</Text>
          </TouchableOpacity>
        </View>
      </View>
    </ScrollView>
  );
};

/** 样式表（家族浅色主题） */
const styles = StyleSheet.create({
  page: {
    flex: 1,
    backgroundColor: '#f5f7fb',
  },
  pageContent: {
    padding: 16,
    gap: 16,
    paddingBottom: 40,
  },
  /* 欢迎横幅：主题色横幅（原生无渐变，用主题色 + 叠色模拟家族渐变观感） */
  hero: {
    backgroundColor: '#4f46e5',
    borderRadius: 16,
    padding: 24,
  },
  heroTitle: {
    color: '#ffffff',
    fontSize: 22,
    fontWeight: '700',
    marginBottom: 10,
  },
  heroDesc: {
    color: 'rgba(255, 255, 255, 0.85)',
    fontSize: 14,
    lineHeight: 22,
  },
  /* 通用卡片 */
  card: {
    backgroundColor: '#ffffff',
    borderRadius: 12,
    padding: 20,
    // 卡片阴影（iOS）
    shadowColor: '#1e293b',
    shadowOpacity: 0.06,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 4 },
    // 卡片阴影（Android）
    elevation: 2,
  },
  cardTitle: {
    fontSize: 16,
    fontWeight: '600',
    color: '#1f2937',
    marginBottom: 12,
  },
  /* 技术栈标签 */
  tagWrap: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: 8,
  },
  tag: {
    backgroundColor: '#f0f2f5',
    borderRadius: 4,
    paddingHorizontal: 10,
    paddingVertical: 4,
  },
  tagText: {
    fontSize: 12,
    color: '#606266',
  },
  /* 功能清单行 */
  featureRow: {
    flexDirection: 'row',
    alignItems: 'flex-start',
    gap: 8,
    marginBottom: 8,
  },
  featureCheck: {
    color: '#4f46e5',
    fontWeight: '700',
    fontSize: 13,
  },
  featureText: {
    flex: 1,
    fontSize: 13,
    color: '#303133',
    lineHeight: 20,
  },
  /* 计数器演示 */
  counterValue: {
    fontSize: 40,
    fontWeight: '700',
    color: '#4f46e5',
    textAlign: 'center',
  },
  counterTip: {
    fontSize: 12,
    color: '#909399',
    textAlign: 'center',
    marginBottom: 12,
  },
  buttonRow: {
    flexDirection: 'row',
    justifyContent: 'center',
    gap: 8,
    flexWrap: 'wrap',
  },
  button: {
    backgroundColor: '#4f46e5',
    borderRadius: 8,
    paddingHorizontal: 18,
    paddingVertical: 10,
  },
  /* 次级按钮（浅底） */
  buttonPlain: {
    backgroundColor: '#eef2ff',
  },
  buttonText: {
    color: '#4f46e5',
    fontSize: 14,
    fontWeight: '600',
  },
});

export default HomeScreen;
