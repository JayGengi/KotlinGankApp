package com.jaygengi.gank.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

 /**  
   * @description: PageAdapter
   * @author JayGengi
   * @date  2018/11/15 0015 下午 4:48
   * @email jaygengiii@gmail.com
   */
class HomePageAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    fun setData(fragments: List<Fragment>, titles: List<String>) {
        this.fragments.clear()
        this.fragments.addAll(fragments)
        this.titles.clear()
        this.titles.addAll(titles)
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int) = titles[position]

}