package com.jaygengi.gank.mvp.contract

import com.jaygengi.gank.base.IBaseView
import com.jaygengi.gank.base.IPresenter

/**
 * @description: 契约类
 * @author JayGengi
 * @date  2018/11/13 0013 上午 10:35
 * @email jaygengiii@gmail.com
 */

interface HomeContract {

    interface View : IBaseView {

        /**
         * 设置第一次请求的数据
         */
        fun<T> setHomeData(homeBean: T)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(itemList:ArrayList<Any>)

        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)


    }

    interface Presenter : IPresenter<View> {

        /**
         * 获取首页精选数据
         */
        fun requestHomeData(num: Int)

        /**
         * 加载更多数据
         */
        fun loadMoreData()


    }


}