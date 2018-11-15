package com.jaygengi.gank.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.HomeContract
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.mvp.presenter.HomePresenter
import com.jaygengi.gank.showToast
import com.jaygengi.gank.ui.adapter.HomePageAdapter
import com.jaygengi.gank.ui.adapter.ToDaySectionAdapter
import com.jaygengi.gank.ui.fragment.home.*
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

    private var isRefresh = false

    private val mPresenter by lazy { HomePresenter() }

    private var mCategoryList = ArrayList<String>()

    private val mAdapter by lazy { activity?.let { ToDaySectionAdapter( mCategoryList) } }

    private var mTitle: String? = null

    private val fragments = ArrayList<Fragment>()

    private val titles = ArrayList<String>()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun lazyLoad() {
        //获取分类信息
        mPresenter.requestGirlInfo(7,1)
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
        fragments.add(WelfareFragment())
        fragments.add(AndroidFragment())
        fragments.add(AppFragment())
        fragments.add(IOSFragment())
        fragments.add(RestFragment())
        fragments.add(WebFragment())
        fragments.add(ExpandFragment())
        fragments.add(RecommendFragment())

        titles.add("福利")
        titles.add("Android")
        titles.add("App")
        titles.add("IOS")
        titles.add("休息视频")
        titles.add("前端")
        titles.add("拓展资源")
        titles.add("推荐")
        viewpager.adapter = HomePageAdapter(childFragmentManager).apply {
            setData(fragments,titles)
        }
        viewpager.offscreenPageLimit = 4

        sliding_tabs.setViewPager(viewpager)
//        recycler.apply {
//            setHasFixedSize(true)
//            isNestedScrollingEnabled = false
//            layoutManager = LinearLayoutManager(context)
//            adapter = mAdapter
//        }

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
