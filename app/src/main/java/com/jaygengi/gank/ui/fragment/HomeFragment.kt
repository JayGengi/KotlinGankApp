package com.jaygengi.gank.ui.fragment

import android.os.Bundle
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.HomeContract
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.presenter.HomePresenter
import com.jaygengi.gank.net.exception.ErrorStatus
import com.jaygengi.gank.showToast
import com.jaygengi.gank.utils.img.GlideImageLoader
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


    private val mPresenter by lazy { HomePresenter() }

    private var mTitle: String? = null


    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun lazyLoad() {
        //获取分类信息
        mPresenter.requestGirlInfo(10,1)
    }


    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView


    }
    override fun showGirlInfo(dataInfo: GirlsEntity) {

        val bannerList = ArrayList<String>()
        for(item : GirlsEntity.ResultsBean in dataInfo.results!!){
            bannerList.add(item.url!!)
        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        banner.setImageLoader(GlideImageLoader())
        //设置图片集合
        banner.setImages(bannerList)
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
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView?.showNoNetwork()
        } else {
            multipleStatusView?.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView?.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView?.showContent()
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
