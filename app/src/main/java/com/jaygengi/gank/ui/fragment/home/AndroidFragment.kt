package com.jaygengi.gank.ui.fragment.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.HomeTypeContract
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.mvp.presenter.HomeTypePresenter
import com.jaygengi.gank.net.exception.ErrorStatus
import com.jaygengi.gank.showToast
import com.jaygengi.gank.ui.adapter.ToDayAndroidAdapter
import kotlinx.android.synthetic.main.fragment_home_common_type.*

/**
   * @description: android
   * @author JayGengi
   * @date  2018/11/7 0007 下午 5:00
   * @email jaygengiii@gmail.com
   */

class AndroidFragment : BaseFragment(), HomeTypeContract.View {

    private var isRefresh = false

    private val mPresenter by lazy { HomeTypePresenter() }

    private var androidList = ArrayList<ToDayEntity.ResultsBean.AndroidBean>()

    private val mAdapter by lazy { activity?.let { ToDayAndroidAdapter( androidList) } }

    override fun getLayoutId(): Int = R.layout.fragment_home_common_type

    override fun lazyLoad() {
        mPresenter.requestToDayInfo()
    }
    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView

        recycler.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            adapter = mAdapter
        }

    }
    override fun showToDayInfo(todayInfo: ToDayEntity) {
        mAdapter?.run {
            if (todayInfo.results!!.Android != null && todayInfo.results!!.Android!!.isNotEmpty()) {
                setNewData(todayInfo.results!!.Android)
            } else {
                multipleStatusView?.showEmpty()
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()

        } else {
            multipleStatusView?.showError()
        }
    }

    /**
     * 显示 Loading （下拉刷新的时候不需要显示 Loading）
     */
    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    /**
     * 隐藏 Loading
     */
    override fun dismissLoading() {
        multipleStatusView?.showContent()
        if(mRefreshLayout!=null && mRefreshLayout.isLoading){
            mRefreshLayout.finishRefresh()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
