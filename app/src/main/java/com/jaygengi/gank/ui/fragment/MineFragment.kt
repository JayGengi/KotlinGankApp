package com.jaygengi.gank.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.ui.activity.WebViewActivity
import kotlinx.android.synthetic.main.fragment_mine.*

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
        tv_view_homepage.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("title", "JayGengi")
            intent.putExtra("url", "https://github.com/JayGengi")
            startActivity(intent)
        }
    }

    override fun lazyLoad() {

    }






}