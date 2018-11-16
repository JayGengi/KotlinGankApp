package com.jaygengi.gank.ui.fragment.home

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.GirlsContract
import com.jaygengi.gank.mvp.model.bean.CategoryEntity
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.presenter.GirlsPresenter
import com.jaygengi.gank.net.exception.ErrorStatus
import com.jaygengi.gank.showToast
import com.jaygengi.gank.ui.adapter.GirlsAdapter
import com.jaygengi.gank.ui.adapter.ToDayAndroidAdapter
import com.jaygengi.gank.utils.img.GlideImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home_common_type.*

/**
   * @description: 福利
   * @author JayGengi
   * @date  2018/11/15 0015 下午 4:59
   * @email jaygengiii@gmail.com
   */

class WelfareFragment : BaseFragment(), GirlsContract.View {

    private val mPresenter by lazy { GirlsPresenter() }
    private var grilsList = ArrayList<GirlsEntity.ResultsBean>()
    private val mAdapter by lazy { activity?.let { GirlsAdapter( grilsList) } }
    override fun getLayoutId(): Int = R.layout.fragment_welfare


    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView
        mRefreshLayout.setOnRefreshListener {
            loadData()
        }
        recycler.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = GridLayoutManager(context,2)
            adapter = mAdapter
        }
//        mAdapter!!.setOnItemClickListener { adapter, _, position ->
//            val item :CategoryEntity.ResultsBean = adapter.getItem(position) as CategoryEntity.ResultsBean
//        }
    }

    override fun lazyLoad() {
        loadData()
    }
    private fun loadData(){
        mPresenter.requestGirlInfo(PAGE_CAPACITY,CURRENT_PAGE)
    }
    override fun showGirlInfo(dataInfo: GirlsEntity) {
        mAdapter?.run {
            if (dataInfo.results != null && dataInfo.results!!.isNotEmpty()) {
                setNewData(dataInfo.results)
            } else {
                multipleStatusView?.showEmpty()
            }
        }
    }

    override fun showError(msg: String, errorCode: Int) {
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
        mLayoutStatusView?.showLoading()
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
