package com.jaygengi.gank.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.ui.adapter.HomePageAdapter
import com.jaygengi.gank.ui.fragment.home.*
import kotlinx.android.synthetic.main.fragment_gank_type.*

/**
   * @description: GankType
   * @author JayGengi
   * @date  2018/10/29 0029 下午 1:55
   * @email jaygengiii@gmail.com
   */
class GankTypeFragment : BaseFragment(){

    private var mTitle: String? = null
    private val fragments = ArrayList<Fragment>()

    private val titles = ArrayList<String>()
    companion object {
        fun getInstance(title: String): GankTypeFragment {
            val fragment = GankTypeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_gank_type

    override fun lazyLoad() {
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun initView() {
        title.text ="GankType"
        fragments.add(WelfareFragment())
        fragments.add(CategoryFragment.newInstance("Android"))
        fragments.add(CategoryFragment.newInstance("App"))
        fragments.add(CategoryFragment.newInstance("iOS"))
        fragments.add(CategoryFragment.newInstance("休息视频"))
        fragments.add(CategoryFragment.newInstance("前端"))
        fragments.add(CategoryFragment.newInstance("拓展资源"))
        fragments.add(CategoryFragment.newInstance("瞎推荐"))
        titles.add("福利")
        titles.add("Android")
        titles.add("App")
        titles.add("iOS")
        titles.add("休息视频")
        titles.add("前端")
        titles.add("拓展资源")
        titles.add("瞎推荐")
        viewpager.adapter = HomePageAdapter(childFragmentManager).apply {
            setData(fragments,titles)
        }
        viewpager.offscreenPageLimit = 4

        sliding_tabs.setViewPager(viewpager)
    }
}