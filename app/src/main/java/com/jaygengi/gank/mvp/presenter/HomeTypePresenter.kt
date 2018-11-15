package com.jaygengi.gank.mvp.presenter

import com.jaygengi.gank.base.BasePresenter
import com.jaygengi.gank.mvp.contract.HomeTypeContract
import com.jaygengi.gank.mvp.model.ToDayModel
import com.jaygengi.gank.net.exception.ExceptionHandle


/**
 * Created by xuhao on 2017/11/8.
 * 首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */

class HomeTypePresenter : BasePresenter<HomeTypeContract.View>(), HomeTypeContract.Presenter {


    private val todayModel: ToDayModel by lazy {

        ToDayModel()
    }
    override fun requestToDayInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = todayModel.getToDayInfo()
                .subscribe({ todayList ->
                    mRootView?.apply {
                        dismissLoading()
                        if(!todayList.isError){
                            showToDayInfo(todayList)
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