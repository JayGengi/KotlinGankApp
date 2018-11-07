package com.hazz.mykotlinmvp.mvp.presenter

import com.hazz.mykotlinmvp.base.BasePresenter
import com.hazz.mykotlinmvp.mvp.contract.CategoryContract
import com.hazz.mykotlinmvp.mvp.model.CategoryModel
import com.hazz.mykotlinmvp.net.exception.ExceptionHandle

/**
 * Created by xuhao on 2017/11/29.
 * desc:分类的 Presenter
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val categoryModel: CategoryModel by lazy {
        CategoryModel()
    }

    /**
     * 获取分类
     */
    override fun getCategoryData() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = categoryModel.getCategoryData()
                .subscribe({ categoryList ->
                    mRootView?.apply {
                        dismissLoading()
                        showCategory(categoryList)
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