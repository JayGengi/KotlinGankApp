package com.jaygengi.gank.ui.fragment

import android.os.Bundle
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment

/**
 * Created by xuhao on 2017/11/9.
 * 我的
 */
class MineFragment : BaseFragment() {

    private var mTitle: String? = null
    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
    override fun getLayoutId(): Int= R.layout.fragment_mine

    override fun initView() {

    }

    override fun lazyLoad() {

    }






}