package com.androidscaffoldingtemplate.network

import retrofit2.http.GET

/**
 * 接口定义（Retrofit）
 *
 * 说明：
 *   - 使用 suspend 函数声明异步请求，Retrofit 会自动切到后台线程，无需手写 Call
 *   - 新增业务接口时，按同样格式在此追加方法即可
 */
interface ApiService {

    /**
     * 获取用户列表（示例接口）
     *
     * 请求地址：{API_BASE_URL}/users
     * 默认示例：https://jsonplaceholder.typicode.com/users
     */
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}
