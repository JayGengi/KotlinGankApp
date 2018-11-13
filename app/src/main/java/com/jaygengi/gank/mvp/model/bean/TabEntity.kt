package com.jaygengi.gank.mvp.model.bean

import com.flyco.tablayout.listener.CustomTabEntity



 /**
   * @description: 首页tab entity
   * @author JayGengi
   * @date  2018/11/13 0013 上午 10:47
   * @email jaygengiii@gmail.com
   */
class TabEntity(var title: String, private var selectedIcon: Int, private var unSelectedIcon: Int) : CustomTabEntity {

    override fun getTabTitle(): String {
        return title
    }

    override fun getTabSelectedIcon(): Int {
        return selectedIcon
    }

    override fun getTabUnselectedIcon(): Int {
        return unSelectedIcon
    }
}