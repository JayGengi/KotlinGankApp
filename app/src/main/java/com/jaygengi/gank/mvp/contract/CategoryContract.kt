package com.jaygengi.gank.mvp.contract

import com.jaygengi.gank.base.IBaseView
import com.jaygengi.gank.base.IPresenter
import com.jaygengi.gank.mvp.model.bean.CategoryBean

/**
 * Created by xuhao on 2017/11/29.
 * desc: 分类 契约类
 */
interface CategoryContract {

    interface View : IBaseView {
        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)

        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{
        /**
         * 获取分类的信息
         */
        fun getCategoryData()
    }
}