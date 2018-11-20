package com.jaygengi.gank.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
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
import android.support.v7.widget.RecyclerView



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


    private val categoryList = ArrayList<String>()
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun lazyLoad() {
        loadData()
    }
    @SuppressLint("ResourceAsColor")
    override fun initView() {

        collapsing_topbar_layout.setCollapsedTitleTextColor(R.color.color_black)
        topbar.addRightImageButton(R.mipmap.ic_action_search_black,R.id.right).setOnClickListener {
            openSearchActivity(topbar.findViewById(R.id.right))
        }

        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView
        recyclerView.apply {
            setHasFixedSize(true)
//            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)

                val l = recyclerView!!.layoutManager as LinearLayoutManager

                val adapterNowPos = l.findFirstVisibleItemPosition()

                collapsing_topbar_layout.title = categoryList[adapterNowPos]

            }

        })

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
                categoryList.clear()
                categoryList.addAll(todayInfo.category!!)
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
