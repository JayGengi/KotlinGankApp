package com.jaygengi.gank.ui.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.HomeContract
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.mvp.presenter.HomePresenter
import com.jaygengi.gank.net.exception.ErrorStatus
import com.jaygengi.gank.ui.activity.SearchActivity
import com.jaygengi.gank.ui.adapter.ToDaySectionAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
   * @description: 首页
   * @author JayGengi
   * @date  2018/11/7 0007 下午 5:00
   * @email jaygengiii@gmail.com
   */

class HomeFragment : BaseFragment(), HomeContract.View {


    private val mPresenter by lazy { HomePresenter() }

    private var mCategoryList = ArrayList<String>()

    private val mAdapter by lazy { activity?.let { ToDaySectionAdapter(context!!, mCategoryList) } }

    private var mTitle: String? = null

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun lazyLoad() {
        loadData()
    }
    override fun initView() {
        topbar.addRightImageButton(R.mipmap.ic_action_search_black,R.id.right).setOnClickListener {
            openSearchActivity(topbar.findViewById(R.id.right))
        }
//        collapsing_topbar_layout.title = "JayGengi"
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView
//        mRefreshLayout.setOnRefreshListener {
//            loadData()
//        }
        recyclerView.apply {
            setHasFixedSize(true)
//            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }
    }
    private fun loadData(){
        mPresenter.requestGirlInfo()
        //获取分类信息
        mPresenter.requestToDayInfo()
    }
    override fun showGirlInfo(dataInfo: GirlsEntity) {
        Glide.with(this).load(dataInfo.results!![0].url).into(head_img)
    }
    override fun showToDayInfo(todayInfo: ToDayEntity) {
        mAdapter?.run {
            if (todayInfo.category != null && todayInfo.results!= null) {
                setNewData(todayInfo.category)
                setToDayTypeInfo(todayInfo.results)
            } else {
                multipleStatusView?.showEmpty()
            }
        }
    }

    private fun openSearchActivity(view :View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val options = activity?.let { ActivityOptionsCompat.makeSceneTransitionAnimation(it, view, "JayGengi") }
            startActivity(Intent(activity, SearchActivity::class.java), options?.toBundle())
        } else {
            startActivity(Intent(activity, SearchActivity::class.java))
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
//        if(mRefreshLayout!=null && mRefreshLayout.isLoading){
//            mRefreshLayout.finishRefresh()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

}
