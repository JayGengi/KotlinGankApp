package com.jaygengi.gank.mvp.model

import com.jaygengi.gank.mvp.model.bean.CategoryBean
import com.jaygengi.gank.net.RetrofitManager
import com.jaygengi.gank.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/29.
 * desc: 分类数据模型
 */
class CategoryModel {


    /**
     * 获取分类信息
     */
    fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory()
                .compose(SchedulerUtils.ioToMain())
    }
}