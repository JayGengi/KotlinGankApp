package com.jaygengi.gank.mvp.contract

import com.jaygengi.gank.base.IBaseView
import com.jaygengi.gank.base.IPresenter
import com.jaygengi.gank.mvp.model.bean.CategoryEntity

/**
 * @description: 契约类
 * @author JayGengi
 * @date  2018/11/13 0013 上午 10:35
 * @email jaygengiii@gmail.com
 */

interface CategoryContract {

    interface View : IBaseView {

        /**
         * 获取最新一天的干货
         */
        fun getCategoryInfo(todayInfo: CategoryEntity)
        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)


    }

    interface Presenter : IPresenter<View> {

        /**
         * 获取最新一天的干货
         */
        fun requestCategoryInfo(key:String,limit: Int,page: Int)


    }


}