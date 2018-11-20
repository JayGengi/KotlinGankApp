package com.jaygengi.gank.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.ui.activity.WebViewActivity
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by xuhao on 2017/11/9.
 * 我的
 */
class MineFragment : BaseFragment(), View.OnClickListener {


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
        github_url.setOnClickListener(this)
        gank_url.setOnClickListener(this)
        demo_url.setOnClickListener(this)
        qmui_url.setOnClickListener(this)
        retrofit_url.setOnClickListener(this)
        adapter_url.setOnClickListener(this)
        smartRefreshLayout_url.setOnClickListener(this)
        multiple_status_view_url.setOnClickListener(this)

    }

    override fun lazyLoad() {

    }

    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.github_url -> {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("title","JayGengi")
                intent.putExtra("url", "https://github.com/JayGengi/KotlinGankApp")
                startActivity(intent)
            }
            R.id.gank_url -> {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("title","Gank.io")
                intent.putExtra("url", "https://gank.io")
                startActivity(intent)
            }
            R.id.demo_url -> {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("title","KotlinMvp")
                intent.putExtra("url", "https://github.com/git-xuhao/KotlinMvp")
                startActivity(intent)
            }
            R.id.qmui_url -> {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("title","提高 Android UI 开发效率的 UI 库")
                intent.putExtra("url", "https://github.com/QMUI/QMUI_Android")
                startActivity(intent)
            }
            R.id.retrofit_url -> {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("title","retrofit")
                intent.putExtra("url", "https://github.com/square/retrofit")
                startActivity(intent)
            }
            R.id.multiple_status_view_url -> {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("title","MultipleStatusView")
            intent.putExtra("url", "https://github.com/qyxxjd/MultipleStatusView")
            startActivity(intent)
            }
            R.id.smartRefreshLayout_url -> {
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("title","SmartRefreshLayout")
                intent.putExtra("url", "https://github.com/scwang90/SmartRefreshLayout")
                startActivity(intent)
            }



        }
    }




}