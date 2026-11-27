package com.androidscaffoldingtemplate.data.model

/**
 * 演示节点实体
 *
 * 对应 Vue3 / React 模板首页里的「路由节点」演示数据，
 * 用于展示列表渲染、分页联动与详情页路由参数传递。
 *
 * @property id 节点唯一标识（详情页通过路由参数接收）
 * @property title 节点标题
 * @property description 节点描述
 */
data class NodeItem(
    val id: Int,
    val title: String,
    val description: String,
)

/**
 * 分页结果
 *
 * @property items 当前页的数据列表
 * @property page 当前页码（仓库层已做边界收敛）
 * @property totalPages 总页数
 * @property totalCount 总条数
 */
data class NodePage(
    val items: List<NodeItem>,
    val page: Int,
    val totalPages: Int,
    val totalCount: Int,
)
