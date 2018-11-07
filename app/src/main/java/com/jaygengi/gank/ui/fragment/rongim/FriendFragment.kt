package com.jaygengi.gank.ui.fragment.rongim


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jaygengi.gank.R


/**
 * @description Friend
 * @author JayGengi
 * @date  2018/10/30 0030 上午 11:39
 * @email jaygengiii@gmail.com
 */

class FriendFragment : Fragment() {
    private var mView: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_friend, container, false)

        return mView

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: FriendFragment? = null   // 单例模式

        fun getInstance(): FriendFragment {
            if (instance == null) {
                instance = FriendFragment()
            }
            return instance as FriendFragment
        }
    }
}
