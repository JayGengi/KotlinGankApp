package com.jaygengi.gank.mvp.model.bean

import java.io.Serializable

/**
 * Created by xuhao on 2017/11/29.
 * desc:ic_classification_selected Bean
 */
data class CategoryBean(val id: Long, val name: String, val description: String, val bgPicture: String, val bgColor: String, val headerImage: String) : Serializable
