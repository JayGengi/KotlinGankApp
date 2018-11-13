package com.jaygengi.gank.mvp.presenter

import com.jaygengi.gank.base.BasePresenter
import com.jaygengi.gank.mvp.contract.HomeContract


/**
 * Created by xuhao on 2017/11/8.
 * 首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {
    override fun requestHomeData(num: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadMoreData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}