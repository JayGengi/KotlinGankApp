package com.jaygengi.gank.mvp.model.bean

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * @description: 分组布局 实体类必须继承SectionEntity
 * @author JayGengi
 * @date  2018/11/14 0014 下午 4:53
 * @email jaygengiii@gmail.com
 */
class MySectionEntity : SectionEntity<ToDayEntity> {
    private val isMore: Boolean = false

    constructor(isHeader: Boolean, header: String) : super(isHeader, header) {}

    constructor(t: ToDayEntity) : super(t) {}
}
