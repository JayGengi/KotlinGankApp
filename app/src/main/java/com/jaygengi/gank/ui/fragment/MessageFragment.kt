package com.jaygengi.gank.ui.fragment

import android.os.Bundle
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment

/**
   * @description: 消息中心
   * @author JayGengi
   * @date  2018/10/29 0029 下午 1:55
   * @email jaygengiii@gmail.com
   */
class MessageFragment : BaseFragment(){

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): MessageFragment {
            val fragment = MessageFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_message

    override fun lazyLoad() {
    }

    override fun initView() {
    }
}