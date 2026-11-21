/**
 * RN 脚手架应用根组件
 * 职责：SafeArea 容器 + 原生导航栈（首页演示 / 关于）；
 * 页面组件放在 src/screens/ 下，业务逻辑与状态放在 src/ 对应目录。
 */
import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { StatusBar, useColorScheme } from 'react-native';
import { SafeAreaProvider } from 'react-native-safe-area-context';
import HomeScreen from './src/screens/HomeScreen';
import AboutScreen from './src/screens/AboutScreen';

/** 导航栈参数表（本模板两屏均无入参，保留空对象便于扩展） */
export type RootStackParamList = {
  Home: undefined;
  About: undefined;
};

// 创建原生导航栈（底层使用系统原生导航组件，性能最优）
const Stack = createNativeStackNavigator<RootStackParamList>();

const App = () => {
  // 跟随系统深浅色设置状态栏文字颜色
  const isDarkMode = useColorScheme() === 'dark';

  return (
    <SafeAreaProvider>
      <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
      <NavigationContainer>
        <Stack.Navigator
          initialRouteName="Home"
          screenOptions={{
            // 导航栏配色对齐家族主题色
            headerStyle: { backgroundColor: '#4f46e5' },
            headerTintColor: '#ffffff',
            headerTitleStyle: { fontWeight: '600' },
          }}>
          {/* 首页演示屏 */}
          <Stack.Screen name="Home" component={HomeScreen} options={{ title: '脚手架RN模板' }} />
          {/* 关于页（导航演示） */}
          <Stack.Screen name="About" component={AboutScreen} options={{ title: '关于' }} />
        </Stack.Navigator>
      </NavigationContainer>
    </SafeAreaProvider>
  );
};

export default App;
