package com.jaygengi.gank.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jaygengi.gank.R
 /**
   * @description: 获取最新一天的干货适配器
  * @author JayGengi
   * @date  2018/11/14 0014 下午 5:01
   * @email jaygengiii@gmail.com
   */
class ToDaySectionAdapter(data: List<String>?)
    : BaseQuickAdapter<String, BaseViewHolder>
    (R.layout.item_today_header, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tvHeader,item)
    }
}
