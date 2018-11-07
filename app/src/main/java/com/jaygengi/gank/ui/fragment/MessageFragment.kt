package com.jaygengi.gank.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.util.Log
import android.view.View
import com.jaygengi.gank.R
import com.jaygengi.gank.base.BaseFragment
import com.jaygengi.gank.ui.fragment.rongim.Friend
import com.jaygengi.gank.ui.fragment.rongim.FriendFragment
import io.rong.imkit.RongIM
import io.rong.imkit.fragment.ConversationListFragment
import io.rong.imlib.model.Conversation
import io.rong.imlib.model.UserInfo
import kotlinx.android.synthetic.main.fragment_message.*
import java.util.*

/**
   * @description: 消息中心
   * @author JayGengi
   * @date  2018/10/29 0029 下午 1:55
   * @email jaygengiii@gmail.com
   */
class MessageFragment : BaseFragment(),View.OnClickListener,RongIM.UserInfoProvider  {

    private var mFragmentPagerAdapter: FragmentPagerAdapter? = null//将tab页面持久在内存中
    private var mConversationList: Fragment? = null
    private val mConversationFragment: Fragment? = null
    private val mFragment = ArrayList<Fragment>()
    private var mTitle: String? = null

    private var userIdList = ArrayList<Friend>()
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
        initUserInfo()
        iv_back_left.setOnClickListener(this)
        mConversationList = initConversationList()//获取融云会话列表的对象

        mFragment.add(mConversationList!!)//加入会话列表
        mFragment.add(FriendFragment.getInstance())
        //配置ViewPager的适配器
        mFragmentPagerAdapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getItem(position: Int): Fragment {
                return mFragment[position]
            }

            override fun getCount(): Int {
                return mFragment.size
            }
        }
        viewpager!!.adapter = mFragmentPagerAdapter

    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back_left -> {
                //启动会话界面
                if (RongIM.getInstance() != null)
                    RongIM.getInstance().startPrivateChat(activity, "654321", "JayGeng")
            }

        }
    }

    //初始化用户信息
    private fun initUserInfo() {

        userIdList.add(Friend("123456", "JayGengi", "http://i10.hoopchina.com.cn/hupuapp/bbs/966/16313966/thread_16313966_20180726164538_s_65949_o_w1024_h1024_62044.jpg?x-oss-process=image/resize,w_800/format,jpg"))//悟空图标
        userIdList.add(Friend("654321", "JayGeng", "http://imgsrc.baidu.com/imgad/pic/item/bba1cd11728b4710ff77bfeac9cec3fdfc0323bf.jpg"))//贝吉塔图标
        RongIM.setUserInfoProvider(this, true)
    }

    override fun getUserInfo(s: String?): UserInfo? {
        for (i in userIdList) {
            if (i.userId == s) {
                Log.e("TAG", i.portraitUri)
                return UserInfo(i.userId, i.name, Uri.parse(i.portraitUri))
            }
        }
        Log.e("TAG", "UserId is : $s")
        return null
    }
    private fun initConversationList(): Fragment {
        /**
         * appendQueryParameter对具体的会话列表做展示
         */
        if (mConversationFragment == null) {
            val listFragment = ConversationListFragment()
            val uri = Uri.parse("rong://" + context!!.applicationInfo.packageName).buildUpon()
                    .appendPath("conversationList")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")//设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")
                    // .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                    //.appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//公共服务号
                    .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置私聊会话是否聚合显示
                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置私聊会是否聚合显示
                    .build()
            listFragment.uri = uri
            return listFragment
        } else {
            return mConversationFragment
        }
    }
}