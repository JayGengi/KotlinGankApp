package com.jaygengi.gank.mvp.presenter

import com.jaygengi.gank.base.BasePresenter
import com.jaygengi.gank.mvp.contract.GirlsContract
import com.jaygengi.gank.mvp.model.GirlsModel
import com.jaygengi.gank.net.exception.ExceptionHandle


/**
 * Created by xuhao on 2017/11/8.
 * 首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */

class GirlsPresenter : BasePresenter<GirlsContract.View>(), GirlsContract.Presenter {



    private val girlsModel: GirlsModel by lazy {

        GirlsModel()
    }

    override fun requestGirlInfo(limit: Int,page: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = girlsModel.getGirlsInfo(limit,page)
                .subscribe({ girlsList ->
                    mRootView?.apply {
                        dismissLoading()
                        if(!girlsList.isError){
                            showGirlInfo(girlsList)
                        }else{
                            showError(ExceptionHandle.errorMsg,ExceptionHandle.errorCode)
                        }
                    }
                }, { t ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }

                })

        addSubscription(disposable)
    }


}