/**
 * 关于页（导航演示第二屏）
 * 展示模板说明与核心版本信息，验证 native-stack 导航可用。
 */
import React from 'react';
import { ScrollView, StyleSheet, Text, View } from 'react-native';

/** 核心版本信息表 */
const VERSIONS = [
  { name: 'react-native', version: '0.88.1', desc: '跨端框架' },
  { name: 'react', version: '19.3.0', desc: '核心库' },
  { name: 'typescript', version: '6.x', desc: '类型系统' },
  { name: '@react-navigation/native', version: '7.x', desc: '导航容器' },
  { name: '@react-navigation/native-stack', version: '7.x', desc: '原生导航栈' },
  { name: 'zustand', version: '5.x', desc: '状态管理' },
];

const AboutScreen = () => (
  <ScrollView style={styles.page} contentContainerStyle={styles.pageContent}>
    {/* 说明卡片 */}
    <View style={styles.card}>
      <Text style={styles.cardTitle}>📖 模板说明</Text>
      <Text style={styles.paragraph}>
        本模板基于 React Native 官方最新脚手架（0.88）生成，并按脚手架家族规范补充了
        导航、状态管理与首页演示，可直接作为新 App 项目的起步工程。
      </Text>
    </View>

    {/* 核心版本卡片 */}
    <View style={styles.card}>
      <Text style={styles.cardTitle}>🧩 核心版本</Text>
      {VERSIONS.map((item) => (
        <View key={item.name} style={styles.versionRow}>
          <Text style={styles.versionName}>{item.name}</Text>
          <Text style={styles.versionDesc}>{item.desc}</Text>
          <Text style={styles.versionTag}>{item.version}</Text>
        </View>
      ))}
    </View>
  </ScrollView>
);

/** 样式表 */
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
  card: {
    backgroundColor: '#ffffff',
    borderRadius: 12,
    padding: 20,
    shadowColor: '#1e293b',
    shadowOpacity: 0.06,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 4 },
    elevation: 2,
  },
  cardTitle: {
    fontSize: 16,
    fontWeight: '600',
    color: '#1f2937',
    marginBottom: 12,
  },
  paragraph: {
    fontSize: 13,
    color: '#303133',
    lineHeight: 22,
  },
  versionRow: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 8,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#e5eaf3',
    gap: 8,
  },
  versionName: {
    flex: 2,
    fontSize: 13,
    color: '#303133',
  },
  versionDesc: {
    flex: 1,
    fontSize: 12,
    color: '#909399',
  },
  versionTag: {
    fontSize: 12,
    color: '#4f46e5',
    fontWeight: '600',
  },
});

export default AboutScreen;
