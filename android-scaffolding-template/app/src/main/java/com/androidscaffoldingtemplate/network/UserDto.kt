package com.androidscaffoldingtemplate.network

import kotlinx.serialization.Serializable

/**
 * 用户数据模型（网络层示例）
 *
 * 对应 jsonplaceholder 的 /users 接口返回结构，
 * 使用 kotlinx.serialization 做 JSON 解析（无需反射，性能更好）。
 *
 * @property id 用户 ID
 * @property name 姓名
 * @property username 用户名
 * @property email 邮箱
 */
@Serializable
data class UserDto(
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
    val email: String = "",
)
