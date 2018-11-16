package com.jaygengi.gank.ui.fragment

import android.os.Bundle
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.HomeContract
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.mvp.presenter.HomePresenter
import com.jaygengi.gank.showToast
import com.jaygengi.gank.ui.adapter.ToDaySectionAdapter
import com.jaygengi.gank.utils.img.GlideImageLoader
import com.jaygengi.gank.widget.SlidingTabLayout
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

/**
   * @description: 首页
   * @author JayGengi
   * @date  2018/11/7 0007 下午 5:00
   * @email jaygengiii@gmail.com
   */

class HomeFragment : BaseFragment(), HomeContract.View {
    override fun showToDayInfo(todayInfo: ToDayEntity) {
    }

    private var isRefresh = false

    private val mPresenter by lazy { HomePresenter() }

    private var mCategoryList = ArrayList<String>()

    private val mAdapter by lazy { activity?.let { ToDaySectionAdapter( mCategoryList) } }

    private var mTitle: String? = null



    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun lazyLoad() {
        //获取分类信息
        mPresenter.requestToDayInfo()
    }
    override fun initView() {
        mPresenter.attachView(this)

        sliding_tabs.setCustomTabColorizer(object : SlidingTabLayout.TabColorizer {
            override fun getIndicatorColor(position: Int): Int {
                return R.color.color_black
            }

            override fun getDividerColor(position: Int): Int {
                return R.color.color_white
            }
        })
//        recycler.apply {
//            setHasFixedSize(true)
//            isNestedScrollingEnabled = false
//            layoutManager = LinearLayoutManager(context)
//            adapter = mAdapter
//        }

    }
    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
    }

    /**
     * 显示 Loading （下拉刷新的时候不需要显示 Loading）
     */
    override fun showLoading() {
    }
    /**
     * 隐藏 Loading
     */
    override fun dismissLoading() {
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
