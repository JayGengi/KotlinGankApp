package com.jaygengi.gank.mvp.presenter

import com.jaygengi.gank.base.BasePresenter
import com.jaygengi.gank.mvp.contract.CategoryContract
import com.jaygengi.gank.mvp.model.CategoryModel
import com.jaygengi.gank.mvp.model.ToDayModel
import com.jaygengi.gank.net.exception.ExceptionHandle


/**
 * Created by xuhao on 2017/11/8.
 * 首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */

class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {


    private val categoryModel: CategoryModel by lazy {

        CategoryModel()
    }

    override fun requestCategoryInfo(key:String,limit: Int,page: Int) {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = categoryModel.getCategoryInfo(key,limit,page)
                .subscribe({ categoryList ->
                    mRootView?.apply {
                        dismissLoading()
                        if(!categoryList.isError){
                            getCategoryInfo(categoryList)
                        }else{
                            showError(ExceptionHandle.errorMsg, ExceptionHandle.errorCode)
                        }
                    }
                }, { t ->
                    mRootView?.apply {
                        //处理异常
                        showError(ExceptionHandle.handleException(t), ExceptionHandle.errorCode)
                    }

                })

        addSubscription(disposable)
    }


}