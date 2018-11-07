package com.hazz.mykotlinmvp.api

import com.hazz.mykotlinmvp.mvp.model.bean.CategoryBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by xuhao on 2017/11/16.
 * Api 接口
 */

interface ApiService{
    /**
     * 获取分类
     */
    @GET("v4/categories")
    fun getCategory(): Observable<ArrayList<CategoryBean>>

}