package com.jaygengi.gank.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.HomeContract
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.mvp.presenter.HomePresenter
import com.jaygengi.gank.net.exception.ErrorStatus
import com.jaygengi.gank.showToast
import com.jaygengi.gank.ui.adapter.ToDaySectionAdapter
import com.jaygengi.gank.utils.img.GlideImageLoader
import com.scwang.smartrefresh.header.MaterialHeader
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

    private var isRefresh = false

    private val mPresenter by lazy { HomePresenter() }

    private var mCategoryList = ArrayList<String>()

    private val mAdapter by lazy { activity?.let { ToDaySectionAdapter( mCategoryList) } }

    private var mTitle: String? = null


    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun lazyLoad() {
        //获取分类信息
        mPresenter.requestGirlInfo(7,1)
        mPresenter.requestToDayInfo()
    }
    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView

        recycler.apply {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener {
            isRefresh = true
            mPresenter.requestToDayInfo()
        }
        //设置下拉刷新主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.color_light_black, R.color.color_title_bg)

    }
    override fun showGirlInfo(dataInfo: GirlsEntity) {

        val bannerList = ArrayList<String>()
        val bannertitle = ArrayList<String>()
        for(item : GirlsEntity.ResultsBean in dataInfo.results!!){
            bannerList.add(item.url!!)
            bannertitle.add(item.who!!)
        }
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
        banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        banner.setImages(bannerList)
        //设置轮播要显示的标题和图片对应
        banner.setBannerTitles(bannertitle)
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default)
        //设置自动轮播，默认为true
        banner.isAutoPlay(true)
        //设置轮播时间
        banner.setDelayTime(3000)
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER)
        //banner设置方法全部调用完毕时最后调用
        banner.start()

        banner.setOnBannerListener { position1 ->

        }
    }

    override fun showToDayInfo(todayInfo: ToDayEntity) {
        mAdapter?.run {
            if (todayInfo.category != null && todayInfo.category!!.isNotEmpty()) {
                setToDayTypeInfo(todayInfo.results)
                setNewData(todayInfo.category)
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
