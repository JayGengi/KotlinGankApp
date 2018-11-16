package com.jaygengi.gank.ui.activity

import android.annotation.SuppressLint
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseActivity
import kotlinx.android.synthetic.main.activity_search.*


/**
   * @description: 首頁搜索頁
   * @author JayGengi
   * @date  2018/10/29 0029 上午 11:57
   * @email jaygengiii@gmail.com
   */


class SearchActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.activity_search
    }

    @SuppressLint("ResourceAsColor")
    override fun initView() {
        topbar.setTitle("Gank Search").setTextColor(R.color.color_black)
        topbar.addLeftImageButton(R.drawable.ic_left,R.id.left).setOnClickListener {
            finish()
        }
    }

    override fun start() {
    }

    override fun initData() {
    }



}
