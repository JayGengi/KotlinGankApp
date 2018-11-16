package com.jaygengi.gank.mvp.presenter

import com.jaygengi.gank.base.BasePresenter
import com.jaygengi.gank.mvp.contract.HomeContract
import com.jaygengi.gank.mvp.model.GirlsModel
import com.jaygengi.gank.mvp.model.ToDayModel
import com.jaygengi.gank.net.exception.ExceptionHandle


 /**
   * @description: 最新一天的干货
   * @author JayGengi
   * @date  2018/11/16 0016 上午 11:29
   * @email jaygengiii@gmail.com
   */

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

     private val girlsModel: GirlsModel by lazy {

         GirlsModel()
     }
    private val todayModel: ToDayModel by lazy {

        ToDayModel()
    }
     override fun requestGirlInfo() {
         checkViewAttached()
         mRootView?.showLoading()
         val disposable = girlsModel.getGirlsInfo(1,1)
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