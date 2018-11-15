package com.jaygengi.gank.ui.fragment.home

import android.os.Bundle
import android.support.v4.app.Fragment
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.mvp.contract.HomeContract
import com.jaygengi.gank.mvp.model.bean.GirlsEntity
import com.jaygengi.gank.mvp.model.bean.ToDayEntity
import com.jaygengi.gank.mvp.presenter.HomePresenter
import com.jaygengi.gank.net.exception.ErrorStatus
import com.jaygengi.gank.showToast
import com.jaygengi.gank.ui.adapter.HomePageAdapter
import com.jaygengi.gank.ui.adapter.ToDaySectionAdapter
import com.jaygengi.gank.utils.img.GlideImageLoader
import com.jaygengi.gank.widget.SlidingTabLayout
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

/**
   * @description: 拓展
   * @author JayGengi
   * @date  2018/11/7 0007 下午 5:00
   * @email jaygengiii@gmail.com
   */

class ExpandFragment : BaseFragment() {
    override fun initView() {
    }

    override fun lazyLoad() {
    }


    override fun getLayoutId(): Int = R.layout.fragment_app


}
